package msa.domain.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by simon.calabrese on 27/07/2017.
 */
public final class FunctionUtils {

    private final static Predicate<Object> checkIsNotNull = Objects::nonNull;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public static <T> Boolean checkIsNotNull(final T... object) {
        return Arrays.stream(object).map(Objects::nonNull).noneMatch(e -> e.equals(Boolean.FALSE));
    }

    public static <T extends Number> T numberConverter(String value, Function<String, T> function) {
        if (isNumber(value, function)) {
            return function.apply(value);
        } else {
            return null;
        }
    }

    public static <T extends Number> Boolean isNumber(String value, Function<String, T> function) {
        try {
            function.apply(value);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    /**
     * between NOT inclusive
     *
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
     *
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

    public static<T extends Comparable<T>> Boolean genericBetween(T left,T right,T evaluate,Boolean isIncl) {
        return isIncl
                ? left.compareTo(evaluate) <= 0  && right.compareTo(evaluate) >= 0
                : left.compareTo(evaluate) == -1 && right.compareTo(evaluate) == 1;
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

    public static <T> List<T> dinstictList(List<T> toFilter, Function<T, Object> keyEstractor) {

        return toFilter
                .stream()
                .collect(Collectors.toMap(keyEstractor,
                        e -> e,
                        (t, t1) -> t))
                .values()
                .stream()
                .collect(Collectors.toList());
    }

    public static <T, K> Boolean equalsListSize(List<T> a, List<K> b) {
        if (checkIsNotNull(a, b)) {
            return Integer.compare(a.size(), b.size()) == 0;
        } else return Boolean.FALSE;
    }

    public static String removePatternInList(final String value, final String... toRemove) {
        return Stream.concat(Stream.of(value), Stream.of(toRemove)).reduce((a, b) -> a.replaceAll(b, "")).orElse(value);
    }

    @SuppressWarnings("unchecked")
    public static<T> T castValueByClass(Object elem, Class<T> clazz) {
        return (T) elem;
    }

    public static<T> List<T> castValueByClass(List<Object> elems, Class<T> clazz) {
        return elems.stream().map(e -> castValueByClass(e,clazz)).collect(Collectors.toList());
    }

    //ritorna il frutto della funzione se l' optional è pieno altrimenti null
    public static<T,U> U execIfIsPresent(final Optional<T> optional, final Function<T,U> mapper) {
        return optional.map(mapper).orElse(null);
    }

    //ritorna il frutto della funzione se l' optional è pieno altrimenti null solo se soddisfa la condizione del predicate
    public static<T,U> U execIfIsPresent(final Optional<T> optional, final Function<T,U> mapper, final Predicate<T> condition) {
        return optional.filter(condition).map(mapper).orElse(null);
    }
}
