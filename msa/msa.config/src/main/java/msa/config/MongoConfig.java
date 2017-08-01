package msa.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import msa.infrastructure.config.AbstractMsaPropertiesReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by simon.calabrese on 27/07/2017.
 */
@Configuration
public class MongoConfig {


    @Autowired
    private AbstractMsaPropertiesReader propertiesConfig;

    @Bean
    public Mongo mongo() {
        return new MongoClient(propertiesConfig.getMongoUrl(), Integer.valueOf(propertiesConfig.getMongoPort()));
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongo(), propertiesConfig.getMongoDbName());
    }
}
