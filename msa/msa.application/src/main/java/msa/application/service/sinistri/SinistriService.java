package msa.application.service.sinistri;

import msa.application.config.BaseDTO;
import msa.application.dto.sinistro.aperturaSinistro.InputAperturaSinistroDTO;
import msa.application.dto.sinistro.cai.InputCaiDTO;
import msa.application.dto.sinistro.constatazioneAmichevole.InputConstatazioneAmichevoleDTO;
import msa.application.dto.sinistro.dannoRca.InputDannoRcaDTO;
import msa.application.dto.sinistro.eventoRca.InputEventoDTO;
import msa.application.dto.ricerca.InputRicercaDTO;
import msa.application.dto.sinistro.segnalazione.InputSegnalazioneDTO;
import msa.infrastructure.repository.SinistriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SinistriService {
    @Autowired
    SinistriRepository sinistriRepository;

    /**
     * Metodo che effettua la ricerca le coperture in base ai parametri passati in input
     *
     * @param input un oggetto di tipo InputRicercaDTO che contiene le informazioni con cui effettuare la ricerca
     * @return
     */
    public BaseDTO ricercaCopertura(InputRicercaDTO input) {
        return null;
    }

    /**
     * Metodo che stacca il numero di un nuovo sinistro provvisorio
     *
     * @param input
     * @return
     */
    public BaseDTO apriSinistro(InputAperturaSinistroDTO input) {
        return null;
    }

    /**
     * Metodo che salva i dati di segnalazione sinistro
     *
     * @return
     */
    public BaseDTO inviaSegnalazione(InputSegnalazioneDTO input) {
        return null;
    }

    /**
     * Metodo che salva i dati dell'evento RCA
     *
     * @param input
     * @return
     */
    public BaseDTO salvaEventoRca(InputEventoDTO input) {
        return null;
    }

    /**
     * Metodo che salva i dati della constatazione amichevole nel caso in cui i veicoli coinvolti siano più di 2
     *
     * @return
     */
    public BaseDTO salvaConstatazioneAmichevole(InputConstatazioneAmichevoleDTO input) {
        return null;
    }

    /**
     * Metodo che calcola la responsabilità in base ai baremes inseriti
     *
     * @param input
     * @return
     */
    public BaseDTO salvaCAI(InputCaiDTO input) {
        return null;
    }

    /**
     * Metodo che salva i danni riportati
     *
     * @param input
     * @return
     */
    public BaseDTO salvaDannoRca(InputDannoRcaDTO input) {
        return null;
    }

}

