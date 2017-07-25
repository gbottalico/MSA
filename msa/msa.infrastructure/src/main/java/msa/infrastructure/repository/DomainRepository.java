package msa.infrastructure.repository;

import msa.domain.Converter.MsaConverter;
import msa.domain.object.dominio.*;
import msa.infrastructure.base.repository.*;
import msa.infrastructure.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

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
    CompagnieBaseRepository compagniaRepository;
    @Autowired
    MezziComunicazioneBaseRepository mezziRepository;
    @Autowired
    CauseRotturaCristalliBaseRepository causaRotturaCristalliRepository;
    @Autowired
    TipoVeicoloBaseRepository tipoVeicoloRepository;
    @Autowired
    TipoTargaBaseRepository tipoTargaRepository;

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
    public List<ProvinciaDO> getElencoProvince(Integer idNazione, String descProvincia) {
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
    public List<ComuneDO> getElencoComuni(Integer idNazione, Integer codProvincia, String desc) {
        List<ComuneDBO> result = comuniRepository.findByDesc(idNazione, codProvincia, desc);
        return converter.convertList(result, ComuneDO.class);
    }

    /**
     * Ottiene tutte le autorità presenti nel database
     *
     * @return una lista di oggetti AutoritaDTO
     */
    public List<AutoritaDO> getElencoAutorita() {
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
    public List<CompagniaDO> getElencoCompagnie(String desc) {
        List<CompagniaDBO> result = compagniaRepository.findByDescrizioneIgnoreCase(desc);
        return converter.convertObject(result, (CompagniaDBO compagniaDBO) -> {
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
        List<MezzoComunicazioneDBO> result = mezziRepository.findAll();
        return converter.convertList(result, MezzoComunicazioneDO.class);

    }

    /**
     * Ottiene tutte le cause di rottura cristalli
     *
     * @return una lista di oggetti CausaRotturaCristalliDO
     */
    public List<CausaRotturaCristalliDO> getElencoCauseRotturaCristalli() {
        List<CausaRotturaCristalliDBO> result = causaRotturaCristalliRepository.findAll();
        return converter.convertList(result, CausaRotturaCristalliDO.class);

    }

    /**
     * Ottiene la lista delle tipologie di veicoli
     *
     * @return
     */
    public List<TipoVeicoloDO> getElencoTipoVeicoli() {
        List<TipoVeicoloDBO> result = tipoVeicoloRepository.findAll();
        return converter.convertList(result, TipoVeicoloDO.class);
    }

    /**
     * Ottiene la lista di tipologie di targhe
     *
     * @return
     */
    public List<TipoTargaDO> getElencoTipoTarga() {
        List<TipoTargaDBO> result = tipoTargaRepository.findAll();
        return converter.convertList(result, TipoTargaDO.class);
    }
}
