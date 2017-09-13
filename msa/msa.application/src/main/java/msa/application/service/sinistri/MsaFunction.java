package msa.application.service.sinistri;

import msa.application.exceptions.InternalMsaException;

/**
 * Created by simon.calabrese on 03/08/2017.
 */
@FunctionalInterface
public interface MsaFunction<A, B> {
    B apply(A a) throws InternalMsaException;

    default <C> MsaFunction<A, C> andThen(MsaFunction<B, C> after) {
        return before -> after.apply(this.apply(before));
    }

    static<A> MsaFunction<A,A> identity() {return e -> e;}
}
