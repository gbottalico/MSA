package msa.domain.Converter.commonObject;


import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Supplier;

/**
 * Created by simon.calabrese on 15/09/2017.
 */
@SuppressWarnings("unchecked")
public abstract class GenericTupla {
    public static <A extends Serializable, B extends Serializable> GenericCouple<A, B> instance(A first, B second) {
        return new GenericCouple<>(first, second);
    }

    public static <A extends Serializable, B extends Serializable, C extends Serializable> GenericTriplet<A, B, C> instance(A first, B second, C third) {
        return new GenericTriplet<>(first, second, third);
    }

    public static class GenericCouple<A extends Serializable, B extends Serializable> {
        private A first;
        private B second;

        public GenericCouple(A first, B second) {
            this.first = first;
            this.second = second;
        }

        public GenericCouple() {
        }

        public<T extends Serializable> Map<String, T> parse(Supplier<String> aParser, Supplier<String> bParser) {
            final Map<String, T> map = new TreeMap<>();
            map.put(aParser.get(), (T) this.first);
            map.put(bParser.get(), (T) this.second);
            return map;
        }

        public A getFirst() {
            return first;
        }

        public void setFirst(A first) {
            this.first = first;
        }

        public B getSecond() {
            return second;
        }

        public void setSecond(B second) {
            this.second = second;
        }
    }

    public static class GenericTriplet<A extends Serializable, B extends Serializable, C extends Serializable> extends GenericCouple<A, B> {
        private C third;

        public GenericTriplet(A first, B second, C third) {
            super(first, second);
            this.third = third;
        }

        public<T extends Serializable> Map<String, T> parse(final Supplier<String> aParser,
                                               final Supplier<String> bParser,
                                               final Supplier<String> cParser) {
            final Map<String, T> map = super.parse(aParser, bParser);
            map.put(cParser.get(), (T) this.third);
            return map;
        }

        public GenericTriplet() {
        }

        public C getThird() {
            return third;
        }

        public void setThird(C third) {
            this.third = third;
        }
    }
}
