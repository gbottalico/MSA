package msa.application.service.base;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import msa.application.service.enumerator.Api;
import msa.infrastructure.config.AbstractMsaPropertiesReader;

public class BaseService {
	
	@Autowired
	protected ObjectMapper objectMapper;

	@Autowired
	protected AbstractMsaPropertiesReader properties;
	
	public BaseService(){
		
	};
	
	public BaseService(ObjectMapper objectMapper, AbstractMsaPropertiesReader properties) {
		super();
		this.objectMapper = objectMapper;
		this.properties = properties;
	}

	
	protected <T>T doGetCall(Class<T> clazz, Api api, Map<String, Object> param){
		return null;
	}
	
	protected <T>T doPostCall(Class<T> clazz, Api api, Object objParam){
		return null;
	}
	
}
