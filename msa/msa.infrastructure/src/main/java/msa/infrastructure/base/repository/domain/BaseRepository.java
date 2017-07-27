package msa.infrastructure.base.repository;

import org.springframework.beans.factory.annotation.Autowired;

import msa.domain.Converter.MsaConverter;


public class BaseRepository {

	@Autowired 
	protected MsaConverter converter;
	
}
