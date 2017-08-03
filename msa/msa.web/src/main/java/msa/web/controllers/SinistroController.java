package msa.web.controllers;

import io.swagger.annotations.ApiOperation;
import msa.application.config.BaseDTO;
import msa.application.dto.ricerca.InputRicercaDTO;
import msa.application.dto.sinistro.SinistroDTO;
import msa.application.dto.sinistro.cai.CaiDTO;
import msa.application.dto.sinistro.constatazioneAmichevole.ConstatazioneAmichevoleDTO;
import msa.application.dto.sinistro.dannoRca.DannoRcaDTO;
import msa.application.dto.sinistro.eventoRca.EventoRcaDTO;
import msa.application.dto.sinistro.segnalazione.SegnalazioneDTO;
import msa.application.exceptions.InternalMsaException;
import msa.application.service.sinistri.SinistriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public BaseDTO<SinistroDTO> apriSinistro(@RequestBody SinistroDTO input) throws InternalMsaException {
        return sinistriService.salvaSinistro(input);
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
    public BaseDTO<SinistroDTO> salvaSegnalazione(@RequestBody SegnalazioneDTO input, @PathVariable("numero") Integer numeroSinistro) throws InternalMsaException {
        return sinistriService.inviaSegnalazione(input, numeroSinistro);
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
    public BaseDTO salvaEventoRca(@RequestBody EventoRcaDTO input, @PathVariable("numero") Integer numeroSinistro) {
        return sinistriService.salvaEventoRca(input, numeroSinistro);
    }

    /**
     * Metodo che salva i dati della constatazione amichevole
     *
     * @param input
     * @return
     */
    @ApiOperation(value = "Metoto che salva i dati della constatazione amichevole")
    @RequestMapping(value = "/{numero}/CA", method = RequestMethod.POST)
    public BaseDTO salvaConstatazioneAmichevole(@RequestBody ConstatazioneAmichevoleDTO input, @PathVariable("numero") Integer numeroSinistro) throws InternalMsaException {
        return sinistriService.salvaConstatazioneAmichevole(input, numeroSinistro);
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
    public BaseDTO salvaCAI(@RequestBody CaiDTO input, @PathVariable("numero") Integer numeroSinistro) {
        return sinistriService.salvaCAI(input, numeroSinistro);

    }

    /**
     * Metodo che salva i danni riportati
     *
     * @param input
     * @param numeroSinistro
     * @return
     */
    @ApiOperation(value = "Metodo che salva i danni riportati")
    @RequestMapping(value = "/{numero}/dannoRCA", method = RequestMethod.POST)
    public BaseDTO salvaDanniRca(@RequestBody DannoRcaDTO input, @PathVariable("numero") Integer numeroSinistro) {
        return sinistriService.salvaDannoRca(input, numeroSinistro);
    }


}
