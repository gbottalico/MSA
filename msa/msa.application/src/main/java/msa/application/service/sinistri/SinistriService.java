package msa.application.service.sinistri;

import msa.application.config.BaseDTO;
import msa.application.config.enumerator.MessageType;
import msa.application.dto.ricerca.InputRicercaDTO;
import msa.application.dto.sinistro.*;
import msa.application.dto.sinistro.anagrafica.AnagraficaTerzePartiDTO;
import msa.application.dto.sinistro.rca.cai.CaiDTO;
import msa.application.dto.sinistro.rca.constatazioneAmichevole.ConstatazioneAmichevoleDTO;
import msa.application.dto.sinistro.rca.dannoRca.AnagraficaDanniDTO;
import msa.application.dto.sinistro.rca.dannoRca.DannoRcaDTO;
import msa.application.dto.sinistro.rca.eventoRca.EventoRcaDTO;
import msa.application.dto.sinistro.segnalazione.SegnalazioneDTO;
import msa.application.exceptions.InternalMsaException;
import msa.application.service.interfaceDispatcher.DispatcherService;
import msa.domain.Converter.FunctionUtils;
import msa.domain.object.dominio.BaremesDO;
import msa.domain.object.dominio.CompagniaDO;
import msa.domain.object.sinistro.AnagraficaTerzePartiDO;
import msa.domain.object.sinistro.BaseSinistroDO;
import msa.domain.object.sinistro.InputRicercaDO;
import msa.domain.object.sinistro.SinistroRcaDO;
import msa.domain.object.sinistro.rca.IncrociBaremesDO;
import msa.infrastructure.repository.DomainRepository;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SinistriService extends BaseSinistroService {

    @Autowired
    private DomainRepository domainRepository;

    @Autowired
    private DispatcherService dispatcherService;


    /**
     * Metodo che effettua la ricerca le coperture in base ai parametri passati in input
     *
     * @param input un oggetto di tipo InputRicercaDTO che contiene le informazioni con cui effettuare la ricerca
     * @return
     */
    public BaseDTO<List<BaseSinistroDTO>> ricercaCopertura(InputRicercaDTO input) throws InternalMsaException {
        if (FunctionUtils.checkIsNotNull(input.getCompagnia()/**, input.getDataEvento()*/)) {
            throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA003"));
        }
        InputRicercaDO inputRicercaDO = converter.convertObject(input, InputRicercaDO.class);
        return new BaseDTO<>(converter.convertList(sinistriRepository.getElencoSinistriProvvisori(inputRicercaDO), BaseSinistroDTO.class));
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
            final Integer numSinis = sinistriRepository.insertSinistroProvvisorioAndGetNum(converter.convertObject(input, BaseSinistroDO.class));
            return new BaseDTO(Stream.of(numSinis).collect(Collectors.toMap(e -> "numSinistroProvvisorio", String::valueOf)));
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

    public BaseDTO<Map<String, String>> inviaSegnalazione(SegnalazioneDTO input, Integer numSinistroProvv) throws InternalMsaException {
        final BaseSinistroDO newSinistro = getSinistroDOByDTO(input, numSinistroProvv);
        final BaseSinistroDO oldSinistro = GET_SINISTRO.apply(numSinistroProvv);
        final BaseDTO<Map<String, String>> result = new BaseDTO(Stream.of("").collect(Collectors.toMap(elem -> "tipoSinistro", elem -> "TODO")));
        if (oldSinistro.getSegnalazione() != null && !(input.getGaranziaSelected().equals(oldSinistro.getSegnalazione().getGaranziaSelected()))) {
            final List<Object> objects = execInParallel(
                    () -> salvaSinistro(newSinistro),
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
            if (salvaSinistro(newSinistro)) {
                return result;
            } else
                throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA005", (String e) -> e.concat("Sezione segnalazione")));

        }
    }

    private Boolean getFlagIsCard(SinistroRcaDO sinistroRcaDOByDTO) {
        final CompagniaDO compagnia = domainRepository.getCompagniaByCodCompagnia(sinistroRcaDOByDTO.getCompagnia());
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
        if(toUpdateByNumVeicoli) {
            sinistroRcaDOByDTO.setConstatazioneAmichevole(null);
        }
        if(sinistroRcaDOByDTO.getCai() != null
                && toUpdateByNumVeicoli) {
            sinistroRcaDOByDTO.getCai().setBaremesControparte(null);
            sinistroRcaDOByDTO.getCai().setNoteControparte(null);
            sinistroRcaDOByDTO.getCai().setColpa(null);
        }
        Boolean isCard = getFlagIsCard(sinistroRcaDOByDTO);
        sinistroRcaDOByDTO.getEventoRca().setFlagCard(isCard);
        if (salvaSinistro(sinistroRcaDOByDTO)) {
            return new BaseDTO<>(Stream.of(isCard)
                    .collect(Collectors.toMap(e -> "flagCard", String::valueOf)));
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
        if (salvaSinistro(sinistroRcaDOByDTO)) {
            return new BaseDTO<>();
        } else {
            throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA005", (String e) -> e.concat("Sezione Salvataggio Danni Conducente")));
        }

    }

    public BaseDTO salvaDannoRcaControparte(AnagraficaDanniDTO input, Integer numSinistro) throws InternalMsaException {
        SinistroRcaDO sinistroRcaDOByDTO = getSinistroDOByDTO(input, numSinistro);
        if (salvaSinistro(sinistroRcaDOByDTO)) {
            return new BaseDTO<>();
        } else {
            throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA005", (String e) -> e.concat("Sezione Salvataggio Danni Controparte")));
        }
    }

    public BaseDTO salvaDannoRcaTerzeParti(List<AnagraficaTerzePartiDTO> input, Integer numSinistro) throws InternalMsaException {
        BaseSinistroDO sinistroDOByDTO = getSinistroDOByDTO(new AnagraficaTerzePartiDTO(), numSinistro);
        List<AnagraficaTerzePartiDO> filteredList = converter.convertList(FunctionUtils.dinstictList(input, AnagraficaTerzePartiDTO::getCf), AnagraficaTerzePartiDO.class);
        sinistroDOByDTO.setAnagraficaTerzeParti(replaceTerzePartiList(sinistroDOByDTO.getAnagraficaTerzeParti(), filteredList, e -> e.getCodRuolo().equals("13")));
        Boolean insertResult = salvaSinistro(sinistroDOByDTO);
        if (insertResult) {
            return new BaseDTO<>(null, addWarningMessageByCondition(() -> "Sono stati inseriti codici fiscali o partite iva duplicati",
                    FunctionUtils.equalsListSize(input, filteredList)));
        } else {
            throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA005", (String e) -> e.concat("Sezione Salvataggio Danni Terze Parti")));
        }

    }

    private List<AnagraficaTerzePartiDO> replaceTerzePartiList(List<AnagraficaTerzePartiDO> oldList, List<AnagraficaTerzePartiDO> newList, Predicate<AnagraficaTerzePartiDO> toExclude) {
        List<AnagraficaTerzePartiDO> filteredOldList = CollectionUtils.isEmpty(oldList)? new ArrayList<>() : oldList.stream().filter(toExclude).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(filteredOldList) && CollectionUtils.isNotEmpty(newList)) {
            return Stream.concat(newList.stream(), filteredOldList.stream()).collect(Collectors.toList());
        } else if (CollectionUtils.isNotEmpty(filteredOldList) && CollectionUtils.isEmpty(newList)) {
            return filteredOldList;

        } else return newList;

    }

    public BaseDTO salvaDannoRcaLegale(List<AnagraficaTerzePartiDTO> input, Integer numeroSinistro) throws InternalMsaException {
        BaseSinistroDO sinistroDOByDTO = getSinistroDOByDTO(new AnagraficaTerzePartiDTO(), numeroSinistro);
        List<AnagraficaTerzePartiDO> filteredList = converter.convertList(FunctionUtils.dinstictList(input, AnagraficaTerzePartiDTO::getCf), AnagraficaTerzePartiDO.class);
        sinistroDOByDTO.setAnagraficaTerzeParti(replaceTerzePartiList(sinistroDOByDTO.getAnagraficaTerzeParti(), filteredList, e -> !e.getCodRuolo().equals("13")));
        Boolean insertResult = salvaSinistro(sinistroDOByDTO);

        if (insertResult) {
            return new BaseDTO<>(null, addWarningMessageByCondition(() -> "Sono stati inseriti codici fiscali o partite iva duplicati",
                   FunctionUtils.equalsListSize(input, filteredList)));
        } else {
            throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA005", (String e) -> e.concat("Sezione Salvataggio Legale")));
        }
    }

    public BaseDTO salvaPerito(PeritoDTO input, Integer numSinistro) throws InternalMsaException {
        if (salvaSinistro(getSinistroDOByDTO(input, numSinistro))) {
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
            final Class<T> toPass = sinistroByNumProvv.getSegnalazione() == null ? (Class<T>) BaseSinistroDTO.class : getClassByGaranzia(sinistroByNumProvv.getSegnalazione().getGaranziaSelected());
            return converter.convertObject(sinistroByNumProvv, toPass);
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
        //TODO MOCK per mancanza del servizio sui centri convenzionati
        ArrayList<CentroConvenzionatoDTO> centri = new ArrayList<>();
        centri.add(new CentroConvenzionatoDTO(1, "FinconsGroup", "16.853831", "41.103556"));
        centri.add(new CentroConvenzionatoDTO(2, "Angiulli", "16.855179", "41.1075051"));
        centri.add(new CentroConvenzionatoDTO(3, "Policlinico", "16.862622", "41.112062"));
        return centri;


    }

    public BaseDTO salvaCentroConvenzionato(CentroConvenzionatoDTO input, Integer numSinistro) throws InternalMsaException {
        if (salvaSinistro(getSinistroDOByDTO(input, numSinistro))) {
            return new BaseDTO<>();
        }
        throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA005", (String e) -> e.concat("Sezione Salvataggio dati centro convenzionato")));

    }

    public PeritoDTO getPerito(IndirizzoDTO indirizzo) {
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

}

