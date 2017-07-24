package msa.application.commons;

import java.util.function.Function;

/**
 * Created by simon.calabrese on 24/07/2017.
 */
public class SuperConverter<A,B> {
    private A valueToConvert;
    private Function<A,B> function;

    public SuperConverter(A valueToConvert, Function<A, B> function) {
        this.valueToConvert = valueToConvert;
        this.function = function;
    }

    public B convert() {
        return function.apply(valueToConvert);
    }
}
