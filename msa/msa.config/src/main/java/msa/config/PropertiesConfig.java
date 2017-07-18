package msa.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import msa.infrastructure.config.AbstractMsaPropertiesReader;

@Component(value = "PropertiesConfig")
@ConfigurationProperties
public class PropertiesConfig extends AbstractMsaPropertiesReader {



}
