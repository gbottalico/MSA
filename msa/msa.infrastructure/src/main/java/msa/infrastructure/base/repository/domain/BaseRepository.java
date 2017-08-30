package msa.infrastructure.base.repository.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import msa.domain.Converter.MsaConverter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.*;
import java.util.function.Function;


public class BaseRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseRepository.class);
    @Autowired
    protected MsaConverter converter;

    @Autowired
    protected MongoTemplate mongoTemplate;
    @Autowired
    private ObjectMapper mapper;

    private Query thisQuery;


    protected Query getCriteriaQueryBuilder() {
        thisQuery = new Query();
        return thisQuery;
    }

    protected <T> String getMongoNameByAttributeName(final String attributeName, Class<T> dboClass) {
        try {
            return dboClass.getDeclaredField(attributeName).getAnnotation(Field.class).value();
        } catch (NoSuchFieldException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    private <K> void insert(K elem) {
        mongoTemplate.insert(elem);
    }

    private <K> void update(K elem) {
        mongoTemplate.save(elem);
    }

    protected <K, T> void insert(K elem, Function<K, T> conversion) {
        if(elem.getClass().isAssignableFrom(List.class)) {
            converter.convertObject((List<K>) elem, conversion).forEach(this::insert);
        } else {
            insert(converter.convertObject(elem,conversion));
        }
    }

    protected <K, T> void insert(K elem, Class<T> dboClass) {
        insert(converter.convertObject(elem, dboClass));
    }


    protected <K, T> void insert(List<K> elem, Class<T> dboClass) {
        converter.convertList(elem, dboClass).forEach(this::insert);

    }

    protected <K, T> void update(K elem, Function<K, T> conversion) {
        if(elem.getClass().isAssignableFrom(List.class)) {
            converter.convertObject((List<K>) elem, conversion).forEach(this::update);
        } else {
            update(converter.convertObject(elem,conversion));
        }
    }


    protected <K, T> void update(K elem, Class<T> dboClass) {
        update(converter.convertObject(elem, dboClass));
    }

    protected <K, T> void update(List<K> elem, Class<T> dboClass) {
        converter.convertList(elem,dboClass).forEach(this::update);
    }

    protected <T> List<T> findAndDelete(Query query, Class<T> dboClass) {
        final List<T> ts = mongoTemplate.find(query, dboClass);
        mongoTemplate.findAllAndRemove(query, dboClass);
        return ts;
    }

    protected <T> Optional<T> getMaxElem(final Class<T> dboClass, Comparator<T> comparator) {
        List<T> all = mongoTemplate.findAll(dboClass);
        return CollectionUtils.isNotEmpty(all) ? all.stream().sorted(comparator).findFirst() : Optional.empty();
    }

    protected <T> List<T> findAll(final Class<T> dboClass) {
        return mongoTemplate.findAll(dboClass);
    }

    protected <T> List<T> findAll(final Class<T> dboClass, Pair<String, Object>... attributeValueCouple) {
        if (attributeValueCouple == null) {
            return findAll(dboClass);
        }
        List<Pair<String, Object>> pairs = new ArrayList<>(Arrays.asList(attributeValueCouple));
        Pair<String, Object> first = pairs.remove(0);
        Criteria criteria = Criteria.where(first.getKey()).is(first.getValue());
        criteria = pairs.stream().reduce(criteria,
                (a, b) -> a.and(b.getKey()).is(b.getValue()),
                (a, b) -> a);
        return mongoTemplate.find(getCriteriaQueryBuilder().addCriteria(criteria), dboClass);
    }

    protected <T> T findOne(final Class<T> dboClass, Pair<String, Object>... attributeValueCouple) {
        return Optional.ofNullable(findAll(dboClass,attributeValueCouple)).map(e -> e.stream().reduce((a,b) -> b).orElse(null)).orElse(null);
    }

    protected <T> List<T> findAll(final Class<T> dboClass,final Query query) {
        return mongoTemplate.find(query,dboClass);
    }

    protected <T, I> T findById(Class<T> dboClass, I id) {
        return mongoTemplate.findById(id, dboClass);
    }
}
