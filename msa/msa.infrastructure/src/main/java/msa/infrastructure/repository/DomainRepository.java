package msa.infrastructure.repository;

import msa.domain.Converter.MsaConverter;
import msa.domain.object.dominio.*;
import msa.domain.object.sinistro.RuoliDO;
import msa.infrastructure.base.repository.domain.*;
import msa.infrastructure.persistence.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Repository
public class DomainRepository extends BaseRepository {

    @Autowired
    private NazioniBaseRepository nazioniRepository;
    @Autowired
    private ProvinceBaseRepository provinceRepository;
    @Autowired
    private ComuniBaseRepository comuniRepository;
    @Autowired
    private AutoritaBaseRepository autoritaRepository;
    @Autowired
    private CompagnieBaseRepository compagniaRepository;
    @Autowired
    private MezziComunicazioneBaseRepository mezziRepository;
    @Autowired
    private CauseRotturaCristalliBaseRepository causaRotturaCristalliRepository;
    @Autowired
    private TipoVeicoloBaseRepository tipoVeicoloRepository;
    @Autowired
    private TipoTargaBaseRepository tipoTargaRepository;
    @Autowired
    private CasaRegoleBaseRepository casaRegoleRepository;
    @Autowired
    private BaremesBaseRepository baremesRepository;
    @Autowired
    private RuoliBaseRepository ruoliRepository;

    /**
     * Effettua la ricerca delle nazioni il cui nome inizia con la stringa passata
     *
     * @param nome la stringa da cercare
     * @return una lista di NazioneDO
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public List<NazioneDO> getListaNazioni(String nome) {
        return converter.convertList(nazioniRepository.findLikeNome(nome), NazioneDO.class);
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
    public List<ProvinciaDO> getElencoProvince(Integer idNazione, String descProvincia) {
        return converter.convertList(provinceRepository.findByDesc(idNazione, descProvincia), ProvinciaDO.class);
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
    public List<ComuneDO> getElencoComuni(Integer idNazione, Integer codProvincia, String desc) {
        return converter.convertList(comuniRepository.findByDesc(idNazione, codProvincia, desc), ComuneDO.class);
    }

    /**
     * Ottiene tutte le autorità presenti nel database
     *
     * @return una lista di oggetti AutoritaDTO
     */
    public List<AutoritaDO> getElencoAutorita() {
        return converter.convertList(autoritaRepository.findAll(), AutoritaDO.class);
    }

    /**
     * Effettua la ricerca delle compagnie la cui descrizione contiene al suo interno
     * la stringa passata come parametro
     *
     * @param desc la stringa da cercare
     * @return una lista di oggetti CompagniaDO
     */
    public List<CompagniaDO> getElencoCompagnie(String desc) {
        return converter.convertObject(compagniaRepository.findByDescrizioneIgnoreCase(desc), (CompagniaDBO compagniaDBO) -> {
            CompagniaDO compagniaDO = new CompagniaDO();
            compagniaDO.setCodFornitore(compagniaDBO.getCodFornitore());
            compagniaDO.setConvenzioneCid(compagniaDBO.getConvenzioneCid());
            compagniaDO.setDataInCard(converter.convertObject(compagniaDBO.getDataInCard(), MsaConverter.Utils.convertStringToLocaldate));
            compagniaDO.setDataOutCard(converter.convertObject(compagniaDBO.getDataOutCard(), MsaConverter.Utils.convertStringToLocaldate));
            compagniaDO.setDescrizione(compagniaDBO.getDescrizione());
            compagniaDO.setEstera(compagniaDBO.getEstera());
            compagniaDO.setId(compagniaDBO.getId());
            compagniaDO.setLiquidazioneCoatta(compagniaDBO.getLiquidazioneCoatta());
            return compagniaDO;
        });
    }

    /**
     * Ottiene tutti  i mezzi di comunicazione presenti nel DB
     *
     * @return una lista di oggetti MezzoComunicazioneDO
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public List<MezzoComunicazioneDO> getElencoMezziComunicazione() {
        return converter.convertList(mezziRepository.findAll(), MezzoComunicazioneDO.class);

    }

    /**
     * Ottiene tutte le cause di rottura cristalli
     *
     * @return una lista di oggetti CausaRotturaCristalliDO
     */
    public List<CausaRotturaCristalliDO> getElencoCauseRotturaCristalli() {
        return converter.convertList(causaRotturaCristalliRepository.findAll(), CausaRotturaCristalliDO.class);

    }

    /**
     * Ottiene la lista delle tipologie di veicoli
     *
     * @return una lista di oggetti TipoVeicoloDO
     */
    public List<TipoVeicoloDO> getElencoTipoVeicoli() {
        return converter.convertList(tipoVeicoloRepository.findAll(), TipoVeicoloDO.class);
    }

    /**
     * Ottiene la lista di tipologie di targhe
     *
     * @return una lista di oggetti TipoTargaDO
     */
    public List<TipoTargaDO> getElencoTipoTarga() {
        return converter.convertList(tipoTargaRepository.findAll(), TipoTargaDO.class);
    }

    /**
     * Ottiene la lista delle regole contenute nella casa delle regole
     *
     * @return una lista di oggetti CasaRegoleDO
     */
    public List<CasaRegoleDO> getElencoRegole() {
        return converter.convertList(casaRegoleRepository.findAll(), CasaRegoleDO.class);
    }

    /**
     * Metodo che ottiene la lista di tutti i baremes
     *
     * @return una lista di oggetti BaremesDO
     */
    public List<BaremesDO> getElencoBaremes() {
        return converter.convertList(baremesRepository.findAll(), BaremesDO.class);
    }

    /**
     * Metodo che ottiene la lista di tutti i ruoli
     *
     * @return
     */
    public List<RuoliDO> getElencoRuoli() {
        return converter.convertList(ruoliRepository.findAll(), RuoliDO.class);
    }
}
