package msa.application.service.domain;

import msa.application.config.enumerator.MessageType;
import msa.application.dto.domain.*;
import msa.application.dto.domain.baremes.BaremesDTO;
import msa.application.dto.sinistro.LuogoDTO;
import msa.application.exceptions.InternalMsaException;
import msa.application.service.base.BaseService;
import msa.domain.object.dominio.ComuneDO;
import msa.domain.object.dominio.ProvinciaDO;
import msa.domain.object.sinistro.LuogoDO;
import msa.infrastructure.costanti.MsaCostanti;
import msa.infrastructure.persistence.domain.ComuneDBO;
import msa.infrastructure.repository.DomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static msa.application.commons.Constants.ID_BAREMES_NON_CLASSIFICABILE;

@Service
public class DomainService extends BaseService {
    @Autowired
    private DomainRepository domainRepository;


    /**
     * Utilizza il DomainRepository per ottenere la lista delle nazioni che iniziano
     * per una data stringa
     *
     * @param nome la stringa da cercare
     * @return una lista di NazioneDTO
     * @throws InternalMsaException
     */
    public List<NazioneDTO> getElencoNazioni(String nome) throws InternalMsaException {

        try {
            return converter.convertList(domainRepository.getListaNazioni(nome), NazioneDTO.class);
        } catch (Exception e) {
            throw new InternalMsaException(e, getErrorMessagesByCodErrore(MessageType.ERROR, "MSA001"));
        }

    }

    /**
     * Utilizza il DomainRepository per ottenere la lista delle province che
     * appartengono ad una data nazione ed il cui nome inizia con la stringa data
     *
     * @param idNazione     l'id della nazione
     * @param descProvincia la stringa da cercare
     * @return una lista di oggetti ProvinciaDTO
     * @throws InternalMsaException
     */
    public List<ProvinciaDTO> getElencoProvince(Integer idNazione, String descProvincia) throws InternalMsaException {
        try {

            return converter.convertList(domainRepository.getElencoProvince(idNazione, descProvincia), ProvinciaDTO.class);
        } catch (Exception e) {
            throw new InternalMsaException(e, getErrorMessagesByCodErrore(MessageType.ERROR, "MSA001"));
        }

    }

    /**
     * Utilizza il DomainRepository per ottenere la lista dei comuni che appartengono ad una data nazione e provincia ed il cui nome inizia con la stringa data
     *
     * @param idNazione   id della nazione
     * @param idProvincia id della provincia
     * @param desc        la stringa da cercare
     * @return una lista di oggetti ComuneDTO
     * @throws InternalMsaException
     */
    public List<ComuneDTO> getElencoComuni(String idNazione, String idProvincia, String desc) throws InternalMsaException {
        try {
            return converter.convertList(domainRepository.getElencoComuni(idNazione, idProvincia, desc), ComuneDTO.class);
        } catch (Exception e) {
            throw new InternalMsaException(e, getErrorMessagesByCodErrore(MessageType.ERROR, "MSA001"));
        }
    }
    
    /**
     * Utilizza il DomainRepository per ottenere la lista dei comuni il cui nome inizia con la stringa data
     * 
     * @param desc
     * @return
     * @throws InternalMsaException
     */
    public List<LuogoDTO> getElencoComuni(String desc) throws InternalMsaException {
    	try {
    		final List<LuogoDO> comuni = domainRepository.getElencoComuni(desc).parallelStream().map(e -> {
    			final LuogoDO luogo = new LuogoDO();
    			luogo.setCodComune(e.getCodComune());
    			luogo.setDescrizioneComune(e.getDescrizione());
    			luogo.setCodProvincia(e.getCodProvincia());
    			luogo.setCodNazione(e.getCodNazione());
    			luogo.setDescrizioneNazione(MsaCostanti.NAZIONE_ITALIA); //TODO strinhe schiantate top
    			luogo.setCaps(e.getCap());
    			return luogo;
    		}).map(e -> {
    			e.setDescrizioneProvincia(domainRepository.getProvinciaById(e.getCodProvincia()).map(ProvinciaDO::getDescProvincia).orElse(null));
    			return e;
    		}).collect(Collectors.toList());
    		return converter.convertList(comuni, LuogoDTO.class);
    	} catch (Exception e) {
    		throw new InternalMsaException(e, getErrorMessagesByCodErrore(MessageType.ERROR, "MSA001"));
    	}
    }

    /**
     * Utilizza il DomainRepository per ottenere la lista delle autorità
     *
     * @return una lista di oggetti AutoritaDTO
     * @throws InternalMsaException
     */
    public List<AutoritaDTO> getElencoAutorita() throws InternalMsaException {

        try {

            return converter.convertList(domainRepository.getElencoAutorita(), AutoritaDTO.class);
        } catch (Exception e) {
            throw new InternalMsaException(e, getErrorMessagesByCodErrore(MessageType.ERROR, "MSA001"));

        }

    }

    /**
     * Utilizza il DomainRepository per ottenere la lista delle
     * la cui descrizione contiene
     * al suo interno la stringa passata come parametro
     *
     * @param desc la stringa da cercare
     * @return una lista di oggetti CompagniaDTO
     * @throws InternalMsaException
     */
    public List<CompagniaDTO> getElencoCompagnie(String desc) throws InternalMsaException {

        try {
            return converter.convertList(domainRepository.getElencoCompagnie(desc), CompagniaDTO.class);

        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalMsaException(e, getErrorMessagesByCodErrore(MessageType.ERROR, "MSA001"));

        }
    }

