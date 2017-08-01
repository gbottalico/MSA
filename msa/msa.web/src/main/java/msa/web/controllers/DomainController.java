package msa.web.controllers;

import java.lang.reflect.InvocationTargetException;
import java.util.List;


import io.swagger.annotations.Api;
import msa.application.dto.domain.*;
import msa.application.dto.domain.baremes.BaremesDTO;
import msa.application.exceptions.InternalMsaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import msa.application.config.BaseDTO;
import msa.application.service.domain.DomainService;
import msa.web.api.BaseController;

@RestController
@RequestMapping(value = "/api/dominio")
public class DomainController extends BaseController {
    @Autowired
    DomainService domainService;

    /**
     * Ottiene la lista delle nazioni il cui nome inizia con la stringa passata nel
     * path
     *
     * @param name la stinga da cercare
     * @return un BaseDTO contente le informazioni richieste
     * @throws InternalMsaException
     */
    @ApiOperation(value = "Metodo che recupera l'elenco delle nazioni che matchano il nome passato come parametro")
    @RequestMapping(value = "/nazione/{name}", method = RequestMethod.GET)
    public BaseDTO<List<NazioneDTO>> getNazioni(@PathVariable("name") String name)
            throws InternalMsaException {

        BaseDTO<List<NazioneDTO>> result = new BaseDTO<List<NazioneDTO>>();
        result.setResult(domainService.getElencoNazioni(name));
        return result;
    }

    /**
     * Ottiene la lista delle province che iniziano con la stringa passata e che
     * appartengono alla nazione di cui viene passato l'id come parametro
     *
     * @param idNazione l'id della nazione di cui cercare le province, descProvincia la
     *                  stringa da cercare
     * @return un BaseDTO contenente le informazioni richieste
     * @throws InternalMsaException
     */

    @ApiOperation(value = "Metodo che recupera l'elenco delle province in base all'ID della nazione passato come parametro e la descrizione")
    @RequestMapping(value = "/provincia/{nazione}/{descProvincia}", method = RequestMethod.GET)
    public BaseDTO<List<ProvinciaDTO>> getProvinceByCodNazione(@PathVariable("nazione") Integer idNazione,
                                                               @PathVariable("descProvincia") String descProvincia)
            throws InternalMsaException {
        BaseDTO<List<ProvinciaDTO>> result = new BaseDTO<List<ProvinciaDTO>>();
        result.setResult(domainService.getElencoProvince(idNazione, descProvincia));
        return result;
    }

    /**
     * Ottiene la lista dei comuni che appartengono ad una data nazione e provincia e il cui nome inizia con la stringa passata
     *
     * @param idNazione   l'id della nazione
     * @param idProvincia l'id della provincia
     * @param desComune   la stringa da cercare
     * @return un BaseDTO contenente le informazioni richieste
     * @throws InternalMsaException
     */
    @ApiOperation(value = "Metodo che recupera l'elenco dei comuni in base all'id della nazione, id della provincia e la descrizione")
    @RequestMapping(value = "/comune/{nazione}/{provincia}/{desComune}", method = RequestMethod.GET)
    public BaseDTO<List<ComuneDTO>> getElencoComuni(@PathVariable("nazione") Integer idNazione,
                                                    @PathVariable("provincia") Integer idProvincia, @PathVariable("desComune") String desComune)
            throws InternalMsaException {
        BaseDTO<List<ComuneDTO>> result = new BaseDTO<List<ComuneDTO>>();
        result.setResult(domainService.getElencoComuni(idNazione, idProvincia, desComune));
        return result;

    }

    /**
     * Ottiene la lista delle autorità
     *
     * @return un BaseDTO contenente le informazioni richieste
     * @throws InternalMsaException
     */
    @ApiOperation(value = "Metodo che ottiene l'elenco di tutte le autorità")
    @RequestMapping(value = "/autorita", method = RequestMethod.GET)
    public BaseDTO<List<AutoritaDTO>> getElencoAutorita() throws InternalMsaException {
        BaseDTO<List<AutoritaDTO>> result = new BaseDTO<>();
        result.setResult(domainService.getElencoAutorita());

        return result;

    }

    /**
     * Ottiene la lista delle compagnie che contengono nella descrizione la stringa passata come parametro
     *
     * @param desc la stringa da cercare
     * @return un BaseDTO contente le informazioni richieste
     * @throws InternalMsaException
     */
    @ApiOperation(value = "Metodo che ottiene l'elenco di tutte le compagnie in base alla descrizione")
    @RequestMapping(value = "/compagnia/{desc}", method = RequestMethod.GET)
    public BaseDTO<List<CompagniaDTO>> getElencoCompagnie(@PathVariable("desc") String desc) throws InternalMsaException {
        BaseDTO<List<CompagniaDTO>> result = new BaseDTO<>();
        result.setResult(domainService.getElencoCompagnie(desc));

        return result;
    }

