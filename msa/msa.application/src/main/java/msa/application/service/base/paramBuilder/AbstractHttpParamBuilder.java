package msa.application.service.base.paramBuilder;

import msa.application.commons.Constants;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by simon.calabrese on 28/07/2017.
 */
public abstract class AbstractHttpParamBuilder<E extends HttpParam> {
    protected Function<Number,String> numberToString = Object::toString;
    protected Function<Date,String> dateToString = Constants.DATE_TO_STRING_DASH;
    protected List<E> parameter;

    public abstract <K extends AbstractHttpParamBuilder<E>> K appendParam(final String paramName, final String paramValue);

    public abstract <K extends AbstractHttpParamBuilder<E>,T extends Number> K appendParam(final String paramName, final T paramValue);

    public abstract <K extends AbstractHttpParamBuilder<E>,T extends Date> K appendParam(final String paramName, final T paramValue);

    public abstract String build();

    public abstract Map<String,String> build(String url);
}
