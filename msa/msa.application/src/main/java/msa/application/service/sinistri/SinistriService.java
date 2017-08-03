package msa.application.service.sinistri;

import msa.application.commons.functions.FunctionUtils;
import msa.application.config.BaseDTO;
import msa.application.config.enumerator.MessageType;
import msa.application.dto.ricerca.InputRicercaDTO;
import msa.application.dto.sinistro.SinistroDTO;
import msa.application.dto.sinistro.anagrafica.AnagraficaTerzePartiDTO;
import msa.application.dto.sinistro.cai.CaiDTO;
import msa.application.dto.sinistro.constatazioneAmichevole.ConstatazioneAmichevoleDTO;
import msa.application.dto.sinistro.dannoRca.AnagraficaDanniDTO;
import msa.application.dto.sinistro.dannoRca.DannoRcaDTO;
import msa.application.dto.sinistro.eventoRca.EventoRcaDTO;
import msa.application.dto.sinistro.segnalazione.SegnalazioneDTO;
import msa.application.exceptions.InternalMsaException;
import msa.domain.Converter.MsaConverter;
import msa.domain.object.sinistro.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SinistriService extends BaseSinistroService {


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
        return new BaseDTO<>(converter.convertList(sinistriRepository.getElencoSinistriProvvisori(inputRicercaDO), SinistroDTO.class));
    }

    /**
     * Metodo esposto per l' inserimento del sinistro alla prima fase
     *
     * @param input
     * @return
     * @throws InternalMsaException
     */
    public BaseDTO<SinistroDTO> salvaSinistro(SinistroDTO input) throws InternalMsaException {
        return this.salvaSinistro(converter.convertObject(input, SinistroDO.class));
    }

    /**
     * Metodo centralizzato per salvataggio o modifica sinistro
     *
     * @param input
     * @return
     */
    private BaseDTO<SinistroDTO> salvaSinistro(SinistroDO input) throws InternalMsaException {

        SinistroDO sinistroByNumProvv;
        if (input.getNumSinistroProvv() == null) {
            try {
                sinistroByNumProvv = sinistriRepository.insertSinistroProvvisorio(input);
            } catch (Exception e) {
                throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA004"));
            }
        } else {
            try {
                sinistroByNumProvv = sinistriRepository.updateSinistroProvvisorio(input);
            } catch (Exception e) {
                throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA005"));
            }
        }
        SinistroDTO sinistroDTO = converter.convertObject(sinistroByNumProvv, SinistroDTO.class);
        return new BaseDTO<>(sinistroDTO);

    }

    /**
     * Metodo che salva i dati di segnalazione sinistro
     *
     * @param input
     * @param numSinistroProvv
     * @return
     */

    public BaseDTO<SinistroDTO> inviaSegnalazione(SegnalazioneDTO input, Integer numSinistroProvv) throws InternalMsaException {
        final SinistroDO sinistroDOByDTO = getSinistroDOByDTO(input, numSinistroProvv);
        return salvaSinistro(sinistroDOByDTO);
    }

    /**
     * Metodo che salva i dati dell'evento RCA
     *
     * @param input
     * @param numSinistroProvv
     * @return
     */
    public BaseDTO<SinistroDTO> salvaEventoRca(EventoRcaDTO input, Integer numSinistroProvv) throws InternalMsaException {

        final SinistroDO sinistroDOByDTO = getSinistroDOByDTO(input, numSinistroProvv);
        return salvaSinistro(sinistroDOByDTO);
    }

    /**
     * Metodo che salva i dati della constatazione amichevole nel caso in cui i veicoli coinvolti siano più di 2
     *
     * @param input
     * @param numSinistroProvv
     * @return
     */
    public BaseDTO<SinistroDTO> salvaConstatazioneAmichevole(ConstatazioneAmichevoleDTO input, Integer numSinistroProvv) throws InternalMsaException {
        return salvaSinistro(getSinistroDOByDTO(input, numSinistroProvv));
    }

    /**
     * Metodo che calcola la responsabilità in base ai baremes inseriti
     *
     * @param input
     * @param numSInistroProvv
     * @return
     */
    public BaseDTO salvaCAI(CaiDTO input, Integer numSInistroProvv) throws InternalMsaException {
        SinistroDO sinistroDOByDTO = getSinistroDOByDTO(input, numSInistroProvv);
        //Inserire logica baremes
        return salvaSinistro(sinistroDOByDTO);
    }

    /**
     * Metodo che salva i danni riportati
     *
     * @param input
     * @param numSinistroProvv
     * @return
     */
    public BaseDTO<SinistroDTO> salvaDannoRca(DannoRcaDTO input, Integer numSinistroProvv) throws InternalMsaException {
        final SinistroDO sinistroDOByDTO = getSinistroDOByDTO(input, numSinistroProvv);
        return salvaSinistro(sinistroDOByDTO);
    }

    public BaseDTO<SinistroDTO> salvaDannoRcaConducente(DannoRcaDTO input, Integer numSinistro) throws InternalMsaException {

        SinistroDO sinistroDOByDTO = getSinistroDOByDTO(input, numSinistro);
        return salvaSinistro(sinistroDOByDTO);


    }

    public BaseDTO<SinistroDTO> salvaDannoRcaControparte(AnagraficaDanniDTO input, Integer numSinistro) throws InternalMsaException {

        SinistroDO sinistroDOByDTO = getSinistroDOByDTO(input, numSinistro);
        return salvaSinistro(sinistroDOByDTO);
    }

    public BaseDTO<SinistroDTO> salvaDannoRcaTerzeParti(List<AnagraficaTerzePartiDTO> input, Integer numSinistro) throws InternalMsaException {

        return salvaSinistro(getSinistroDOByDTO(input, numSinistro));
    }
}

