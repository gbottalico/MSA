package msa.config;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import reactor.Environment;

@SpringBootApplication
@EnableCaching
@EnableAspectJAutoProxy
@EnableAsync
@EnableMongoRepositories(basePackages = "msa.infrastructure.base")
@ComponentScan({ "msa.batch", "msa.application", "msa.infrastructure", "msa.domain", "msa.config" })
public abstract class BaseMsaApplication {

	@Autowired
	private ObjectMapper jacksonMapper;

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}


	@Bean
	public Environment env() {
		return Environment.initializeIfEmpty().assignErrorJournal();
	}

	@PostConstruct
	public void initApp() {
		jacksonMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
	}


}
