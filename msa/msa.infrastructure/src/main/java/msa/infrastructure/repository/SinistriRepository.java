package msa.infrastructure.repository;

import msa.domain.object.sinistro.InputRicercaDO;
import msa.domain.object.sinistro.SinistroDO;
import msa.infrastructure.base.repository.domain.BaseRepository;
import msa.infrastructure.base.repository.sinistri.SinistriBaseRepository;
import msa.infrastructure.persistence.sinistro.SinistroDBO;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

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
        //doToJson.put("compagnia", new AbstractMap.SimpleEntry<>(true, "compagnia"));
        doToJson.put("dataEvento", new AbstractMap.SimpleEntry<>(true, "segnalazione.dataDenuncia"));
        //se persona giuridica il cognome rappresenta la rag. sociale
        doToJson.put("cognome", new AbstractMap.SimpleEntry<>(false, "contraente.cognome"));
        doToJson.put("nome", new AbstractMap.SimpleEntry<>(false, "contraente.nome"));
        doToJson.put("numeroPolizza", new AbstractMap.SimpleEntry<>(false, "numeroPolizza"));
        //doToJson.put("numeroSinistro", "numeroSinistro");
        doToJson.put("targa", new AbstractMap.SimpleEntry<>(false, "targa"));
        doToJson.put("numeroProvvisorio", new AbstractMap.SimpleEntry<>(false, "numSinistroProvv"));
        //doToJson.put("numeroPreapertura", "numeroPreapertura");
        //doToJson.put("tipoPersona", "tipoPersona");
    }

    /**
     * Metodo di save or update per l'inserimento di un sinsitro provvisorio
     *
     * @param input i dati da inserire
     * @return
     */
    public Integer insertSinistroProvvisorio(SinistroDO input) {
        Integer numProvv = getNextNumSinistroProvv();
        input.setNumSinistroProvv(numProvv);
        mongoTemplate.insert(converter.convertObject(input, SinistroDBO.class));
        return numProvv;

    }

    private Query getQueryFromNotNullValues(InputRicercaDO inputRicerca) {
        Map<String, Object> fieldsNotNull = Arrays.stream(inputRicerca.getClass().getDeclaredFields())
                .reduce(new HashMap<String, Object>(),
                        (map, field) -> {
                            try {
                                field.setAccessible(true);
                                final Object value = PropertyUtils.getProperty(inputRicerca, field.getName());
                                if (value != null) {
                                    map.put(field.getName(), value);
                                }
                                return map;
                            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e1) {
                                return null;
                            }
                        },
                        (a, b) -> a);
        return getCriteriaQueryBuilder()
                .addCriteria(doToJson
                        .entrySet()
                        .stream()
                        .filter(e -> fieldsNotNull.keySet().contains(e.getKey()))
                        .reduce(Criteria.where("compagnia").is(fieldsNotNull.get("compagnia")),
                                (criteria, row) -> {
                                    if (row.getValue().getKey()) {
                                        return criteria.and(row.getValue().getValue()).is(fieldsNotNull.get(row.getKey()));
                                    } else {
                                        return criteria.and(row.getValue().getValue()).regex(Objects.toString(fieldsNotNull.get(row.getKey())));
                                    }
                                },
                                (a, b) -> a));
    }

    /**
     * Ottiene l'elenco dei sinistri provvisori in base ad i parametri passati in input
     */
    public List<SinistroDO> getElencoSinistriProvvisori(final InputRicercaDO inputRicerca) {
        Query queryFromNotNullValues = getQueryFromNotNullValues(inputRicerca);
        List<SinistroDBO> sinistroDBOS = mongoTemplate.find(queryFromNotNullValues, SinistroDBO.class);
        return converter.convertList(sinistroDBOS, SinistroDO.class);
    }

    /**
     * Metodo che serve ad ottenere l'ultimo ID inserito nel database
     *
     * @return
     */
    public Integer getNextNumSinistroProvv() {
        SinistroDBO numSinistroProvv = mongoTemplate.findOne(
                getCriteriaQueryBuilder()
                        .with(new Sort(Sort.Direction.DESC, "numSinistroProvv"))
                        .limit(1),
                SinistroDBO.class);
        return numSinistroProvv != null ? numSinistroProvv.getNumSinistroProvv()+1 : 0;
    }

    private boolean isNotNull(Object value) {
        return Objects.nonNull(value);
    }


    public SinistroDO getSinistroByNumProvv(Integer numProvv) {
        return converter.convertObject(mongoTemplate.findOne(getCriteriaQueryBuilder().addCriteria(Criteria.where(getMongoNameByAttributeName("numSinistroProvv", SinistroDBO.class)).is(numProvv)), SinistroDBO.class), SinistroDO.class);
    }
}
