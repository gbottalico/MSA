package msa.application.service.sinistri;

import msa.application.exceptions.InternalMsaException;

/**
 * Created by simon.calabrese on 02/08/2017.
 */
@FunctionalInterface
public interface MsaBiFunction<A,B,C> {
    C apply(A a,B b) throws InternalMsaException;

    default <D> MsaBiFunction<A,B,D> andThen(MsaFunction<C,D> toConcat) throws InternalMsaException {
        return (a,b) -> toConcat.apply(this.apply(a,b));
    }
}
