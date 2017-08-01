package msa.application.service.base.paramBuilder;

/**
 * Created by simon.calabrese on 28/07/2017.
 */
public class HttpParam {

    private String paramName;
    private String paramValue;
    private ParamType paramType;

    public HttpParam(String paramName, String paramValue) {
        this.paramName = paramName;
        this.paramValue = paramValue;
    }

    public String getParamName() {
        return paramName;
    }

    public String getParamValue() {
        return paramValue;
    }

    public ParamType getParamType() {
        return paramType;
    }

    public void setParamType(ParamType paramType) {
        this.paramType = paramType;
    }

    public enum ParamType {
        QUERY, PATH
    }
}
