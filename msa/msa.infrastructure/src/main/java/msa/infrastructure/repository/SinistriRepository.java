package msa.infrastructure.repository;

import msa.domain.object.ricerca.BasePolizzaDO;
import msa.domain.object.ricerca.FullPolizzaDO;
import msa.domain.object.sinistro.*;
import msa.infrastructure.base.repository.domain.BaseRepository;
import msa.infrastructure.base.repository.sinistri.SinistriBaseRepository;
import msa.infrastructure.persistence.ricerca.FullPolizzaDBO;
import msa.infrastructure.persistence.sinistro.SinistroDBO;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class SinistriRepository extends BaseRepository {
    @Autowired
    SinistriBaseRepository sinistriRepository;
    private static final Map<String, Map.Entry<Boolean, String>> doToJson = new HashMap<>();

    /**
     * Mappa per associare il nome attributo del DTO ad i campi del JSON
     * se true va in IS, altrimenti la query va in LIKE
     */
    static {
        //  doToJson.put("compagnia", new AbstractMap.SimpleEntry<>(true, "compagnia"));
        doToJson.put("dataEvento", new AbstractMap.SimpleEntry<>(true, "segnalazione.dataSinistro"));
        //se persona giuridica il cognome rappresenta la rag. sociale
        doToJson.put("cognome", new AbstractMap.SimpleEntry<>(false, "contraente.cognome"));
        doToJson.put("nome", new AbstractMap.SimpleEntry<>(false, "contraente.nome"));
        //doToJson.put("numeroPolizza", new AbstractMap.SimpleEntry<>(false, "numeroPolizza"));
        //doToJson.put("numeroSinistro", "numSinistroProvv");
        doToJson.put("targa", new AbstractMap.SimpleEntry<>(false, "targa"));
        doToJson.put("numeroProvvisorio", new AbstractMap.SimpleEntry<>(true, "numSinistroProvv"));
        //doToJson.put("numeroPreapertura", "numeroPreapertura");
        //doToJson.put("tipoPersona", "tipoPersona");
    }

    private static Map<String, Class> classiGaranzieMap = new HashMap<>();

    static {
        classiGaranzieMap.put("rca", SinistroRcaDO.class);
        classiGaranzieMap.put("incendio", SinistroFurtoIncendioDO.class);
        classiGaranzieMap.put("furto_totale", SinistroFurtoIncendioDO.class);
        classiGaranzieMap.put("furto_parziale", SinistroFurtoIncendioDO.class);
        classiGaranzieMap.put("kasko", SinistroKaskoDO.class);
        classiGaranzieMap.put("cristalli", SinistroCristalliDO.class);
        classiGaranzieMap.put("inf_conducente", SinistroInfortuniConducenteDO.class);
    }

    private Query getQueryFromNotNullValues(InputRicercaDO inputRicerca) {
        final Map<String, Object> fields = Arrays.stream(inputRicerca.getClass().getDeclaredFields())
                .filter(e -> {
                    try {
                        e.setAccessible(true);
                        return PropertyUtils.getProperty(inputRicerca, e.getName()) != null;
                    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e1) {
                        return Boolean.FALSE;
                    }
                })
                .collect(Collectors.toMap(Field::getName, e -> {
                    try {
                        e.setAccessible(true);
                        return PropertyUtils.getProperty(inputRicerca, e.getName());
                    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e1) {
                        return null;
                    }
                }));
        final Criteria criteria = Criteria.where("contraente.compagnia").is(fields.remove("compagnia"));
        return getCriteriaQueryBuilder().addCriteria(fields.entrySet()
                .stream()
                .reduce(criteria,
                        (crit, set) -> {
                            if (doToJson.get(set.getKey()).getKey()) {
                                crit.and(doToJson.get(set.getKey()).getValue()).is(set.getValue());
                            } else {
                                return crit.and(doToJson.get(set.getKey()).getValue()).regex(Objects.toString(set.getValue()));
                            }
                            return crit;
                        },
                        (a, b) -> a));
    }

    /**
     * Ottiene l'elenco dei sinistri provvisori in base ad i parametri passati in input
     */
    public <K extends BaseSinistroDO> List<K> getElencoSinistriProvvisori(final InputRicercaDO inputRicerca) {
        Query queryFromNotNullValues = getQueryFromNotNullValues(inputRicerca);
        List<SinistroDBO> sinistroDBOS = findAll(SinistroDBO.class, queryFromNotNullValues)
                .stream()
                .filter(e -> inputRicerca.getUserLogged().getAmministratore()
                        || inputRicerca.getUserLogged().getIdUser().equalsIgnoreCase(e.getUserLogged().getIdUser()))
                .collect(Collectors.toList());
        return sinistroDBOS.stream().map(e -> {
            final Class<K> toPass = e.getSegnalazione() == null
                    ? (Class<K>) BaseSinistroDO.class
                    : getClassByGaranzia(e.getSegnalazione().getGaranziaSelected());
            return converter.convertObject(e, toPass);
        }).collect(Collectors.toList());
    }

    protected <T extends BaseSinistroDO> Class<T> getClassByGaranzia(final String garanziaSelected) {
        return classiGaranzieMap.get(garanziaSelected);
    }

    /**
     * Metodo di save or update per l'inserimento di un sinsitro provvisorio
     *
     * @param input i dati da inserire
     * @return
     */
    public <E extends BaseSinistroDO> Boolean insertSinistroProvvisorio(E input) throws Exception {
        final Integer numProvv = getNextNumSinistroProvv();
        input.setNumSinistroProvv(numProvv);
        insert(input);
        return Boolean.TRUE;
    }


    private <E extends BaseSinistroDO> void insert(E input) throws Exception {
        insert(input, SinistroDBO.class);
    }

    public <E extends BaseSinistroDO> Integer insertSinistroProvvisorioAndGetNum(E input) throws Exception {
        final Integer numProvv = getNextNumSinistroProvv();
        input.setNumSinistroProvv(numProvv);
        insert(input);
        return numProvv;
    }


    public <E extends BaseSinistroDO> Boolean updateSinistroProvvisorio(E input) throws Exception {
        update(input, SinistroDBO.class);
        return Boolean.TRUE;
    }


    public FullPolizzaDO getPolizzaByNumPoli(final String numPoli) throws Exception {
        FullPolizzaDBO polizzaById = mongoTemplate.findById(numPoli, FullPolizzaDBO.class);
        if (polizzaById == null) throw new Exception();
        return converter.convertObject(polizzaById, FullPolizzaDO.class);
    }

    /**
     * Metodo che serve ad ottenere l'ultimo ID inserito nel database
     *
     * @return
     */
    public Integer getNextNumSinistroProvv() {
        return getMaxElem(SinistroDBO.class, Comparator.comparing(SinistroDBO::getNumSinistroProvv).reversed())
                .map((sinistroDBO) -> sinistroDBO.getNumSinistroProvv() + 1).orElse(0);
    }

    public <E extends BaseSinistroDO> E getSinistroByNumProvv(Integer numProvv) throws Exception {
        final SinistroDBO sinistroByNumProvvQuery = getSinistroByNumProvvQuery(numProvv);
        final Class<E> toPass = sinistroByNumProvvQuery.getSegnalazione() == null ? (Class<E>) BaseSinistroDO.class : getClassByGaranzia(sinistroByNumProvvQuery.getSegnalazione().getGaranziaSelected());
        return converter.convertObject(sinistroByNumProvvQuery, toPass);
    }

    private SinistroDBO getSinistroByNumProvvQuery(Integer numProvv) throws Exception {
        final SinistroDBO sinistro = mongoTemplate.findOne(getQueryFindByNumProvv(numProvv), SinistroDBO.class);
        if (sinistro == null) {
            throw new Exception();
        }
        return sinistro;
    }

    public <E> E getSinistroByNumProvv(Integer numProvv, Class<E> finalClass) throws Exception {
        final SinistroDBO sinistro = mongoTemplate.findOne(getQueryFindByNumProvv(numProvv), SinistroDBO.class);
        if (sinistro == null) {
            throw new Exception();
        }
        return converter.convertObject(sinistro, finalClass);
    }

    private Query getQueryFindByNumProvv(Integer numProvv) {
        return getCriteriaQueryBuilder().addCriteria(Criteria.where(getMongoNameByAttributeName("numSinistroProvv", SinistroDBO.class)).is(numProvv));
    }
}
