package msa.infrastructure.repository;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import msa.domain.object.dominio.*;
import msa.infrastructure.base.repository.*;
import msa.infrastructure.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Repository;

@Repository
public class DomainRepository extends BaseRepository {

    @Autowired
    NazioniBaseRepository nazioniRepository;
    @Autowired
    ProvinceBaseRepository provinceRepository;
    @Autowired
    ComuniBaseRepository comuniRepository;
    @Autowired
    AutoritaBaseRepository autoritaRepository;
    @Autowired
    CompagniaBaseRepository compagniaRepository;
    @Autowired
    MezziComunicazioneBaseRepository mezziRepository;

    /**
     * Effettua la ricerca delle nazioni il cui nome inizia con la stringa passata
     *
     * @param nome la stringa da cercare
     * @return una lista di NazioneDO
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public List<NazioneDO> getListaNazioni(String nome)
            throws IllegalAccessException, InvocationTargetException, InstantiationException {
        List<NazioneDBO> result = nazioniRepository.findLikeNome(nome);
        return converter.convertList(result, NazioneDO.class);
    }

    /**
     * Effettua la ricerca delle province il cui nome inizia con la stringa passata
     * e che appartengono alla nazione di cui viene passato l'id
     *
     * @param idNazione     l'id della nazione di cui cercare le province
     * @param descProvincia la stringa da cercare
     * @return una lista di ProvinciaDO
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public List<ProvinciaDO> getElencoProvince(Integer idNazione, String descProvincia)
            throws InstantiationException, IllegalAccessException, InvocationTargetException {
        List<ProvinciaDBO> result = provinceRepository.findByDesc(idNazione, descProvincia);
        return converter.convertList(result, ProvinciaDO.class);
    }

    /**
     * Effettua la ricerca dei comuni il cui nome inizia con la stringa passata ed
     * appartenenti alla nazione e provincia passata
     *
     * @param idNazione    l'id della nazione di cui cercare il comune
     * @param codProvincia il codice della provincia di cui cercare il comune
     * @param desc         la stringa da cercare
     * @return una lista di oggetti ComuneDO
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public List<ComuneDO> getElencoComuni(Integer idNazione, Integer codProvincia, String desc)
            throws InstantiationException, IllegalAccessException, InvocationTargetException {
        List<ComuneDBO> result = comuniRepository.findByDesc(idNazione, codProvincia, desc);
        return converter.convertList(result, ComuneDO.class);
    }

    /**
     * Ottiene tutte le autorità presenti nel database
     *
     * @return una lista di oggetti AutoritaDTO
     */
    public List<AutoritaDO> getElencoAutorita() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        List<AutoritaDBO> result = autoritaRepository.findAll();
        return converter.convertList(result, AutoritaDO.class);
    }

    /**
     * Effettua la ricerca delle compagnie la cui descrizione contiene al suo interno
     * la stringa passata come parametro
     *
     * @param desc la stringa da cercare
     * @return una lista di oggetti CompagniaDO
     */
    public List<CompagniaDO> getElencoCompagnie(String desc) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        List<CompagniaDBO> result = compagniaRepository.findByDescrizioneIgnoreCase(desc);
        return converter.convertList(result, CompagniaDO.class);
    }

    /**
     * Ottiene tutti  i mezzi di comunicazione presenti nel DB
     *
     * @return una lista di oggetti MezzoComunicazioneDO
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public List<MezzoComunicazioneDO> getElencoMezziComunicazione() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        List<MezzoComunicazioneDBO> result = mezziRepository.findAll();
        return converter.convertList(result, MezzoComunicazioneDO.class);
    }
}
