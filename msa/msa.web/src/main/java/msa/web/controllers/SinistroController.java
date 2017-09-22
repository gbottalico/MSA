package msa.web.controllers;

import io.swagger.annotations.ApiOperation;
import msa.application.config.BaseDTO;
import msa.application.dto.ricerca.InputRicercaDTO;
import msa.application.dto.sinistro.*;
import msa.application.dto.sinistro.anagrafica.FullAnagraficaControparteDTO;
import msa.application.dto.sinistro.rca.cai.CaiDTO;
import msa.application.dto.sinistro.rca.constatazioneAmichevole.ConstatazioneAmichevoleDTO;
import msa.application.dto.sinistro.rca.dannoRca.AnagraficaDanniDTO;
import msa.application.dto.sinistro.rca.dannoRca.DannoRcaDTO;
import msa.application.dto.sinistro.rca.eventoRca.EventoRcaDTO;
import msa.application.dto.sinistro.segnalazione.SegnalazioneDTO;
import msa.application.exceptions.InternalMsaException;
import msa.application.service.sinistri.SinistriService;
import msa.domain.object.enums.TipiSinisto;
import msa.web.api.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/sinistro")
public class SinistroController extends BaseController {

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
    public BaseDTO apriSinistro(@RequestBody BaseSinistroDTO input,
                                @RequestHeader(name = "user") final String userHeader) throws InternalMsaException {
        input.parseUserLogged(userHeader);
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
    public BaseDTO ricercaCopertura(@RequestBody InputRicercaDTO input,
                                    @RequestHeader(value = "user") final String userHeader) throws InternalMsaException {
        input.parseUserLogged(userHeader);
        return sinistriService.ricerca(input);
    }


    @ApiOperation(value = "Metodo che effettua la restituisce il sinistro provvisorio tramite il numSinistro")
    @RequestMapping(value = "/{numero}/get", method = RequestMethod.GET)
    public <T extends BaseSinistroDTO> BaseDTO<T> getSinistroProvvisorioByNumProvv(@PathVariable("numero") Integer numSinistro) throws InternalMsaException {
        return new BaseDTO<>(sinistriService.getSinistroByNumProvv(numSinistro));
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
    public BaseDTO salvaSegnalazione(@RequestBody SegnalazioneDTO input, @PathVariable("numero") Integer numeroSinistro) throws InternalMsaException {
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
    public BaseDTO salvaEventoRca(@RequestBody EventoRcaDTO input, @PathVariable("numero") Integer numeroSinistro) throws InternalMsaException {
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
    public BaseDTO<Map<String, String>> salvaCAI(@RequestBody CaiDTO input, @PathVariable("numero") Integer numeroSinistro) throws InternalMsaException {
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
    public BaseDTO salvaDanniRcaControparte(@RequestBody List<AnagraficaDanniDTO> input, @PathVariable("numero") Integer numeroSinistro) throws InternalMsaException {

        return sinistriService.salvaDannoRcaControparte(input, numeroSinistro);

    }

    @ApiOperation(value = "Metodo che salva i danni riportati dal conducente e l'anagrafica")
    @RequestMapping(value = "/{numero}/dannoRCA/conducente", method = RequestMethod.POST)
    public BaseDTO salvaDanniRcaConducente(@RequestBody DannoRcaDTO input, @PathVariable("numero") Integer numeroSinistro) throws InternalMsaException {

        return sinistriService.salvaDannoRcaConducente(input, numeroSinistro);

    }

    @ApiOperation(value = "Metodo che salva i dati delle terze parti")
    @RequestMapping(value = "/{numero}/dannoRCA/terzeParti", method = RequestMethod.POST)
    public BaseDTO salvaDanniRcaTerzeParti(@RequestBody List<FullAnagraficaControparteDTO> input, @PathVariable("numero") Integer numeroSinistro) throws InternalMsaException {
        return sinistriService.salvaDannoRcaTerzeParti(input, numeroSinistro);
    }

    @ApiOperation(value = "Metodo che salva il legale incaricato")
    @RequestMapping(value = "/{numero}/dannoRCA/legale", method = RequestMethod.POST)
    public BaseDTO salvaDanniRcaLegale(@RequestBody List<FullAnagraficaControparteDTO> input, @PathVariable("numero") Integer numeroSinistro) throws InternalMsaException {

        return sinistriService.salvaDannoRcaLegale(input, numeroSinistro);

    }

    @ApiOperation(value = "Metodo che mostra i sinistri e le partite danno")
    @RequestMapping(value = "/{numero}/PD", method = RequestMethod.GET)
    public BaseDTO getSinistriPartiteDanno(@PathVariable("numero") final Integer numSinistroProvv) throws InternalMsaException {
        return new BaseDTO<>(sinistriService.getSinistroPartiteDanno(numSinistroProvv));
    }

    @ApiOperation(value = "Metodo che mostra le partite danno da associare a terze parti o legale")
    @RequestMapping(value = "/{numero}/anagraficheAssociabili", method = RequestMethod.GET)
    public BaseDTO getAnagrafichePartiteDanno(@PathVariable("numero") final Integer numSinistroProvv) throws InternalMsaException {
        return new BaseDTO<>(sinistriService.getAnagrafichePartiteDanno(numSinistroProvv));
    }

    @ApiOperation(value = "Metodo che salva il perito incaricato")
    @RequestMapping(value = "/{numero}/perito", method = RequestMethod.POST)
    public BaseDTO salvaPerito(@RequestBody final PeritoDTO input, @PathVariable("numero") Integer numSinistro) throws InternalMsaException {
        return sinistriService.salvaPerito(input, numSinistro);
    }

    /**
     * Metodo che salva i dati di un sinistro di tipo furto o incendio
     *
     * @return un BaseDTO
     */
    @ApiOperation(value = "Metodo che salva i dati di un sinistro del tipo furto o incendio")
    @RequestMapping(value = "/{numero}/furtoIncendio", method = RequestMethod.POST)
    public BaseDTO salvaFurtoIncendio(@RequestBody final SinistroFurtoIncendioDTO input, @PathVariable("numero") Integer numSinistro) throws InternalMsaException {
        return sinistriService.inserisciFurtoIncendio(input, numSinistro);
    }

    /**
     * Metodo che salva i dati di un sinistro di tipo kasko
     */
    @ApiOperation(value = "Metodo che salva i dati di un sinistro di tipo kasko")
    @RequestMapping(value = "/{numero}/kasko", method = RequestMethod.POST)
    public BaseDTO salvaKasko(@RequestBody final SinistroKaskoDTO input, @PathVariable("numero") Integer numSinistro) throws InternalMsaException {
        return sinistriService.inserisciKasko(input, numSinistro);
    }

    /**
     * Metodo che salva i dati di un sinistro di tipo cristalli
     */
    @ApiOperation(value = "Metodo che salva i dati di un sinistro di tipo kasko")
    @RequestMapping(value = "/{numero}/cristalli", method = RequestMethod.POST)
    public BaseDTO salvaCristalli(@RequestBody final SinistroCristalliDTO input, @PathVariable("numero") Integer numSinistro) throws InternalMsaException {
        return sinistriService.inserisciCristalli(input, numSinistro);
    }

    @ApiOperation(value = "Metodo che salva i dati di un sinistro di tipo infortuni conducente")
    @RequestMapping(value = "/{numero}/infortuniConducente", method = RequestMethod.POST)
    public BaseDTO salvaInfortuniConducente(@RequestBody final SinistroInfortuniConducenteDTO input, @PathVariable("numero") Integer numSinistro) throws InternalMsaException {
        return sinistriService.inserisciInfortuniConducente(input, numSinistro);
    }

    @ApiOperation(value = "Metodo che restituisce l'elenco dei centri convenzionati in base all'indirizzo fornito")
    @RequestMapping(value = "/centri/{indirizzo}", method = RequestMethod.GET)
    public BaseDTO getElencoCentriConvenzionati(@PathVariable("indirizzo") String indirizzo) {
        return new BaseDTO(sinistriService.getElencoCentriConvenzionati(indirizzo));
    }

    @ApiOperation(value = "Metodo che effettua il salvataggio del centro convenzionato")
    @RequestMapping(value = "/{numero}/centroConvenzionato/", method = RequestMethod.POST)
    public BaseDTO salvaCentroConvenzionato(@RequestBody final CentroConvenzionatoDTO centro, @PathVariable("numero") Integer numSinistro) throws InternalMsaException {
        return sinistriService.salvaCentroConvenzionato(centro, numSinistro);
    }

    @ApiOperation(value=" Metodo che effettua il calcolo della colpa in base ai baremes ricevuti")
    @RequestMapping(value="/colpa/",method = RequestMethod.POST)
    public BaseDTO calcolaColpa(@RequestBody final CaiDTO input) throws InternalMsaException {
        return new BaseDTO(sinistriService.calcolaColpaBaremes(input));
    }

    @ApiOperation(value="Metodo che restituisce il perito in base all'indirizzo")
    @RequestMapping(value="/perito/", method = RequestMethod.POST)
    public BaseDTO getPerito(@RequestBody final String input){
    return new BaseDTO(sinistriService.getPerito(input));
    }



    @ApiOperation(value="Metodo che restituisce una polizza in base al numero polizza")
    @RequestMapping(value="/polizza/{numpoli}", method = RequestMethod.GET)
    public BaseDTO getPolizzaByNum(@PathVariable("numpoli") String numPoli) throws InternalMsaException {
        return new BaseDTO(sinistriService.getPolizzaByNumPoli(numPoli));
    }

    @ApiOperation(value="Metodo che restituisce il tipo del Sinistro provvisorio")
    @RequestMapping(value = "/{numero}/getTipoSinistro",  method = RequestMethod.GET)
    public BaseDTO<TipiSinisto> getTipoSinistro(@PathVariable(value = "numero") final Integer numSinistro) throws InternalMsaException {
        return new BaseDTO<>(sinistriService.getTipoSinistro(numSinistro));
    }
}
