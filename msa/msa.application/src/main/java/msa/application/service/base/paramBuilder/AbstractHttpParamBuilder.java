package msa.application.service.base.paramBuilder;

import java.util.Date;
import java.util.List;

/**
 * Created by simon.calabrese on 28/07/2017.
 */
public abstract class AbstractHttpParamBuilder<E extends HttpParam> {
    protected List<E> parameter;

    public abstract <K extends AbstractHttpParamBuilder<E>> K appendParam(final String paramName, final String paramValue);

    public abstract <K extends AbstractHttpParamBuilder<E>,T extends Number> K appendParam(final String paramName, final T paramValue);

    public abstract <K extends AbstractHttpParamBuilder<E>,T extends Date> K appendParam(final String paramName, final T paramValue);

    public abstract String build();
}
