package msa.infrastructure.base.repository.domain;

import msa.infrastructure.config.AbstractMsaPropertiesReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import msa.domain.Converter.MsaConverter;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.query.Query;


public class BaseRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(BaseRepository.class);
	@Autowired 
	protected MsaConverter converter;

	@Autowired
	protected MongoTemplate mongoTemplate;

	private Query thisQuery;


	protected Query getCriteriaQueryBuilder() {
		thisQuery = new Query();
		return thisQuery;
	}

	protected <T>  String getMongoNameByAttributeName(final String attributeName, Class<T> dboClass) {
		try {
			return dboClass.getDeclaredField(attributeName).getAnnotation(Field.class).value();
		} catch (NoSuchFieldException e) {
			LOGGER.error(e.getMessage());
			return null;
		}
	}
}
