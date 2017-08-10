package msa.application.service.base.paramBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by simon.calabrese on 10/08/2017.
 */
public class FormDataParamBuilder extends AbstractHttpParamBuilder<FormDataParamBuilder.FormDataParam>{


    public FormDataParamBuilder() {
        parameter = new ArrayList<>();
    }

    @Override
    public AbstractHttpParamBuilder<FormDataParam> appendParam(String paramName, String paramValue) {
        parameter.add(new FormDataParam(paramName,paramValue));
        return this;
    }

    @Override
    public AbstractHttpParamBuilder<FormDataParam> appendParam(String paramName, Number paramValue) {
        parameter.add(new FormDataParam(paramName,numberToString.apply(paramValue)));
        return this;
    }

    @Override
    public AbstractHttpParamBuilder<FormDataParam> appendParam(String paramName, Date paramValue) {
        parameter.add(new FormDataParam(paramName,dateToString.apply(paramValue)));
        return this;
    }

    @Override
    public String build() {
        return null;
    }

    @Override
    public Map<String,String> build(final String url) {
        return parameter.stream().collect(Collectors.toMap(HttpParam::getParamName, HttpParam::getParamValue));
    }

    public class FormDataParam extends HttpParam {

        public FormDataParam(String paramName, String paramValue) {
            super(paramName, paramValue);
        }
    }
}