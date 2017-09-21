package msa.application.service.sinistri;

import msa.application.commons.Constants;
import msa.application.commons.SuperConverter;
import msa.application.config.BaseDTO;
import msa.application.config.enumerator.MessageType;
import msa.application.dto.ricerca.FullPolizzaDTO;
import msa.application.dto.ricerca.InputRicercaDTO;
import msa.application.dto.ricerca.OutputRicercaDTO;
import msa.application.dto.sinistro.*;
import msa.application.dto.sinistro.anagrafica.AnagraficaTerzePartiDTO;
import msa.application.dto.sinistro.anagrafica.FullAnagraficaControparteDTO;
import msa.application.dto.sinistro.rca.cai.CaiDTO;
import msa.application.dto.sinistro.rca.constatazioneAmichevole.ConstatazioneAmichevoleDTO;
import msa.application.dto.sinistro.rca.dannoRca.AnagraficaDanniDTO;
import msa.application.dto.sinistro.rca.dannoRca.DannoRcaDTO;
import msa.application.dto.sinistro.rca.eventoRca.EventoRcaDTO;
import msa.application.dto.sinistro.segnalazione.SegnalazioneDTO;
import msa.application.dto.user.UserLoggedDTO;
import msa.application.exceptions.InternalMsaException;
import msa.application.service.interfaceDispatcher.DispatcherService;
import msa.application.service.sinistri.tipoSinistro.TipoSinistroTreeMap;
import msa.domain.Converter.FunctionUtils;
import msa.domain.Converter.commonObject.GenericTupla;
import msa.domain.object.dominio.*;
import msa.domain.object.enums.TipiSinisto;
import msa.domain.object.ricerca.FullPolizzaDO;
import msa.domain.object.sinistro.*;
import msa.domain.object.sinistro.rca.AnagraficaDanniDO;
import msa.domain.object.sinistro.rca.IncrociBaremesDO;
import msa.domain.object.sinistro.rca.SegnalazioneDO;
import msa.infrastructure.costanti.MsaCostanti;
import msa.infrastructure.repository.DomainRepository;
import msa.infrastructure.repository.PolizzeRepository;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SinistriService extends BaseSinistroService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SinistriService.class);
    @Autowired
    private DomainRepository domainRepository;

    @Autowired
    private DispatcherService dispatcherService;

    @Autowired
    private PolizzeRepository polizzeRepository;

    @SuppressWarnings("unchecked")
    public BaseDTO<OutputRicercaDTO> ricerca(InputRicercaDTO input) throws InternalMsaException {
        List<Object> objects = execInParallel(
                () -> ricercaSinistriProvvisori(input),
                () -> ricercaPolizze(input)
        );
        final OutputRicercaDTO toReturn = new OutputRicercaDTO();
        toReturn.setSinistriProvvisori((List<? extends BaseSinistroDTO>) objects.get(0));
        toReturn.setPolizze(FunctionUtils.castValueByClass((List) objects.get(1), FullPolizzaDTO.class));


        //TODO SALVARE LA POLIZZA IN MONGO
        return new BaseDTO<>(toReturn);
    }

    /**
     * Metodo che effettua la ricerca le coperture in base ai parametri passati in input
     *
     * @param input un oggetto di tipo InputRicercaDTO che contiene le informazioni con cui effettuare la ricerca
     * @return
     */
    private <K extends BaseSinistroDTO> List<K> ricercaSinistriProvvisori(final InputRicercaDTO input) throws InternalMsaException {
        if (!FunctionUtils.checkIsNotNull(input.getCompagnia()/**, input.getDataEvento()*/)) {
            throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA003"));
        }
        InputRicercaDO inputRicercaDO = converter.convertObject(input, InputRicercaDO.class);
        return sinistriRepository.getElencoSinistriProvvisori(inputRicercaDO).stream().map(e -> {
            final Class<K> toPass = e.getSegnalazione() == null
                    ? (Class<K>) BaseSinistroDTO.class
                    : getClassByGaranzia(e.getSegnalazione().getGaranziaSelected());
            return converter.convertObject(e, toPass);
        }).collect(Collectors.toList());

    }

    private List<FullPolizzaDTO> ricercaPolizze(final InputRicercaDTO input) throws InternalMsaException {
        //Todo MOCK
        //Todo Add polizze mock

        final FullPolizzaDTO polizza1 = new FullPolizzaDTO();
        polizza1.setNumeroPolizza("abc");
        polizza1.setTarga("ab123cd");
        polizza1.setNominativoContraente("ciao ciao");
        polizza1.setStato("stato");
        polizza1.setDataVariazione(FunctionUtils.nowAsDate());
        polizza1.setDataAttivazione(FunctionUtils.nowAsDate());
        polizza1.setDataScadenza(FunctionUtils.nowAsDate());
        final FullPolizzaDTO polizza2 = new FullPolizzaDTO();
        polizza2.setNumeroPolizza("abcd");
        polizza2.setTarga("ab123cd");
        polizza2.setNominativoContraente("ciao ciao");
        polizza2.setStato("stato");
        polizza2.setDataVariazione(FunctionUtils.nowAsDate());
        polizza2.setDataAttivazione(FunctionUtils.nowAsDate());
        polizza2.setDataScadenza(FunctionUtils.nowAsDate());
        List<FullPolizzaDTO> collect = Stream.of(polizza1, polizza2).collect(Collectors.toList());
        asyncSavePolizzeInMongo(collect, input.getUserLogged());
        return converter.convertList(collect, FullPolizzaDTO.class);
    }

    @Async
    public void asyncSavePolizzeInMongo(final List<FullPolizzaDTO> polizze, UserLoggedDTO userLogged) {
        polizzeRepository.deletePolizzeForUser(converter.convertObject(userLogged, UserLoggedDO.class));
        polizzeRepository.savePolizzeMsa(converter.convertList(polizze, FullPolizzaDO.class));
    }


    private FullAnagraficaControparteDTO getProprietarioIfIsDifferent(final BaseSinistroDTO input) {
        if (input.getContraente().getCf().equalsIgnoreCase(input.getProprietario().getCf())) {
            return input.getContraente();
        } else {
            final FullAnagraficaControparteDTO proprietario = input.getProprietario();
            final Optional<ProvinciaDO> provinviaBySiglaProvincia = domainRepository.getProvinviaBySiglaProvincia(proprietario.getTracking().getResidenza().getCodProvincia());
            if (provinviaBySiglaProvincia.isPresent()) {
                proprietario.getTracking().getResidenza().setCodProvincia(provinviaBySiglaProvincia.map(ProvinciaDO::getCodProvincia).map(Object::toString).get());
                proprietario.getTracking().getResidenza().setDescrizioneProvincia(provinviaBySiglaProvincia.map(ProvinciaDO::getDescProvincia).get());
                final ComuneDO comuneDO = domainRepository.getElencoComuni(
                        proprietario.getTracking().getResidenza().getCodNazione(),
                        provinviaBySiglaProvincia.map(ProvinciaDO::getCodProvincia).get(),
                        proprietario.getTracking().getResidenza().getDescrizioneComune()).stream().reduce((a, b) -> a).orElse(null);
                proprietario.getTracking().getResidenza().setCodComune(comuneDO.getCodComune());
                proprietario.setCodRuolo(MsaCostanti.COD_RUOLO_PROPRIETARIO.toString());
                input.setProprietario(proprietario);
            }
            return proprietario;
        }
    }

    /**
     * Metodo esposto per l' inserimento del sinistro alla prima fase
     *
     * @param input
     * @return
     * @throws InternalMsaException
     */
    public BaseDTO<Map<String, Integer>> salvaSinistro(BaseSinistroDTO input) throws InternalMsaException {
        try {
            if (input.getNumeroPolizza() != null) {
                input.getContraente().setVeicolo(domainRepository.getElencoTipoVeicoli()
                        .stream()
                        .filter(e -> e.getDescVeicolo().equalsIgnoreCase(input.getContraente().getVeicolo()))
                        .map(TipoVeicoloDO::getId)
                        .findFirst()
                        .map(Object::toString)
                        .orElse(null));
                input.getContraente().setCodRuolo(MsaCostanti.COD_RUOLO_CONTRAENTE.toString());
                input.getContraente().setCompagnia(input.getCompagnia());
                input.setProprietario(getProprietarioIfIsDifferent(input));
            }
            final Integer numSinis = sinistriRepository.insertSinistroProvvisorioAndGetNum(converter.convertObject(input, BaseSinistroDO.class));
            return new BaseDTO(Stream.of(numSinis).collect(Collectors.toMap(e -> "numSinistroProvvisorio", Integer::valueOf)));
        } catch (Exception e) {
            throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA004"));
        }
    }

    /**
     * Metodo centralizzato per salvataggio o modifica sinistro
     *
     * @param input
     * @return
     */
    private <E extends BaseSinistroDO> Boolean salvaSinistro(E input) throws InternalMsaException {

        if (input.getNumSinistroProvv() == null) {
            try {
                return sinistriRepository.insertSinistroProvvisorio(input);
            } catch (Exception e) {
                throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA004"));
            }
        } else {
            try {
                return sinistriRepository.updateSinistroProvvisorio(input);
            } catch (Exception e) {
                throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA005"));
            }
        }
    }

    /**
     * Metodo che salva i dati di segnalazione sinistro
     *
     * @param input
     * @param numSinistroProvv
     * @return
     */

    public <T extends BaseSinistroDO> BaseDTO inviaSegnalazione(SegnalazioneDTO input, Integer numSinistroProvv) throws InternalMsaException {

        if (input.getGaranziaSelected() == null)
            throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA005", (String e) -> e.concat(" E' necessario selezionare una garanzia.")));
        if (!FunctionUtils.between(input.getDataDenuncia(), input.getDataOraSinistro(), FunctionUtils.nowAsDate(), Boolean.TRUE))
            throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA005",
                    (String e) -> e.concat(" Data sinistro deve essere precedente alla data di denuncia ed alla data odierna.")));
        final T newSinistro;
        T oldSinistro = null;
        try {
            newSinistro = sinistriRepository.getSinistroByNumProvv(numSinistroProvv);//getSinistroDOByDTO(input, numSinistroProvv);
            oldSinistro = converter.clone(newSinistro);
        } catch (Exception ex) {
            throw new InternalMsaException(ex, getErrorMessagesByCodErrore(MessageType.ERROR, "MSA005", (String e) -> e.concat("Sezione segnalazione")));
        }
        final BaseDTO result = new BaseDTO();
        if (oldSinistro.getSegnalazione() != null && !(input.getGaranziaSelected().equals(oldSinistro.getSegnalazione().getGaranziaSelected()))) {
            final List<Object> objects = execInParallel(
                    () -> {
                        newSinistro.setSegnalazione(converter.convertObject(input, SegnalazioneDO.class));
                        newSinistro.setLegali(null);
                        newSinistro.setCentroConvenzionato(null);
                        newSinistro.setPerito(null);
                        return salvaSinistro(converter.convertObject(newSinistro, BaseSinistroDO.class));
                    },
                    () -> dispatcherService.resetView(input.getGaranziaSelected(), numSinistroProvv)
            );
            final Optional<Boolean> conditions = Optional.of(objects.stream().filter(e -> e.getClass().isAssignableFrom(Boolean.class))
                    .findFirst().map(e -> {
                        if ((Boolean) e) {
                            return Boolean.TRUE;
                        } else {
                            return null;
                        }
                    }).orElseGet(() -> Boolean.FALSE)
                    && objects.stream().filter(e -> e.getClass().isAssignableFrom(Integer.class))
                    .findFirst().map(e -> {
                        if ((Integer) e > 0) {
                            return Boolean.TRUE;
                        } else {
                            return null;
                        }
                    }).orElseGet(() -> Boolean.FALSE));

            return conditions.map(e -> result).orElseThrow(() -> new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA005", (String e) -> e.concat("Sezione segnalazione"))));
        } else {
            newSinistro.setSegnalazione(converter.convertObject(input, SegnalazioneDO.class));
            if (salvaSinistro(newSinistro)) {
                return result;
            } else
                throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA005", (String e) -> e.concat("Sezione segnalazione")));

        }
    }

    private Boolean getFlagIsCardCompagnia(final Integer codCompagnia) {
        final CompagniaDO compagnia = domainRepository.getCompagniaByCodCompagnia(codCompagnia);
        final Boolean isCard = FunctionUtils.between(
                FunctionUtils.nowAsDate(),
                compagnia.getDataInCard(),
                compagnia.getDataOutCard(),
                true);
        return isCard;
    }

    /**
     * Metodo che salva i dati dell'evento RCA
     *
     * @param input
     * @param numSinistroProvv
     * @return
     */
    public BaseDTO<Map<String, String>> salvaEventoRca(EventoRcaDTO input, Integer numSinistroProvv) throws InternalMsaException {

        final SinistroRcaDO sinistroRcaDOByDTO = getSinistroDOByDTO(input, numSinistroProvv);
        final Boolean toUpdateByNumVeicoli = sinistroRcaDOByDTO.getEventoRca() != null
                && Integer.compare(sinistroRcaDOByDTO.getEventoRca().getNumVeicoli(), input.getNumVeicoli()) != 0
                && input.getNumVeicoli() < 2;
        if (toUpdateByNumVeicoli) {
            sinistroRcaDOByDTO.setConstatazioneAmichevole(null);
        }
        if (sinistroRcaDOByDTO.getCai() != null
                && toUpdateByNumVeicoli) {
            sinistroRcaDOByDTO.getCai().setBaremesControparte(null);
            sinistroRcaDOByDTO.getCai().setNoteControparte(null);
            sinistroRcaDOByDTO.getCai().setColpa(null);
        }
        if (salvaSinistro(sinistroRcaDOByDTO)) {
            return new BaseDTO<>();
        } else
            throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA005", (String e) -> e.concat("Sezione Evento RCA")));
    }

    /**
     * Metodo che salva i dati della constatazione amichevole nel caso in cui i veicoli coinvolti siano più di 2
     *
     * @param input
     * @param numSinistroProvv
     * @return
     */
    public BaseDTO salvaConstatazioneAmichevole(ConstatazioneAmichevoleDTO input, Integer numSinistroProvv) throws InternalMsaException {
        Boolean res = salvaSinistro(getSinistroDOByDTO(input, numSinistroProvv));
        if (res) {
            return new BaseDTO<>();
        } else {
            throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA005", (String e) -> e.concat("Sezione Constatazione Amichevole")));
        }
    }

    /**
     * Metodo che calcola la responsabilità in base ai baremes inseriti
     *
     * @param input
     * @param numSInistroProvv
     * @return
     */
    public BaseDTO<Map<String, String>> salvaCAI(CaiDTO input, Integer numSInistroProvv) throws InternalMsaException {
        SinistroRcaDO sinistroRcaDOByDTO = getSinistroDOByDTO(input, numSInistroProvv);
        final Boolean existControparte = input.getBaremesControparte() != null;
        final String codColpa;
        if (existControparte) {
            codColpa = calcolaColpaBaremes(input);
            sinistroRcaDOByDTO.getCai().setColpa(codColpa);
        } else {
            codColpa = null;
        }
        if (salvaSinistro(sinistroRcaDOByDTO)) {
            return existControparte ?
                    new BaseDTO<>(Stream.of("").collect(Collectors.toMap(e -> "responsabilita", e -> codColpa)))
                    : new BaseDTO<>();
        } else {
            throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA005", (String e) -> e.concat("Sezione CAI")));
        }
    }

    public String calcolaColpaBaremes(CaiDTO input) throws InternalMsaException {
        try {
            BaremesDO baremesCliente = converter.convertObject(input.getBaremesCliente(), BaremesDO.class);
            BaremesDO baremesControparte = converter.convertObject(input.getBaremesControparte(), BaremesDO.class);
            IncrociBaremesDO colpaByBaremes = domainRepository.getColpaByBaremes(baremesCliente, baremesControparte);
            return colpaByBaremes.getCodResponsabilita();
        } catch (Exception e) {
            throw new InternalMsaException(e, getErrorMessagesByCodErrore(MessageType.ERROR, "MSA013"));
        }
    }

    public BaseDTO salvaDannoRcaConducente(DannoRcaDTO input, Integer numSinistro) throws InternalMsaException {

        SinistroRcaDO sinistroRcaDOByDTO = getSinistroDOByDTO(input, numSinistro);
        if (!input.getConducenteDiverso()) {
            sinistroRcaDOByDTO.getDannoRca()
                    .getAnagraficaDanniCliente()
                    .setAnagrafica(converter.convertObject(sinistroRcaDOByDTO.getContraente(), FullAnagraficaControparteDO.class));

            sinistroRcaDOByDTO.getDannoRca()
                    .getAnagraficaDanniCliente().getAnagrafica().setCodRuolo(MsaCostanti.COD_RUOLO_CONDUCENTE_PROPR.toString());
        } else {
            sinistroRcaDOByDTO
                    .getDannoRca()
                    .getAnagraficaDanniCliente()
                    .getAnagrafica()
                    .setCodRuolo(MsaCostanti.COD_RUOLO_CONDUCENTE_NO_PROPR.toString());
            if (sinistroRcaDOByDTO.getContraente() != null) {
                converter.biConvertObject(sinistroRcaDOByDTO
                                .getDannoRca()
                                .getAnagraficaDanniCliente().getAnagrafica(), sinistroRcaDOByDTO.getContraente(),
                        (conducente, contraente) -> {
                            conducente.setTarga(contraente.getTarga());
                            conducente.setVeicolo(contraente.getVeicolo());
                            conducente.setTargaEstera(contraente.getTargaEstera());
                            conducente.setTargaSpeciale(contraente.getTargaSpeciale());
                            return conducente;
                        });
            }
        }

        if (sinistroRcaDOByDTO.getEventoRca().getNumVeicoli() == 2) {
            Boolean isCard = getFlagIsCardCompagnia(sinistroRcaDOByDTO.getCompagnia());
            sinistroRcaDOByDTO.getDannoRca().getAnagraficaDanniCliente().getAnagrafica().setFlagCard(isCard);
        } else {
            sinistroRcaDOByDTO.getDannoRca().getAnagraficaDanniCliente().getAnagrafica().setFlagCard(Boolean.FALSE);
        }

        if (salvaSinistro(sinistroRcaDOByDTO)) {
            return new BaseDTO<>();
        } else {
            throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA005", (String e) -> e.concat("Sezione Salvataggio Danni Conducente")));
        }
    }

    public BaseDTO salvaDannoRcaControparte(List<AnagraficaDanniDTO> input, Integer numSinistro) throws InternalMsaException {
        if (input.stream().filter(e -> e.getDanni() != null).count() != Constants.MAX_NUM_CONTROPARTE_CON_DANNI.longValue()) {
            throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA005",
                    (String e) -> e.concat(" Devono essere censiti i danni di una sola controparte.  ")));
        }
        SinistroRcaDO sinistroRcaDOByDTO = getSinistroDOByDTO(new AnagraficaDanniDTO(), numSinistro);
        sinistroRcaDOByDTO.getDannoRca().setAnagraficaDanniControparte(converter.convertList(input, AnagraficaDanniDO.class).stream().map(e -> {
            e.getAnagrafica().setFlagCard(sinistroRcaDOByDTO.getEventoRca().getNumVeicoli() == 2
                    ? getFlagIsCardCompagnia(FunctionUtils.numberConverter(e.getAnagrafica().getCompagnia(), Integer::valueOf))
                    : Boolean.FALSE);
            e.getAnagrafica().setCodRuolo(MsaCostanti.COD_RUOLO_CONTROPARTE.toString());
            return e;
        }).collect(Collectors.toList()));
        salvaFlagCardPerSinistro(sinistroRcaDOByDTO);

        if (salvaSinistro(sinistroRcaDOByDTO)) {
            return new BaseDTO<>();
        } else {
            throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA005", (String e) -> e.concat("Sezione Salvataggio Danni Controparte")));
        }
    }

    @Async
    public void salvaFlagCardPerSinistro(final SinistroRcaDO sinistroByNumProvv) throws InternalMsaException {
        try {
            final List<Object> objects = execInParallel(
                    () -> FunctionUtils.between(sinistroByNumProvv.getSegnalazione().getDataOraSinistro(),
                            MsaCostanti.dataInizioCard.get(),
                            new Date(),
                            Boolean.TRUE),
                    () -> Integer.compare(sinistroByNumProvv.getEventoRca().getNumVeicoli(), 2) == 0,
                    () -> MsaCostanti.nazioneInCard.test(FunctionUtils.numberConverter(
                            sinistroByNumProvv.getSegnalazione().getLuogoSinistro().getCodNazione(),
                            Integer::valueOf)),
                    () -> sinistroByNumProvv.getDannoRca().getAnagraficaDanniCliente().getAnagrafica().getFlagCard(),
                    () -> sinistroByNumProvv.getDannoRca()
                            .getAnagraficaDanniControparte()
                            .stream()
                            .limit(1)
                            .map(AnagraficaDanniDO::getAnagrafica)
                            .map(FullAnagraficaControparteDO::getFlagCard)
                            .reduce((a, b) -> a).orElse(null)
            );
            final boolean b = objects.stream().allMatch(e -> BooleanUtils.compare((Boolean) e, Boolean.TRUE) == 0);
            sinistroByNumProvv.setFlagSinistroCard(b);
            if (!salvaSinistro(sinistroByNumProvv)) {
                throw new InternalMsaException();
            }
        } catch (Exception e) {
            throw new InternalMsaException();
        }
    }

    public BaseDTO salvaDannoRcaTerzeParti(List<AnagraficaTerzePartiDTO> input, Integer numSinistro) throws InternalMsaException {
        if (input.stream().anyMatch(e -> e.getCodRuolo() == null))
            throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA005", (String e) -> e.concat(" Il codice ruolo di ogni terza parte deve essere valorizzato. ")));
        SinistroRcaDO sinistroDOByDTO = getSinistroDOByDTO(new AnagraficaTerzePartiDTO(), numSinistro);
        List<AnagraficaTerzePartiDO> filteredList = converter.convertList(FunctionUtils.dinstictList(input, AnagraficaTerzePartiDTO::getCf), AnagraficaTerzePartiDO.class);
        sinistroDOByDTO.getDannoRca().setTerzeParti(filteredList);
        Boolean insertResult = salvaSinistro(sinistroDOByDTO);
        if (insertResult) {
            return new BaseDTO<>(null, addWarningMessageByCondition(() -> "Sono stati inseriti codici fiscali o partite iva duplicati",
                    FunctionUtils.equalsListSize(input, filteredList)));
        } else {
            throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA005", (String e) -> e.concat("Sezione Salvataggio Danni Terze Parti")));
        }

    }


    public <K extends BaseSinistroDO> BaseDTO salvaDannoRcaLegale(List<AnagraficaTerzePartiDTO> input, Integer numeroSinistro) throws InternalMsaException {
        //BaseSinistroDO sinistroDOByDTO = LEGALE.apply(input, numeroSinistro);
        final K sinistroDOByDTO;
        final Boolean insertResult;
        final List<AnagraficaTerzePartiDO> filteredList;
        try {
            sinistroDOByDTO = sinistriRepository.getSinistroByNumProvv(numeroSinistro);
            final List<AnagraficaTerzePartiDTO> listaFiltrata = FunctionUtils.dinstictList(input, AnagraficaTerzePartiDTO::getCf);
            filteredList = converter.convertList(listaFiltrata, AnagraficaTerzePartiDO.class)
                    .stream()
                    .map(e -> {
                        e.setCodRuolo(MsaCostanti.COD_RUOLO_LEGALE.toString());
                        return e;
                    }).collect(Collectors.toList());
            sinistroDOByDTO.setLegali(filteredList);
            insertResult = salvaSinistro(sinistroDOByDTO);
        } catch (Exception ex) {
            throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA005", (String e) -> e.concat("Sezione Salvataggio Legale")));
        }

        if (insertResult) {
            return new BaseDTO<>(null, addWarningMessageByCondition(() -> "Sono stati inseriti codici fiscali o partite iva duplicati",
                    FunctionUtils.equalsListSize(input, filteredList)));
        } else {
            throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA005", (String e) -> e.concat("Sezione Salvataggio Legale")));
        }
    }

    public List<Map<String, Serializable>> getSinistroPartiteDanno(final Integer numSinistroProvv) throws InternalMsaException {
        return getSinistroPartiteDannoSvc(numSinistroProvv);
    }

    private List<Map<String, Serializable>> getSinistroPartiteDannoSvc(final Integer numSinistroProvv) throws InternalMsaException {
        try {
            return getStreamPd(numSinistroProvv)
                    .map(e -> GenericTupla.instance(
                            e.getTarga(), //Todo check if is terza parte
                            domainRepository.getDesRuoloById(e.getCodRuolo()),
                            e).parse(() -> "bene", () -> "ruolo", () -> "note")).collect(Collectors.toList());

        } catch (Exception ex) {
            throw new InternalMsaException(ex, getErrorMessagesByCodErrore(MessageType.ERROR, "MSA005", (String e) -> e.concat("Sezione Partite Danno")));
        }
    }

    public List<FullAnagraficaControparteDTO> getAnagrafichePartiteDanno(final Integer numSinistroProvv) throws InternalMsaException {
        return getSinistroPartiteDannoSvc(numSinistroProvv)
                .stream()
                .map(e -> e.get("note"))
                .flatMap(Stream::of)
                .map(e -> converter.convertObject(FunctionUtils.castValueByClass(e, FullAnagraficaControparteDO.class), FullAnagraficaControparteDTO.class))
                .collect(Collectors.toList());
    }

    public <K extends BaseSinistroDO> BaseDTO salvaPerito(PeritoDTO input, Integer numSinistro) throws InternalMsaException {

        final K sinistroDOByDTO;
        try {
            sinistroDOByDTO = sinistriRepository.getSinistroByNumProvv(numSinistro);
            sinistroDOByDTO.setPerito(converter.convertObject(input, PeritoDO.class));
        } catch (Exception ex) {
            throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA005", (String e) -> e.concat("Sezione Salvataggio Legale")));
        }
        if (salvaSinistro(sinistroDOByDTO)) {
            return new BaseDTO<>();
        } else {
            throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA005", (String e) -> e.concat("Sezione Salvataggio Perito")));
        }
    }


    public BaseDTO inserisciFurtoIncendio(SinistroFurtoIncendioDTO input, Integer numSinistro) throws InternalMsaException {
        if (salvaSinistro(getSinistroDOByDTO(input, numSinistro))) {
            return new BaseDTO<>();
        } else {
            throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA005", (String e) -> e.concat("Sezione Salvataggio dati sinistro furto o incendio")));

        }


    }


    public BaseDTO inserisciKasko(SinistroKaskoDTO input, Integer numSinistro) throws InternalMsaException {
        if (salvaSinistro(getSinistroDOByDTO(input, numSinistro))) {
            return new BaseDTO<>();
        } else {
            throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA005", (String e) -> e.concat("Sezione Salvataggio dati sinistro kasko")));

        }

    }

    public <T extends BaseSinistroDTO, K extends BaseSinistroDO> T getSinistroByNumProvv(final Integer numSinistro) throws InternalMsaException {
        try {
            //Todo MOCK per mancanza di garanzie specifiche o tipi sinistri specifici
            final K sinistroByNumProvv = sinistriRepository.getSinistroByNumProvv(numSinistro);
            final Class<T> toPass = sinistroByNumProvv.getSegnalazione() == null ? (Class<T>) BaseSinistroDTO.class : (Class<T>) getClassByGaranzia(sinistroByNumProvv.getSegnalazione().getGaranziaSelected());
            final T t = converter.convertObject(sinistroByNumProvv, toPass);
            return t;
        } catch (Exception e) {
            throw new InternalMsaException(e, getErrorMessagesByCodErrore(MessageType.ERROR, "MSA009"));
        }
    }

    public BaseDTO inserisciCristalli(SinistroCristalliDTO input, Integer numSinistro) throws InternalMsaException {
        if (salvaSinistro(getSinistroDOByDTO(input, numSinistro))) {
            return new BaseDTO<>();
        } else {
            throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA005", (String e) -> e.concat("Sezione Salvataggio dati sinistro cristalli")));

        }
    }

    public BaseDTO inserisciInfortuniConducente(SinistroInfortuniConducenteDTO input, Integer numSinistro) throws InternalMsaException {
        if (salvaSinistro(getSinistroDOByDTO(input, numSinistro))) {
            return new BaseDTO<>();
        } else {
            throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA005", (String e) -> e.concat("Sezione Salvataggio dati sinistro infortuni conducente")));

        }
    }

    public List<CentroConvenzionatoDTO> getElencoCentriConvenzionati(String indirizzo) {
        //TODO MOCK per mancanza del servizio sui centri convenzionati+ù
        // BISOGNA FARE ANCHE LA VERIFICA SULLA GARANZIA PRIMA DI RESTITUIRE UN CENTRO
        ArrayList<CentroConvenzionatoDTO> centri = new ArrayList<>();
        if (indirizzo.toLowerCase().contains("david".toLowerCase())) {

            centri.add(new CentroConvenzionatoDTO(1, "FinconsGroup", "16.853831", "41.103556"));
            centri.add(new CentroConvenzionatoDTO(2, "Angiulli", "16.855179", "41.1075051"));
        } else {
            centri.add(new CentroConvenzionatoDTO(1, "Parco 2 Giugno", "16.8742974", "41.1044346"));
            centri.add(new CentroConvenzionatoDTO(2, "Campus Via Orabona ", "16.8789361", "41.1074986"));
            centri.add(new CentroConvenzionatoDTO(3, "Policlinico", "16.862622", "41.112062", getPerito("ciao")));
        }
        return centri;


    }

    public <K extends BaseSinistroDO> BaseDTO salvaCentroConvenzionato(CentroConvenzionatoDTO input, Integer numSinistro) throws InternalMsaException {
        final K sinistroDOByDTO;
        try {
            sinistroDOByDTO = sinistriRepository.getSinistroByNumProvv(numSinistro);
            sinistroDOByDTO.setCentroConvenzionato(converter.convertObject(input, CentroConvenzionatoDO.class));
        } catch (Exception ex) {
            throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA005", (String e) -> e.concat("Sezione Salvataggio dati centro convenzionato")));
        }
        if (input.getPerito() != null) {
            //perito default per quel centro convenzionato
            sinistroDOByDTO.setPerito(converter.convertObject(input.getPerito(), PeritoDO.class));
        }
        if (salvaSinistro(sinistroDOByDTO)) {
            return new BaseDTO<>();
        }
        throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA005", (String e) -> e.concat("Sezione Salvataggio dati centro convenzionato")));

    }

    public PeritoDTO getPerito(String indirizzo) {
        //TODO MOCK per mancanza del servizio sul perito

        LuogoDTO luogoPerizia = new LuogoDTO();
        luogoPerizia.setCodComune("18538");
        luogoPerizia.setCodNazione("1");
        luogoPerizia.setCodProvincia("80");
        luogoPerizia.setDescrizioneComune("CAROVIGNO");
        luogoPerizia.setDescrizioneNazione("ITALIA");
        luogoPerizia.setDescrizioneProvincia("BRINDISI");
        PeritoDTO perito = new PeritoDTO();
        perito.setDenominazione("Andrea Esposito");
        perito.setCfPartitaIva("SPSNDR94T05G187D");
        perito.setLuogoPerizia(luogoPerizia);
        perito.setTelefono("000000000");
        perito.setTargaDaPerizare("XX555BB");
        return perito;
    }

    /**
     * Metodo che restituisce una polizza effettuando la ricerca in base al numpoli
     *
     * @param numPoli
     * @return
     */
    public FullPolizzaDTO getPolizzaByNumPoli(String numPoli) throws InternalMsaException {
        try {
            return converter.convertObject(sinistriRepository.getPolizzaByNumPoli(numPoli), FullPolizzaDTO.class);
        } catch (Exception e) {
            throw new InternalMsaException(e, getErrorMessagesByCodErrore(MessageType.ERROR, "MSA014"));

        }
    }

    @Autowired
    private TipoSinistroTreeMap<? super BaseSinistroDO> tipoSinistroTreeMap;

    public <T extends BaseSinistroDO> TipiSinisto getTipoSinistro(final Integer numSinistroProvv) throws InternalMsaException {
        try {
            final T sinistroByNumProvv = sinistriRepository.getSinistroByNumProvv(numSinistroProvv);
            final TipiSinisto tipiSinisto = tipoSinistroTreeMap.calcolaTipoSinistro(sinistroByNumProvv);
            sinistroByNumProvv.setTipoSinisto(tipiSinisto);
            if (salvaSinistro(sinistroByNumProvv)) {
                return tipiSinisto;
            } else {
                throw new InternalMsaException();
            }
        } catch (Exception e) {
            throw new InternalMsaException(e, getErrorMessagesByCodErrore(MessageType.ERROR, "MSA015"));

        }
    }


}

