package msa.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import msa.infrastructure.config.AbstractMsaPropertiesReader;

import java.util.Map;

@Component(value = "PropertiesConfig")
@ConfigurationProperties
public class PropertiesConfig extends AbstractMsaPropertiesReader {
    @Value("${spring.data.mongodb.host}")
    private String url;

    @Value("${spring.data.mongodb.port}")
    private String port;

    @Value("${spring.data.mongodb.database}")
    private String dbName;

    @Value("${msa.basePath}")
    private String basePath;

    @Value("${msa.getRestUrlMap}")
    private Map<String,String> restUrl;

    public String getMongoUrl() {
        return url;
    }

    public String getMongoPort() {
        return port;
    }

    public String getMongoDbName() {
        return dbName;
    }
    @Override
    public Map<String,String> getRestUrlMap() {
        return restUrl;
    }

    @Override
    public String getBasePath() {
        return this.basePath;
    }
}
