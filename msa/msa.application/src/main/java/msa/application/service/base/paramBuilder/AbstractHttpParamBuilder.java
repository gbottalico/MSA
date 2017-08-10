package msa.application.service.base.paramBuilder;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by simon.calabrese on 28/07/2017.
 */
public abstract class AbstractHttpParamBuilder<E extends HttpParam> {
    protected Function<Number,String> numberToString = Object::toString;
    protected Function<Date,String> dateToString = e -> {
        LocalDate date = e.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
        return date.format(formatter);
    };
    protected List<E> parameter;

    public abstract <K extends AbstractHttpParamBuilder<E>> K appendParam(final String paramName, final String paramValue);

    public abstract <K extends AbstractHttpParamBuilder<E>,T extends Number> K appendParam(final String paramName, final T paramValue);

    public abstract <K extends AbstractHttpParamBuilder<E>,T extends Date> K appendParam(final String paramName, final T paramValue);

    public abstract String build();

    public abstract Map<String,String> build(String url);
}
