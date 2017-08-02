package msa.application.commons.functions;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by simon.calabrese on 27/07/2017.
 */
public final class FunctionUtils {

    private final static Predicate<Object> checkIsNotNull = Objects::nonNull;

    public static<T> Boolean checkIsNotNull(final T...object) {
        return Arrays.stream(object).noneMatch(checkIsNotNull);
    }
}
