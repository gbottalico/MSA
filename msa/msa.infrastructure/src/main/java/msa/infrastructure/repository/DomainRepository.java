package msa.infrastructure.repository;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import msa.domain.object.dominio.NazioneDO;
import msa.infrastructure.base.repository.BaseRepository;
import msa.infrastructure.base.repository.NazioniBaseRepository;
import msa.infrastructure.persistence.NazioneDBO;

@Repository
public class DomainRepository extends BaseRepository {

	@Autowired
	NazioniBaseRepository nazioniRepository;

	/**
	 * 
	 * @param nome
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	public List<NazioneDO> getListaNazioni(String nome) throws IllegalAccessException, InvocationTargetException, InstantiationException {
		List<NazioneDBO> result = nazioniRepository.findLikeNome(nome);
		List<NazioneDO> listaDO = converter.convertList(result, NazioneDO.class);
		return listaDO;
	}

}
