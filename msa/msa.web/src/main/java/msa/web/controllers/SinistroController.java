package msa.web.controllers;

import io.swagger.annotations.ApiOperation;
import msa.application.config.BaseDTO;
import msa.application.dto.ricerca.InputRicercaDTO;
import msa.application.dto.sinistro.aperturaSinistro.InputAperturaSinistroDTO;
import msa.application.dto.sinistro.cai.InputCaiDTO;
import msa.application.dto.sinistro.constatazioneAmichevole.InputConstatazioneAmichevoleDTO;
import msa.application.dto.sinistro.dannoRca.DanniDTO;
import msa.application.dto.sinistro.dannoRca.InputDannoRcaDTO;
import msa.application.dto.sinistro.eventoRca.InputEventoDTO;
import msa.application.dto.sinistro.segnalazione.InputSegnalazioneDTO;
import msa.application.exceptions.InternalMsaException;
import msa.application.service.sinistri.SinistriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/sinistro")
public class SinistroController {

    @Autowired
   private SinistriService sinistriService;

    /**
     * Metodo che effettua l'apertura di un sinistro in base ai dati ricevuti in input
     *
     * @param input
     * @return
     */
    @ApiOperation(value = "Metodo che effettua l'apertura di un sinistro in base ai dati ricevuti in input")
    @RequestMapping(value = "/apertura", method = RequestMethod.PUT)
    public BaseDTO apriSinistro(InputAperturaSinistroDTO input) {
        return sinistriService.apriSinistro(input);
    }

    /**
     * Metodo che effettua la ricerca di una copertura in base ai parametri ricevuti in input
     *
     * @param input
     * @return
     */
    @ApiOperation(value = "Metodo che effettua la ricerca di una copertura in base ai dati ricevuti in input")
    @RequestMapping(value = "/ricerca", method = RequestMethod.POST)
    public BaseDTO ricercaCopertura(@RequestBody InputRicercaDTO input) throws InternalMsaException {
        return sinistriService.ricercaCopertura(input);
    }

    /**
     * Metodo che salva i dati di segnalazione del sinistro
     *
     * @param input
     * @param numeroSinistro
     * @return
     */
    @ApiOperation(value = "Metodo che effettua l'inserimento di una segnalazione ")
    @RequestMapping(value = "/{numero}/segnalazione", method = RequestMethod.POST)
    public BaseDTO salvaSegnalazione(InputSegnalazioneDTO input, @PathVariable("numero") String numeroSinistro) {
        return sinistriService.inviaSegnalazione(input);
    }

    /**
     * Metodo che salva i dati all'interno dell'evento RCA
     *
     * @param input
     * @param numeroSinistro
     * @return
     */
    @ApiOperation(value = " Metodo che salva i dati dell'evento RCA")
    @RequestMapping(value = "/{numero}/RCA", method = RequestMethod.POST)
    public BaseDTO salvaEventoRca(InputEventoDTO input, @PathVariable("numero") String numeroSinistro) {
        return sinistriService.salvaEventoRca(input);
    }

    /**
     * Metodo che salva i dati della constatazione amichevole
     *
     * @param input
     * @return
     */
    @ApiOperation(value = "Metoto che salva i dati della constatazione amichevole")
    @RequestMapping(value = "/{numero/constatazioneAmichevole", method = RequestMethod.POST)
    public BaseDTO salvaConstatazioneAmichevole(InputConstatazioneAmichevoleDTO input) {
        return sinistriService.salvaConstatazioneAmichevole(input);
    }

    /**
     * Metodo che calcola la responsabilità in base ai baremes e salva i dati
     *
     * @param input
     * @param numeroSinistro
     * @return
     */
    @ApiOperation(value = "Metodo che calcola la responsabilità e salva il CAI in base ai baremes inseriti")
    @RequestMapping(value = "/{numero}/CAI", method = RequestMethod.POST)
    public BaseDTO salvaCAI(InputCaiDTO input, @PathVariable("numero") String numeroSinistro) {
        return sinistriService.salvaCAI(input);

    }

    /**
     * Metodo che salva i danni riportati
     * @param input
     * @param numeroSinistro
     * @return
     */
    @ApiOperation(value = "Metodo che salva i danni riportati")
    @RequestMapping(value = "/{numero}/dannoRCA", method = RequestMethod.POST)
    public BaseDTO salvaDanniRca(InputDannoRcaDTO input, @PathVariable("numero") String numeroSinistro) {
        return sinistriService.salvaDannoRca(input);
    }

/*    //TODO rimuoverlo
    @RequestMapping(value = "/testdanni", method =RequestMethod.GET)
    public Integer testDanni(){

        //return sinistriService.getMaxNumSinistroProvv();
    }*/



}
