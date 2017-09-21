package msa.domain.Converter;

import java.time.LocalDate;
import java.time.ZoneId;
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
            return Optional.ofNullable(value).map(function).map(e -> Boolean.TRUE).orElse(null);
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    public static LocalDate dateToLocalDate(final Date date) {
    	return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
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
    public static Boolean between(Date toEvaluate, Date dateStart, Date dateEnd) {
        return between(toEvaluate, dateStart, dateEnd, Boolean.FALSE);
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
    public static Boolean between(Date toEvaluate, Date dateStart, Date dateEnd, Boolean isInclusive) {
        if(isInclusive == null || !isInclusive) {
        	return dateToLocalDate(toEvaluate).compareTo(dateToLocalDate(dateStart)) == 1
        			&& dateToLocalDate(toEvaluate).compareTo(dateToLocalDate(dateEnd)) == -1;
        } else {
        	return dateToLocalDate(toEvaluate).compareTo(dateToLocalDate(dateStart)) >= 0
        			&& dateToLocalDate(toEvaluate).compareTo(dateToLocalDate(dateEnd)) <= 0;
        }
    }

    public static<T extends Comparable<T>> Boolean genericBetween(T left,T right,T evaluate,Boolean isIncl) {
        return isIncl
                ? left.compareTo(evaluate) <= 0  && right.compareTo(evaluate) >= 0
                : left.compareTo(evaluate) == -1 && right.compareTo(evaluate) == 1;
    }



    public final static Function<String, Date> convertStringToLocaldate = s -> {
        if (s == null) {
            return null;
        }
        return Date.from(LocalDate.parse(s, FORMATTER).atStartOfDay(ZoneId.systemDefault()).toInstant());
    };

    public static Date nowAsDate() {
        return Date.from((LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
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

    public static class StreamBuilder<T> {
        private Stream<T> stream;

        public StreamBuilder() {
        }

        public <U> void of(Map<U,List<Function<U,Stream<T>>>> map) {
            this.stream = map.entrySet().stream()
                    .flatMap(e -> e.getValue().stream().map(elem -> elem.apply(e.getKey())))
                    .reduce(null,
                            (a,b) -> Optional.ofNullable(a).map(e -> Stream.concat(e, b)).orElseGet(() -> b),
                            (a,b) -> a);
        }

        public Stream<T> getStream() {
            return stream;
        }
    }
}
