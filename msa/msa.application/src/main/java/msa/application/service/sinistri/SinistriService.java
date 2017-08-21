package msa.application.service.sinistri;

import msa.application.config.BaseDTO;
import msa.application.config.enumerator.MessageType;
import msa.application.dto.ricerca.InputRicercaDTO;
import msa.application.dto.sinistro.BaseSinistroDTO;
import msa.application.dto.sinistro.PeritoDTO;
import msa.application.dto.sinistro.SinistroRcaDTO;
import msa.application.dto.sinistro.anagrafica.AnagraficaTerzePartiDTO;
import msa.application.dto.sinistro.rca.cai.CaiDTO;
import msa.application.dto.sinistro.rca.constatazioneAmichevole.ConstatazioneAmichevoleDTO;
import msa.application.dto.sinistro.rca.dannoRca.AnagraficaDanniDTO;
import msa.application.dto.sinistro.rca.dannoRca.DannoRcaDTO;
import msa.application.dto.sinistro.rca.eventoRca.EventoRcaDTO;
import msa.application.dto.sinistro.segnalazione.SegnalazioneDTO;
import msa.application.exceptions.InternalMsaException;
import msa.domain.Converter.FunctionUtils;
import msa.domain.object.dominio.BaremesDO;
import msa.domain.object.dominio.CompagniaDO;
import msa.domain.object.sinistro.rca.IncrociBaremesDO;
import msa.domain.object.sinistro.InputRicercaDO;
import msa.domain.object.sinistro.SinistroDO;
import msa.infrastructure.repository.DomainRepository;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SinistriService extends BaseSinistroService {

    @Autowired
    private DomainRepository domainRepository;

    /**
     * Metodo che effettua la ricerca le coperture in base ai parametri passati in input
     *
     * @param input un oggetto di tipo InputRicercaDTO che contiene le informazioni con cui effettuare la ricerca
     * @return
     */
    public BaseDTO ricercaCopertura(InputRicercaDTO input) throws InternalMsaException {
        if (FunctionUtils.checkIsNotNull(input.getCompagnia()/**, input.getDataEvento()*/)) {
            throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA003"));
        }
        InputRicercaDO inputRicercaDO = converter.convertObject(input, InputRicercaDO.class);
        return new BaseDTO<>(converter.convertList(sinistriRepository.getElencoSinistriProvvisori(inputRicercaDO), SinistroRcaDTO.class));
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
            final Integer numSinis = sinistriRepository.insertSinistroProvvisorioAndGetNum(converter.convertObject(input, SinistroDO.class));
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
    private Boolean salvaSinistro(SinistroDO input) throws InternalMsaException {

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
        final SinistroDO sinistroDOByDTO = getSinistroDOByDTO(input, numSinistroProvv);

        if (salvaSinistro(sinistroDOByDTO)) {
            return new BaseDTO(Stream.of("").collect(Collectors.toMap(e -> "tipoSinistro", e -> "TODO")));
        } else
            throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA005", (String e) -> e.concat("Sezione segnalazione")));
    }

    /**
     * Metodo che salva i dati dell'evento RCA
     *
     * @param input
     * @param numSinistroProvv
     * @return
     */
    public BaseDTO<Map<String, String>> salvaEventoRca(EventoRcaDTO input, Integer numSinistroProvv) throws InternalMsaException {

        final SinistroDO sinistroDOByDTO = getSinistroDOByDTO(input, numSinistroProvv);
        CompagniaDO compagnia = domainRepository.getCompagniaByCodCompagnia(sinistroDOByDTO.getCompagnia());
        Boolean isCard = FunctionUtils.between(
                FunctionUtils.nowAsDate(),
                compagnia.getDataInCard(),
                compagnia.getDataOutCard(),
                true);
        sinistroDOByDTO.getEventoRca().setFlagCard(isCard);
        if (salvaSinistro(sinistroDOByDTO)) {
            return new BaseDTO(Stream.of(isCard)
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
    public BaseDTO<Map<String,String>> salvaCAI(CaiDTO input, Integer numSInistroProvv) throws InternalMsaException {
        SinistroDO sinistroDOByDTO = getSinistroDOByDTO(input, numSInistroProvv);
        String codColpa = calcolaColpaBaremes(input);
        sinistroDOByDTO.getCai().setColpa(codColpa);
        if (salvaSinistro(sinistroDOByDTO)) {
            //Inserire logica baremes
            return new BaseDTO<>(Stream.of("").collect(Collectors.toMap(e -> "responsabilita", e -> codColpa)));
        } else {
            throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA005", (String e) -> e.concat("Sezione CAI")));
        }
    }

    private String calcolaColpaBaremes(CaiDTO input) {
        BaremesDO baremesCliente = converter.convertObject(input.getBaremesCliente(),BaremesDO.class);
        BaremesDO baremesControparte = converter.convertObject(input.getBaremesControparte(),BaremesDO.class);
        IncrociBaremesDO colpaByBaremes = domainRepository.getColpaByBaremes(baremesCliente, baremesControparte);
        return colpaByBaremes.getCodResponsabilita();
    }

    public BaseDTO salvaDannoRcaConducente(DannoRcaDTO input, Integer numSinistro) throws InternalMsaException {

        SinistroDO sinistroDOByDTO = getSinistroDOByDTO(input, numSinistro);
        if (salvaSinistro(sinistroDOByDTO)) {
            return new BaseDTO<>();
        } else {
            throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA005", (String e) -> e.concat("Sezione Salvataggio Danni Conducente")));
        }

    }

    public BaseDTO<SinistroRcaDTO> salvaDannoRcaControparte(AnagraficaDanniDTO input, Integer numSinistro) throws InternalMsaException {
        SinistroDO sinistroDOByDTO = getSinistroDOByDTO(input, numSinistro);
        if (salvaSinistro(sinistroDOByDTO)) {
            return new BaseDTO<>();
        } else {
            throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA005", (String e) -> e.concat("Sezione Salvataggio Danni Controparte")));
        }
    }

    public BaseDTO salvaDannoRcaTerzeParti(List<AnagraficaTerzePartiDTO> input, Integer numSinistro) throws InternalMsaException {
        final Boolean insertResult = salvaSinistro(getSinistroDOByDTO(input, numSinistro, (output, aggregatore) -> {
            if (CollectionUtils.isEmpty(output.getDannoRca().getTerzeParti())) {
                output.getDannoRca().setTerzeParti(new ArrayList<>());
            }
            output.getDannoRca().getTerzeParti().addAll(aggregatore.getDannoRca().getTerzeParti());
            return output;
        }));
        if (insertResult) {
            return new BaseDTO<>();
        } else {
            throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA005", (String e) -> e.concat("Sezione Salvataggio Danni Terze Parti")));
        }

    }

    public BaseDTO salvaDannoRcaLegale(AnagraficaTerzePartiDTO input, Integer numeroSinistro) throws InternalMsaException {
        if (salvaSinistro(getSinistroDOByDTOAndFunction(input, numeroSinistro, LEGALE))) {
            return new BaseDTO<>();
        } else {
            throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA005", (String e) -> e.concat("Sezione Salvataggio Legale")));
        }
    }

    public BaseDTO salvaPerito(PeritoDTO input,Integer numSinistro) throws InternalMsaException {
        if(salvaSinistro(getSinistroDOByDTO(input,numSinistro))){
            return new BaseDTO<>();
        } else {
            throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA005", (String e) -> e.concat("Sezione Salvataggio Perito")));
        }
    }
}

