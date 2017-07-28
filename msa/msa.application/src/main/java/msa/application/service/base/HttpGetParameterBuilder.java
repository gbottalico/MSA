package msa.application.service.base;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Created by simon.calabrese on 27/07/2017.
 */
public class HttpGetParameterBuilder {


    private List<HttpParameter> parameter;

    public HttpGetParameterBuilder() {
        parameter = new ArrayList<>();
    }


    public HttpGetParameterBuilder appendQueryParam(final String paramName, final String paramValue) {
        parameter.add(new HttpParameter(paramName, paramValue, ParamType.QUERY));
        return this;
    }

    public <T extends Number> HttpGetParameterBuilder appendQueryParam(final String paramName, final T paramValue) {
        final Function<T, String> numberToString = Object::toString;
        parameter.add(new HttpParameter(paramName, numberToString.apply(paramValue), ParamType.QUERY));
        return this;
    }

    public <T extends Date> HttpGetParameterBuilder appendQueryParam(final String paramName, final T paramValue) {
        final Function<T, String> dateToString = Object::toString;
        parameter.add(new HttpParameter(paramName, dateToString.apply(paramValue), ParamType.QUERY));
        return this;
    }

    public String build() {
        return parameter.stream()
                .reduce("",
                        (acc, comb) -> {
                            if (comb.getParamType() == ParamType.QUERY) {
                                acc = acc.concat(
                                        StringUtils.isEmpty(acc)
                                                ? "?".concat(comb.getParamName()).concat("=").concat(comb.getParamValue())
                                                : "&".concat(comb.getParamName()).concat("=").concat(comb.getParamValue()));
                            } else {
                                //Todo PATHPARAM
                            }
                            return acc;
                        },
                        (a, b) -> a);
    }


    public enum ParamType {
        QUERY, PATH
    }

    private class HttpParameter {
        private String paramName;
        private String paramValue;
        private ParamType paramType;

        public HttpParameter() {
        }

        private HttpParameter(String paramName, String paramValue, ParamType paramType) {
            this.paramName = paramName;
            this.paramValue = paramValue;
            this.paramType = paramType;
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
    }
}
