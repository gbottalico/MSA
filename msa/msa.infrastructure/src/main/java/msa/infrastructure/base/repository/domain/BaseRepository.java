package msa.infrastructure.base.repository.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import msa.domain.Converter.MsaConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;


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

    protected <K,T> void insert(K elem,Class<T> dboClass) {
        mongoTemplate.insert(converter.convertObject(elem,dboClass));
    }

    protected <K,T> void update(K elem,Class<T> dboClass) {
        mongoTemplate.save(converter.convertObject(elem, dboClass));
    }

    protected <K,T> void update(List<K> elem,Class<T> dboClass) {
        elem.forEach(e -> mongoTemplate.save(converter.convertObject(e, dboClass)));
    }

    protected <K,T> void insert(List<K> elem, Class<T> dboClass) {
        elem.forEach(e -> mongoTemplate.insert(converter.convertObject(e,dboClass)));

    }

    protected <T> void findAnddelete(Query query,Class<T> dboClass) {
        mongoTemplate.findAllAndRemove(query, dboClass);
    }
}
