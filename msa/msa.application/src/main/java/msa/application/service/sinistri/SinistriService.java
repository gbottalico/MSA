package msa.application.service.sinistri;

import msa.application.commons.functions.FunctionUtils;
import msa.application.config.BaseDTO;
import msa.application.config.enumerator.MessageType;
import msa.application.dto.ricerca.InputRicercaDTO;
import msa.application.dto.sinistro.SinistroDTO;
import msa.application.dto.sinistro.cai.CaiDTO;
import msa.application.dto.sinistro.constatazioneAmichevole.ConstatazioneAmichevoleDTO;
import msa.application.dto.sinistro.dannoRca.DannoRcaDTO;
import msa.application.dto.sinistro.eventoRca.EventoRcaDTO;
import msa.application.dto.sinistro.segnalazione.SegnalazioneDTO;
import msa.application.exceptions.InternalMsaException;
import msa.application.service.base.BaseService;
import msa.domain.object.sinistro.InputRicercaDO;
import msa.domain.object.sinistro.SinistroDO;
import msa.infrastructure.repository.SinistriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SinistriService extends BaseService {
    @Autowired
    private SinistriRepository sinistriRepository;

    /**
     * Metodo che effettua la ricerca le coperture in base ai parametri passati in input
     *
     * @param input un oggetto di tipo InputRicercaDTO che contiene le informazioni con cui effettuare la ricerca
     * @return
     */
    public BaseDTO ricercaCopertura(InputRicercaDTO input) throws InternalMsaException {
        if (FunctionUtils.checkIsNotNull(input.getCompagnia(), input.getDataEvento())) {
            throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA003"));
        }
        InputRicercaDO inputRicercaDO = converter.convertObject(input, InputRicercaDO.class);
        return new BaseDTO<>(converter.convertList(sinistriRepository.getElencoSinistriProvvisori(inputRicercaDO), SinistroDTO.class));
    }


    private Integer getMaxNumSinistroProvv() {
        return sinistriRepository.getNextNumSinistroProvv();
    }

    /**
     * Metodo che stacca il numero di un nuovo sinistro provvisorio
     *
     * @param input
     * @return
     */
    public BaseDTO<SinistroDTO> apriSinistro(SinistroDTO input) throws InternalMsaException {


        Integer numProvv = sinistriRepository.insertSinistroProvvisorio(converter.convertObject(input, SinistroDO.class));
        // facciamo la get per verificare che lo abbia inserito
        SinistroDO sinistroByNumProvv = sinistriRepository.getSinistroByNumProvv(numProvv);
        if (sinistroByNumProvv != null) {
            SinistroDTO sinistroDTO = converter.convertObject(sinistroByNumProvv, SinistroDTO.class);
            return new BaseDTO<>(sinistroDTO);
        } else {
            throw new InternalMsaException(getErrorMessagesByCodErrore(MessageType.ERROR, "MSA004"));
        }


    }

    /**
     * Metodo che salva i dati di segnalazione sinistro
     *
     * @return
     * @param input
     * @param numSinistroProvv
     */

    public BaseDTO inviaSegnalazione(SegnalazioneDTO input, Integer numSinistroProvv) {
        return null;
    }

    /**
     * Metodo che salva i dati dell'evento RCA
     *
     * @param input
     * @param numSinistroProvv
     * @return
     */
    public BaseDTO salvaEventoRca(EventoRcaDTO input, Integer numSinistroProvv) {
        return null;
    }

    /**
     * Metodo che salva i dati della constatazione amichevole nel caso in cui i veicoli coinvolti siano più di 2
     *
     * @return
     * @param input
     * @param numSinistroProvv
     */
    public BaseDTO salvaConstatazioneAmichevole(ConstatazioneAmichevoleDTO input, Integer numSinistroProvv) {
        return null;
    }

    /**
     * Metodo che calcola la responsabilità in base ai baremes inseriti
     *
     * @param input
     * @param numSInistroProvv
     * @return
     */
    public BaseDTO salvaCAI(CaiDTO input, Integer numSInistroProvv) {
        return null;
    }

    /**
     * Metodo che salva i danni riportati
     *
     * @param input
     * @param numSinistroProvv
     * @return
     */
    public BaseDTO salvaDannoRca(DannoRcaDTO input, Integer numSinistroProvv) {
        return null;
    }

    public SinistroDTO getSinistroByNumProvvisorio(Integer numProvvisorio) {
        return converter.convertObject(sinistriRepository.getSinistroByNumProvv(numProvvisorio), SinistroDTO.class);
    }

}

