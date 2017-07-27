package msa.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by simon.calabrese on 27/07/2017.
 */
@Configuration
public class MongoConfig {


    @Autowired
    private PropertiesConfig propertiesConfig;

    @Bean
    public Mongo mongo() {
        return new MongoClient(propertiesConfig.getUrl(),Integer.valueOf(propertiesConfig.getPort()));
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongo(),propertiesConfig.getDbName());
    }
}
