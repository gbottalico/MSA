package msa.infrastructure.repository;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import msa.domain.object.dominio.ComuneDO;
import msa.domain.object.dominio.NazioneDO;
import msa.domain.object.dominio.ProvinciaDO;
import msa.infrastructure.base.repository.BaseRepository;
import msa.infrastructure.base.repository.ComuniBaseRepository;
import msa.infrastructure.base.repository.NazioniBaseRepository;
import msa.infrastructure.base.repository.ProvinceBaseRepository;
import msa.infrastructure.persistence.ComuneDBO;
import msa.infrastructure.persistence.NazioneDBO;
import msa.infrastructure.persistence.ProvinciaDBO;

@Repository
public class DomainRepository extends BaseRepository {

	@Autowired
	NazioniBaseRepository nazioniRepository;
	@Autowired
	ProvinceBaseRepository provinceRepository;
	@Autowired
	ComuniBaseRepository comuniRepository;

	/**
	 * Effettua la ricerca delle nazioni il cui nome inizia con la stringa passata
	 * 
	 * @param nome
	 *            la stringa da cercare
	 * @return una lista di NazioneDO
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	public List<NazioneDO> getListaNazioni(String nome)
			throws IllegalAccessException, InvocationTargetException, InstantiationException {
		List<NazioneDBO> result = nazioniRepository.findLikeNome(nome.toUpperCase());
		List<NazioneDO> listaDO = converter.convertList(result, NazioneDO.class);
		return listaDO;
	}

	/**
	 * Effettua la ricerca delle province il cui nome inizia con la stringa passata
	 * e che appartengono alla nazione di cui viene passato l'id
	 * 
	 * @param idNazione
	 *            l'id della nazione di cui cercare le province
	 * @param descProvincia
	 *            la stringa da cercare
	 * @return una lista di ProvinciaDO
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public List<ProvinciaDO> getElencoProvince(Integer idNazione, String descProvincia)
			throws InstantiationException, IllegalAccessException, InvocationTargetException {
		List<ProvinciaDBO> result = provinceRepository.findByDesc(idNazione, descProvincia.toUpperCase());
		List<ProvinciaDO> listaDO = converter.convertList(result, ProvinciaDO.class);
		return listaDO;
	}

	/**
	 * Effettua la ricerca dei comuni il cui nome inizia con la stringa passata ed
	 * appartenenti alla nazione e provincia passata
	 * 
	 * @param idNazione
	 *            l'id della nazione di cui cercare il comune
	 * @param codProvincia
	 *            il codice della provincia di cui cercare il comune
	 * @param desc
	 *            la stringa da cercare
	 * @return una lista di oggetti ComuneDO
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public List<ComuneDO> getElencoComuni(Integer idNazione, String codProvincia, String desc)
			throws InstantiationException, IllegalAccessException, InvocationTargetException {
		List<ComuneDBO> result = comuniRepository.findByDesc(idNazione, codProvincia, desc);
		List<ComuneDO> listaDO = converter.convertList(result, ComuneDO.class);
		return listaDO;
	}

}
