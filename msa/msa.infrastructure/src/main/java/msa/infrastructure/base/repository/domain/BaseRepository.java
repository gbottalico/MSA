package msa.infrastructure.base.repository.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import msa.domain.Converter.MsaConverter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


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

    protected <K, T> void insert(K elem, Class<T> dboClass) {
        mongoTemplate.insert(converter.convertObject(elem, dboClass));
    }

    protected <K, T> void update(K elem, Class<T> dboClass) {
        mongoTemplate.save(converter.convertObject(elem, dboClass));
    }

    protected <K, T> void update(List<K> elem, Class<T> dboClass) {
        elem.forEach(e -> mongoTemplate.save(converter.convertObject(e, dboClass)));
    }

    protected <K, T> void insert(List<K> elem, Class<T> dboClass) {
        elem.forEach(e -> mongoTemplate.insert(converter.convertObject(e, dboClass)));

    }

    protected <T> void findAndDelete(Query query, Class<T> dboClass) {
        mongoTemplate.findAllAndRemove(query, dboClass);
    }

    protected <T> Optional<T> getMaxElem(final Class<T> dboClass, Comparator<T> comparator) {
        List<T> all = mongoTemplate.findAll(dboClass);
        return CollectionUtils.isNotEmpty(all) ? all.stream().sorted(comparator).findFirst() : Optional.empty();
    }

    protected <T> List<T> findAll(final Class<T> dboClass) {
        return mongoTemplate.findAll(dboClass);
    }

    protected <T> List<T> findAll(final Class<T> dboClass, Pair<String,Object>... attributeValueCouple) {
        if(attributeValueCouple == null) {
            return findAll(dboClass);
        }
        List<Pair<String, Object>> pairs = new ArrayList<>(Arrays.asList(attributeValueCouple));
        Pair<String, Object> first = pairs.remove(0);
        Criteria criteria = Criteria.where(first.getKey()).is(first.getValue());
        criteria = pairs.stream().reduce(criteria,
                (a,b) -> a.and(b.getKey()).is(b.getValue()),
                (a,b) -> a);
        return mongoTemplate.find(getCriteriaQueryBuilder().addCriteria(criteria),dboClass);
    }
}
