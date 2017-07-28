package msa.config;

import msa.infrastructure.config.AbstractMsaApiMap;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
@ConfigurationProperties(prefix = "msa.restUrl")
public class MsaApiMap  extends AbstractMsaApiMap{
    private Map<String,String> api;

    public Map<String, String> getApi() {
        return api;
    }

    public void setApi(Map<String, String> api) {
        this.api = api;
    }
}
