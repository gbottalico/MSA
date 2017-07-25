package msa.application.service.domain;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import msa.application.commons.Constants;
import msa.application.config.enumerator.MessageType;
import msa.application.dto.domain.*;
import msa.application.exceptions.InternalMsaException;
import msa.domain.object.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import msa.application.service.base.BaseService;
import msa.infrastructure.repository.DomainRepository;

@Service
public class DomainService extends BaseService {
    @Autowired
    DomainRepository domainRepository;

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
            List<NazioneDO> result = domainRepository.getListaNazioni(nome);
            return converter.convertList(result, NazioneDTO.class);

        } catch (Exception e) {
            throw new InternalMsaException(e, buildErrorMessageByText(MessageType.ERROR, Constants.DEFAULT_DOMAIN_ERROR_MESSAGE + "nazioni"));
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
            List<ProvinciaDO> result = domainRepository.getElencoProvince(idNazione, descProvincia);
            return converter.convertList(result, ProvinciaDTO.class);
        } catch (Exception e) {
            throw new InternalMsaException(e, buildErrorMessageByText(MessageType.ERROR, Constants.DEFAULT_DOMAIN_ERROR_MESSAGE + "provincie"));
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
    public List<ComuneDTO> getElencoComuni(Integer idNazione, Integer idProvincia, String desc) throws InternalMsaException {
        try {
            List<ComuneDO> result = domainRepository.getElencoComuni(idNazione, idProvincia, desc);
            return converter.convertList(result, ComuneDTO.class);
        } catch (Exception e) {
            throw new InternalMsaException(e, buildErrorMessageByText(MessageType.ERROR, Constants.DEFAULT_DOMAIN_ERROR_MESSAGE + "comuni"));
        }
    }

    /**
     * Utilizza il DomainRepository per ottenere la lista delle autorità
     *
     * @return una lista di oggetti AutoritaDTO
     * @throws InternalMsaException
     */
    public List<AutoritaDTO> getElencoAutorita() throws InternalMsaException {
        List<AutoritaDO> result = null;
        try {
            result = domainRepository.getElencoAutorita();
            return converter.convertList(result, AutoritaDTO.class);
        } catch (Exception e) {
            throw new InternalMsaException(e, buildErrorMessageByText(MessageType.ERROR, Constants.DEFAULT_DOMAIN_ERROR_MESSAGE + "autorità"));

        }

    }

    /**
     * Utilizza il DomainRepository per ottenere la lista delle compagnie la cui descrizione contiene
     * al suo interno la stringa passata come parametro
     *
     * @param desc la stringa da cercare
     * @return una lista di oggetti CompagniaDTO
     * @throws InternalMsaException
     */
    public List<CompagniaDTO> getElencoCompagnie(String desc) throws InternalMsaException {
        List<CompagniaDO> result = null;
        try {
            return converter.convertList(result, CompagniaDTO.class);

        } catch (Exception e) {
            throw new InternalMsaException(e, buildErrorMessageByText(MessageType.ERROR, Constants.DEFAULT_DOMAIN_ERROR_MESSAGE + "compagnie"));

        }
    }

    /**
     * Utilizza il DomainRepository per ottenere la listas dei mezzi di comunicazione
     *
     * @return una lista di oggetti MezzoDiComunicazioneDTO
     * @throws InternalMsaException
     */
    public List<MezzoComunicazioneDTO> getElencoMezziComunicazione() throws InternalMsaException {
        List<MezzoComunicazioneDO> result = null;
        try {
            result = domainRepository.getElencoMezziComunicazione();
            return converter.convertList(result, MezzoComunicazioneDTO.class);

        } catch (Exception e) {
            throw new InternalMsaException(e, buildErrorMessageByText(MessageType.ERROR, Constants.DEFAULT_DOMAIN_ERROR_MESSAGE + "mezzi di comunicazione"));

        }
    }

    /**
     * Utilizza il DomainRepository per ottenere la lista delle cause di rottura cristalli
     *
     * @return una lista di oggetti CausaRotturaCristalli
     * @throws InternalMsaException
     */
    public List<CausaRotturaCristalliDTO> getCauseRotturaCristalli() throws InternalMsaException {
        List<CausaRotturaCristalliDO> result = null;
        try {
            result = domainRepository.getElencoCauseRotturaCristalli();
            return converter.convertList(result, CausaRotturaCristalliDTO.class);
        } catch (Exception e) {
            throw new InternalMsaException(e, buildErrorMessageByText(MessageType.ERROR, Constants.DEFAULT_DOMAIN_ERROR_MESSAGE + "causa rottura cristalli"));

        }
    }

    /**
     * Utilizza il DomainRepository per ottenere la lista delle tipologie di veicoli
     *
     * @return
     * @throws InternalMsaException
     */
    public List<TipoVeicoloDTO> getElencoTipoVeicoli() throws InternalMsaException {
        List<TipoVeicoloDO> result = null;
        try {
            result = domainRepository.getElencoTipoVeicoli();
            return converter.convertList(result, TipoVeicoloDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalMsaException(e, buildErrorMessageByText(MessageType.ERROR, Constants.DEFAULT_DOMAIN_ERROR_MESSAGE + "tipologia veicolo "));
        }
    }

    /**
     * Utilizza il DomainRepository per ottenere la lista delle tipoologie targa
     *
     * @return
     * @throws InternalMsaException
     */
    public List<TipoTargaDTO> getElencoTipoTarghe() throws InternalMsaException {
        List<TipoTargaDO> result = null;
        try {
            result = domainRepository.getElencoTipoTarga();
            return converter.convertList(result, TipoTargaDTO.class);
        } catch (Exception e) {
            throw new InternalMsaException(e, buildErrorMessageByText(MessageType.ERROR, Constants.DEFAULT_DOMAIN_ERROR_MESSAGE + "tipologia targhe "));

        }
    }

    /**
     * Utilizza il DomainRepository per ottenere la lista delle regole presenti nella casa delle regole
     *
     * @return
     * @throws InternalMsaException
     */
    public List<CasaRegoleDTO> getElencoRegole() throws InternalMsaException {
        List<CasaRegoleDO> result = null;
        try {
            result = domainRepository.getElencoRegole();
            return converter.convertList(result, CasaRegoleDTO.class);
        } catch (Exception e) {
            throw new InternalMsaException(e, buildErrorMessageByText(MessageType.ERROR, Constants.DEFAULT_DOMAIN_ERROR_MESSAGE + "casa delle regole  "));
        }
    }
}
