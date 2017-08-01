package msa.application.service.base.paramBuilder;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Created by simon.calabrese on 27/07/2017.
 */
public class HttpQueryParameterBuilder extends AbstractHttpParamBuilder<HttpQueryParameterBuilder.HttpQueryParam>{


    private String url;

    public HttpQueryParameterBuilder() {
        this.parameter = new ArrayList<>();
    }


    public HttpQueryParameterBuilder appendParam(final String paramName, final String paramValue) {
        parameter.add(new HttpQueryParam(paramName, paramValue));
        return this;
    }

    @Override
    public <K extends AbstractHttpParamBuilder<HttpQueryParam>, T extends Number> K appendParam(String paramName, T paramValue) {
        final Function<T, String> numberToString = Object::toString;
        return (K) this.appendParam(paramName,numberToString.apply(paramValue));
    }

    @Override
    public <K extends AbstractHttpParamBuilder<HttpQueryParam>, T extends Date> K appendParam(String paramName, T paramValue) {
        final Function<T, String> dateToString = Object::toString;
        return (K) appendParam(paramName,dateToString.apply(paramValue));
    }


    public String build() {
        return parameter.stream()
                .reduce("",
                        (acc, comb) -> {
                            acc = acc.concat(
                                    StringUtils.isEmpty(acc)
                                            ? "?".concat(comb.getParamName()).concat("=").concat(comb.getParamValue())
                                            : "&".concat(comb.getParamName()).concat("=").concat(comb.getParamValue()));
                            return acc;
                        },
                        (a, b) -> a);
    }




    public class HttpQueryParam extends HttpParam{

        private HttpQueryParam(String paramName,String paramValue) {
            super(paramName,paramValue);
            super.setParamType(ParamType.QUERY);
        }
    }
}
