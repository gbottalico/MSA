package msa.application.service.sinistri;

import msa.application.exceptions.InternalMsaException;

/**
 * Created by simon.calabrese on 02/08/2017.
 */
@FunctionalInterface
public interface MsaBiFunction<A,B,C> {
    C apply(A a,B b) throws InternalMsaException;

}
