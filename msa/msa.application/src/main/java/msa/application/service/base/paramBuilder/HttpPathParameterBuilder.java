package msa.application.service.base.paramBuilder;

import msa.application.service.enumerator.Api;
import msa.infrastructure.config.AbstractMsaPropertiesReader;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by simon.calabrese on 27/07/2017.
 */
public class HttpPathParameterBuilder extends AbstractHttpParamBuilder<HttpPathParameterBuilder.HttpPathParam> {

    @Autowired
    protected AbstractMsaPropertiesReader properties;

    private String url;


    public HttpPathParameterBuilder(final Api url) {
        parameter = new ArrayList<>();
        this.url = properties.getRestUrlMap().getApi().get(url.getValue());
    }


    public HttpPathParameterBuilder appendParam(final String paramName, final String paramValue) {
        parameter.add(new HttpPathParam(paramName, paramValue));
        return this;
    }

    @Override
    public <K extends AbstractHttpParamBuilder<HttpPathParam>, T extends Number> K appendParam(String paramName, T paramValue) {
        final Function<T, String> numberToString = Object::toString;
        return (K) this.appendParam(paramName,numberToString.apply(paramValue));
    }

    @Override
    public <K extends AbstractHttpParamBuilder<HttpPathParam>, T extends Date> K appendParam(String paramName, T paramValue) {
        final Function<T, String> dateToString = Object::toString;
        return (K) appendParam(paramName,dateToString.apply(paramValue));
    }

    public String build() {
        return parameter.stream()
                .reduce("",
                        (acc, comb) -> StringUtils.isEmpty(acc) ? acc.concat(url)
                                : acc.replace("<" + comb.getParamName() + ">", comb.getParamValue()),
                        (a, b) -> a);
    }


    @Override
    public Map<String,String> build(String url) {
        return null;
    }

    public class HttpPathParam extends HttpParam {

        private HttpPathParam(String paramName, String paramValue) {
            super(paramName, paramValue);
            super.setParamType(ParamType.PATH);
        }
    }
}
