package msa.infrastructure.base.repository.domain;

import msa.infrastructure.config.AbstractMsaPropertiesReader;
import org.springframework.beans.factory.annotation.Autowired;

import msa.domain.Converter.MsaConverter;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;


public class BaseRepository {

	@Autowired 
	protected MsaConverter converter;

	@Autowired
	protected MongoTemplate mongoTemplate;

	private Query thisQuery;


	protected Query getCriteriaQueryBuilder() {
		thisQuery = new Query();
		return thisQuery;
	}
}
