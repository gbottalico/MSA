package msa.infrastructure.config;

public abstract class AbstractMsaPropertiesReader {

    public abstract String getMongoUrl();

    public abstract String getMongoPort();

    public abstract String getMongoDbName();

    public abstract AbstractMsaApiMap getRestUrlMap();
    public abstract String getBasePath();
    public abstract String getPathDocumenti();
}