    /**
     * Utilizza il DomainRepository per ottenere la listas dei mezzi di comunicazione
     *
     * @return una lista di oggetti MezzoDiComunicazioneDTO
     * @throws InternalMsaException
     */
    public List<MezzoComunicazioneDTO> getElencoMezziComunicazione() throws InternalMsaException {
        try {
            return converter.convertList(domainRepository.getElencoMezziComunicazione(), MezzoComunicazioneDTO.class);

        } catch (Exception e) {
            throw new InternalMsaException(e, getErrorMessagesByCodErrore(MessageType.ERROR, "MSA001"));

        }
    }

    /**
     * Utilizza il DomainRepository per ottenere la lista delle cause di rottura cristalli
     *
     * @return una lista di oggetti CausaRotturaCristalli
     * @throws InternalMsaException
     */
    public List<CausaRotturaCristalliDTO> getCauseRotturaCristalli() throws InternalMsaException {

        try {
            return converter.convertList(domainRepository.getElencoCauseRotturaCristalli(), CausaRotturaCristalliDTO.class);
        } catch (Exception e) {
            throw new InternalMsaException(e, getErrorMessagesByCodErrore(MessageType.ERROR, "MSA001"));

        }
    }

    /**
     * Utilizza il DomainRepository per ottenere la lista delle tipologie di veicoli
     *
     * @return una lista di oggetti TipoVeicoloDTO
     * @throws InternalMsaException
     */
    public List<TipoVeicoloDTO> getElencoTipoVeicoli() throws InternalMsaException {

        try {
            return converter.convertList(domainRepository.getElencoTipoVeicoli(), TipoVeicoloDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalMsaException(e, getErrorMessagesByCodErrore(MessageType.ERROR, "MSA001"));
        }
    }

    /**
     * Utilizza il DomainRepository per ottenere la lista delle tipoologie targa
     *
     * @return una lista di oggetti TipoTargaDTO
     * @throws InternalMsaException
     */
    public List<TipoTargaDTO> getElencoTipoTarghe() throws InternalMsaException {
        try {

            return converter.convertList(domainRepository.getElencoTipoTarga(), TipoTargaDTO.class);
        } catch (Exception e) {
            throw new InternalMsaException(e, getErrorMessagesByCodErrore(MessageType.ERROR, "MSA001"));

        }
    }

    /**
     * Utilizza il DomainRepository per ottenere la lista delle regole presenti nella casa delle regole
     *
     * @return un elenco di oggetti CasaRegoleDTO
     * @throws InternalMsaException
     */
    public List<CasaRegoleDTO> getElencoRegole() throws InternalMsaException {

        try {

            return converter.convertList(domainRepository.getElencoRegole(), CasaRegoleDTO.class);
        } catch (Exception e) {
            throw new InternalMsaException(e, getErrorMessagesByCodErrore(MessageType.ERROR, "MSA001"));
        }
    }

    /**
     * Utilizza il DomainRepository per ottenere l'elenco di tutti i Baremes
     *
     * Filtraggio con l' obbiettivo di rimuovere "NESSUNA INDICAZIONE"
     * @return un elenco di oggetti BaremesDTO
     * @throws InternalMsaException
     */
    public List<BaremesDTO> getElencoBaremes() throws InternalMsaException {
        try {
            return converter.convertList(domainRepository.getElencoBaremes().stream().filter(e -> Integer.compare(e.getId(),ID_BAREMES_NON_CLASSIFICABILE) != 0).collect(Collectors.toList()), BaremesDTO.class);
        } catch (Exception e) {
            throw new InternalMsaException(e, getErrorMessagesByCodErrore(MessageType.ERROR, "MSA001"));

        }
    }

    /**
     * Utilizza il DomainRepository per ottenere l'elenco di tutti i ruoli
     *
     * @return un eleneco  di oggetti RuoliDTO
     * @throws InternalMsaException
     */
    public List<RuoliDTO> getElencoRuoli() throws InternalMsaException {
        try {
            return converter.convertList(domainRepository.getElencoRuoli(), RuoliDTO.class);
        } catch (Exception e) {
            throw new InternalMsaException(e, getErrorMessagesByCodErrore(MessageType.ERROR, "MSA001"));

        }
    }

    public List<ParticelleTopoDTO> getParticelleToponomastiche() throws InternalMsaException {
        try {
            return converter.convertList(domainRepository.getParticelleToponomastiche(), ParticelleTopoDTO.class);
        } catch (Exception e) {
            throw new InternalMsaException(e, getErrorMessagesByCodErrore(MessageType.ERROR, "MSA001"));
        }
    }

    public List<String> getDesLuogoById(String id, Character param) throws InternalMsaException {
        try {
            if (param.equals(MsaCostanti.PARAM_COMUNE)) {
                return domainRepository.getComuneById(id).map(e -> Collections.singletonList(e.getDescrizione())).orElse(null);

            } else if (param.equals(MsaCostanti.PARAM_NAZIONE)) {
                return domainRepository.getNazioneById(id).map(e -> Collections.singletonList(e.getDescrizione())).orElse(null);

            } else if (param.equals(MsaCostanti.PARAM_PROVINCIA)) {
                return domainRepository.getProvinciaById(id).map(e -> Collections.singletonList(e.getDescProvincia())).orElse(null);
            } else if (param.equals(MsaCostanti.PARAM_CAP)) {
                return domainRepository.getComuneById(id).map(ComuneDO::getCap).orElse(null);

            } else throw new InternalMsaException();
        } catch (Exception e) {
            throw new InternalMsaException(e, getErrorMessagesByCodErrore(MessageType.ERROR, "MSA001"));
        }
    }


}
