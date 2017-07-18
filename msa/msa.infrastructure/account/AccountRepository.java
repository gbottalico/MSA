package nfc.infrastructure.persistence.account;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.AccountException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import nfc.domain.model.funzionalitaNfc.NfcFunzioneCustomDO;
import nfc.domain.model.usernodo.NodoDefaultDO;
import nfc.domain.shared.mapper.IModelMapper;
import nfc.infrastructure.config.AbstractNfcPropertiesReader;
import nfc.infrastructure.exceptions.DBException;
import nfc.infrastructure.persistence.BaseRepository;
import nfc.infrastructure.persistence.datamodel.NfcFunzioneCustom;
import nfc.infrastructure.persistence.datamodel.UserNodoDefault;
import nfc.infrastructure.persistence.datamodel.UserNodoDefaultExample;
import nfc.infrastructure.persistence.map.NfcFunzioneMapper;
import nfc.infrastructure.persistence.map.NfcProfiloFunzioneMapper;
import nfc.infrastructure.persistence.map.UserNodoDefaultMapper;

@Repository
public class AccountRepository extends BaseRepository {

	@Autowired
	UserNodoDefaultMapper usernodo;

	@Autowired
	NfcProfiloFunzioneMapper nfcProfiloFunzioneMapper;

	@Autowired
	NfcFunzioneMapper nfcFunzioneMapper;

	public AccountRepository() {
		super();
	}

	public AccountRepository(IModelMapper mapper, JdbcTemplate jdbcTemplate, AbstractNfcPropertiesReader propertiesReader) {
		super(mapper, jdbcTemplate, propertiesReader);
	}

	/**
	 * Metodo che recupera il nodo di default dell'utente connesso
	 * 
	 * @author leonardo.amodio
	 * @param username
	 * @return userNodoDO
	 * @throws AccountException
	 */
	public NodoDefaultDO getNodoDefault(String username) throws DBException {
		UserNodoDefaultExample userNodoCriteria = new UserNodoDefaultExample();
		NodoDefaultDO userNodoDO = new NodoDefaultDO();
		try {
			logger.debug("Costruisco il DBO con i risultati di una SELECT condizionata per username\n");
			userNodoCriteria.setOrderByClause("COD_USER");
			UserNodoDefault userNodoDBO = usernodo.selectByPrimaryKey(username);
			userNodoDO = mapper.map(userNodoDBO, NodoDefaultDO.class);
		} catch (Exception e) {
			logger.error("Errore durante il recupero delle abilitazioni dell'utente: ", e);
			throw new DBException();
		}
		return userNodoDO;
	}

	/**
	 * Metodo per il salvataggio nella USER_NODO_DEFAULT di un nuovo nodo di default
	 * 
	 * @author leonardo.amodio
	 * @param nodoDefaultDO
	 * @return
	 * @throws DBException
	 */
	public void salvaNodoDefault(NodoDefaultDO nodoDefaultDO) throws DBException {
		try {
			logger.debug("Effettuo la INSERT del nodo di default\n");
			UserNodoDefault nodoDefaultDBO = mapper.map(nodoDefaultDO, UserNodoDefault.class);
			// verifico se esiste gia' il nodo default per l'utente
			UserNodoDefaultExample example = new UserNodoDefaultExample();
			example.createCriteria().andCodUserEqualTo(nodoDefaultDBO.getCodUser());
			long esistenza = usernodo.countByExample(example);
			if (esistenza > 0) {// se esiste faccio update
				usernodo.updateByPrimaryKey(nodoDefaultDBO);
			} else {// altrimenti faccio insert
				usernodo.insert(nodoDefaultDBO);
			}
		} catch (Exception e) {
			logger.error("Errore durante il salvataggio del nodo di default: ", e);
			throw new DBException();
		}
	}

	/**
	 * Servizio che permette di reperire la lista delle funzionalita' valide per il ruolo utente connesso ad NFC
	 * 
	 * @param profiloUtente
	 * @return
	 * @throws DBException
	 */
	public List<NfcFunzioneCustomDO> recuperaFunzionalitaUtente(String profiloUtente) throws DBException {
		try {
			logger.debug("Inizio metodo Repository: recuperaFunzionalitaUtente  --> profiloUtente =" + profiloUtente);
			List<NfcFunzioneCustom> listaFunzionalita = new ArrayList<NfcFunzioneCustom>();
			List<NfcFunzioneCustomDO> listaFunzionalitaDO = new ArrayList<NfcFunzioneCustomDO>();
			if (!StringUtils.isBlank(profiloUtente)) {
				listaFunzionalita = nfcFunzioneMapper.recuperaFunzionalitaPerRuoloUtente(profiloUtente);
			}
			logger.debug("Fine metodo Repository: recuperaFunzionalitaUtente");
			listaFunzionalitaDO = mapper.mapAsList(listaFunzionalita, NfcFunzioneCustomDO.class);
			return listaFunzionalitaDO;
		} catch (Exception e) {
			logger.error("Errore durante l'esecuzione del metodo recuperaFunzionalitaUtente: ", e);
			throw new DBException();
		}
	}

}
