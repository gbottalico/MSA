package msa.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import msa.infrastructure.config.AbstractMsaPropertiesReader;

@Component(value = "PropertiesConfig")
@ConfigurationProperties
public class PropertiesConfig extends AbstractMsaPropertiesReader {
    @Value("${spring.data.mongodb.host}")
    private String url;

    @Value("${spring.data.mongodb.port}")
    private String port;

    @Value("${spring.data.mongodb.database}")
    private String dbName;

    public String getUrl() {
        return url;
    }

    public String getPort() {
        return port;
    }

    public String getDbName() {
        return dbName;
    }
}
