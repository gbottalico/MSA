package msa.application.service.base;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import msa.application.config.Message;
import msa.application.config.enumerator.MessageType;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import msa.application.service.enumerator.Api;
import msa.domain.Converter.MsaConverter;
import msa.infrastructure.config.AbstractMsaPropertiesReader;

public class BaseService {
	
	@Autowired
	protected ObjectMapper objectMapper;

	@Autowired
	protected AbstractMsaPropertiesReader properties;
	
	@Autowired
	protected MsaConverter converter;	
	
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

	protected List<Message> buildErrorMessageByText(MessageType type, final String... texts) {
		return Arrays.stream(texts).map(e -> new Message(MessageType.ERROR,e)).collect(Collectors.toList());
	}
}