    /**
     * Ottiene la lista di tutti i mezzi di comunicazione presenti nel database
     *
     * @return un BaseDTO contente le informazioni richieste
     * @throws InternalMsaException
     */
    @ApiOperation(value = " Metodo che ottiene l'elenco di tutti i mezzi di comunicazione")
    @RequestMapping(value = "/mezzicomunicazione", method = RequestMethod.GET)
    public BaseDTO<List<MezzoComunicazioneDTO>> getElencoMezziComunicazione() throws InternalMsaException {
        BaseDTO<List<MezzoComunicazioneDTO>> result = new BaseDTO<>();
        result.setResult(domainService.getElencoMezziComunicazione());
        return result;
    }

    /**
     * Ottiene la lista di tutte le cause di rottura cristalli
     *
     * @return un BaseDTO contente le informazioni richieste
     * @throws InternalMsaException
     */
    @ApiOperation(value = " Metodo che ottiene l'elenco di tutte le cause di rottura cristalli")
    @RequestMapping(value = "/causerotturacristalli", method = RequestMethod.GET)
    public BaseDTO<List<CausaRotturaCristalliDTO>> getElencoCauseRotturaCristalli() throws InternalMsaException {
        BaseDTO<List<CausaRotturaCristalliDTO>> result = new BaseDTO<>();
        result.setResult(domainService.getCauseRotturaCristalli());
        return result;
    }

    /**
     * Ottiene la lista di tutte le tipologie di veicoli
     *
     * @return un BaseDTO contente le informazioni richiete
     * @throws InternalMsaException
     */
    @ApiOperation(value = " Metodo che ottiene la l'elenco di tutte le tipologie di veicoli")
    @RequestMapping(value = "/tipoveicoli")
    public BaseDTO<List<TipoVeicoloDTO>> getElencoTipologieVeicoli() throws InternalMsaException {
        BaseDTO<List<TipoVeicoloDTO>> result = new BaseDTO<>();
        result.setResult(domainService.getElencoTipoVeicoli());
        return result;
    }

    /**
     * Ottiene la lista di tutte le tipologie di targhe
     *
     * @return
     * @throws InternalMsaException
     */
    @ApiOperation(value = "Metodo che ottiene l'elenco di tutte le tipologie targa")
    @RequestMapping(value = "/tipotarghe", method = RequestMethod.GET)
    public BaseDTO<List<TipoTargaDTO>> getElencoTipologieTarghe() throws InternalMsaException {
        BaseDTO<List<TipoTargaDTO>> result = new BaseDTO<>();
        result.setResult(domainService.getElencoTipoTarghe());
        return result;
    }

    /**
     * Ottiene la lista delle regole presenti nella casa delle regole
     *
     * @return un BaseDTO contente le informazioni richieste
     * @throws InternalMsaException
     */
    @ApiOperation(value = "Metodo che ottiene l'elenco di tutte le regole presenti nella casa delle regole")
    @RequestMapping(value = "/casaregole", method = RequestMethod.GET)
    public BaseDTO<List<CasaRegoleDTO>> getElencoRegole() throws InternalMsaException {
        BaseDTO<List<CasaRegoleDTO>> result = new BaseDTO<>();
        result.setResult(domainService.getElencoRegole());
        return result;
    }

    /**
     * Metodo che ottiene la lista di tutti i baremes
     *
     * @return un BaseDTO contente le informazioni richieste
     * @throws InternalMsaException
     */
    @ApiOperation(value = " Metodo che ottiene la lista di tutti i baremes")
    @RequestMapping(value = "/baremes", method = RequestMethod.GET)
    public BaseDTO<List<BaremesDTO>> getElencoBaremes() throws InternalMsaException {
        BaseDTO<List<BaremesDTO>> result = new BaseDTO<>();
        result.setResult(domainService.getElencoBaremes());
        return result;
    }

    /**
     * Metodo che ottiene la lista di tutti i ruoli
     * @return un BaseDTO contente le informazioni richieste
     * @throws InternalMsaException
     */
    @ApiOperation(value = " Metodo chef ottiene la lista di tutti i ruoli")
    @RequestMapping(value = "/ruoli", method = RequestMethod.GET)
    public BaseDTO<List<RuoliDTO>> getElencoRuoli() throws InternalMsaException {
        BaseDTO<List<RuoliDTO>> result = new BaseDTO<>();
        result.setResult(domainService.getElencoRuoli());
        return result;
    }
}




