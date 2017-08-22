package msa.domain.Converter;

import com.gs.collections.impl.block.factory.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by simon.calabrese on 27/07/2017.
 */
public final class FunctionUtils {

    private final static Predicate<Object> checkIsNotNull = Objects::nonNull;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public static <T> Boolean checkIsNotNull(final T... object) {
        return Arrays.stream(object).noneMatch(checkIsNotNull);
    }

    public static <T extends Number> T numberConverter(String value, Function<String, T> function) {
        return function.apply(value);
    }

    /**
     * between NOT inclusive
     * @param toEvaluate date to check
     * @param dateStart  left side date
     * @param dateEnd    right side date
     * @param <T>        all date's son
     * @return is between or not
     */
    public static <T extends Date> Boolean between(T toEvaluate, T dateStart, T dateEnd) {
        return between(toEvaluate, dateStart, dateEnd, null);
    }

    /**
     * between inclusive
     * @param toEvaluate  date to check
     * @param dateStart   left side date
     * @param dateEnd     right side date
     * @param isInclusive is inclusive or not
     * @param <T>         all date's son
     * @return is between or not
     */
    public static <T extends Date> Boolean between(T toEvaluate, T dateStart, T dateEnd, Boolean isInclusive) {
        Calendar calToEval = Calendar.getInstance();
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        calToEval.setTime(toEvaluate);
        cal1.setTime(dateStart);
        cal2.setTime(dateEnd);
        if (isInclusive == null || !isInclusive) {
            return calToEval.before(cal2) && calToEval.after(cal1);
        } else {
            return (calToEval.before(cal2) || calToEval.equals(cal2)) && (calToEval.after(cal1) || calToEval.equals(cal1));
        }
    }

    public final static Function<String, java.sql.Date> convertStringToLocaldate = s -> {
        if (s == null) {
            return null;
        }
        return java.sql.Date.valueOf(LocalDate.parse(s, FORMATTER));
    };

    public static java.sql.Date nowAsDate() {
        return java.sql.Date.valueOf(LocalDate.now());
    }

    public static<T> List<T> dinstictList(List<T> toFilter,Function<T,Object> keyEstractor){

        return toFilter
                .stream()
                .collect(Collectors.toMap(keyEstractor,
                        e -> e,
                        (t,t1) -> t))
                .values()
                .stream()
                .collect(Collectors.toList());
    }
}
