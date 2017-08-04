package msa.web.controllers;

import io.swagger.annotations.ApiOperation;
import msa.application.config.BaseDTO;
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
import msa.application.service.sinistri.SinistriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public BaseDTO<SinistroDTO> salvaEventoRca(@RequestBody EventoRcaDTO input, @PathVariable("numero") Integer numeroSinistro) throws InternalMsaException {
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
    public BaseDTO<SinistroDTO> salvaConstatazioneAmichevole(@RequestBody ConstatazioneAmichevoleDTO input, @PathVariable("numero") Integer numeroSinistro) throws InternalMsaException {
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
    public BaseDTO<SinistroDTO> salvaCAI(@RequestBody CaiDTO input, @PathVariable("numero") Integer numeroSinistro) throws InternalMsaException {
        return sinistriService.salvaCAI(input, numeroSinistro);

    }

    /**
     * Metodo che salva i danni riportati
     *
     * @param input
     * @param numeroSinistro
     * @return
     */
   /* @ApiOperation(value = "Metodo che salva i danni riportati")
    @RequestMapping(value = "/{numero}/dannoRCA", method = RequestMethod.POST)
    public BaseDTO salvaDanniRca(@RequestBody DannoRcaDTO input, @PathVariable("numero") Integer numeroSinistro) throws InternalMsaException {
        return sinistriService.salvaDannoRca(input, numeroSinistro);
    }*/
    @ApiOperation(value = "Metodo che salva i danni riportati dalla controparte e l'anagrafica")
    @RequestMapping(value = "/{numero}/dannoRCA/controparte", method = RequestMethod.POST)
    public BaseDTO salvaDanniRcaControparte(@RequestBody AnagraficaDanniDTO input, @PathVariable("numero") Integer numeroSinistro) throws InternalMsaException {

        return sinistriService.salvaDannoRcaControparte(input, numeroSinistro);

    }

    @ApiOperation(value = "Metodo che salva i danni riportati dal conducente e l'anagrafica")
    @RequestMapping(value = "/{numero}/dannoRCA/conducente", method = RequestMethod.POST)
    public BaseDTO<SinistroDTO> salvaDanniRcaConducente(@RequestBody DannoRcaDTO input, @PathVariable("numero") Integer numeroSinistro) throws InternalMsaException {

        return sinistriService.salvaDannoRcaConducente(input, numeroSinistro);

    }
    @ApiOperation(value = "Metodo che salva i dati delle terze parti")
    @RequestMapping(value = "/{numero}/dannoRCA/terzeParti", method = RequestMethod.POST)
    public BaseDTO<SinistroDTO> salvaDanniRcaTerzeParti(@RequestBody List<AnagraficaTerzePartiDTO> input, @PathVariable("numero") Integer numeroSinistro) throws InternalMsaException {
        return sinistriService.salvaDannoRcaTerzeParti(input, numeroSinistro);
    }

    @ApiOperation(value = "Metodo che salva i danni riportati dal conducente e l'anagrafica")
    @RequestMapping(value = "/{numero}/dannoRCA/legale", method = RequestMethod.POST)
    public BaseDTO<SinistroDTO> salvaDanniRcaLegale(@RequestBody AnagraficaTerzePartiDTO input, @PathVariable("numero") Integer numeroSinistro) throws InternalMsaException {

        return sinistriService.salvaDannoRcaLegale(input, numeroSinistro);

    }
}
