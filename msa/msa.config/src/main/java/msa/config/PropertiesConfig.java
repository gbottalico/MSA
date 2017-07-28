package msa.config;

import msa.infrastructure.config.AbstractMsaApiMap;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private MsaApiMap msaApiMap;


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
    public AbstractMsaApiMap getRestUrlMap() {
        return msaApiMap;
    }

    @Override
    public String getBasePath() {
        return this.basePath;
    }
}
