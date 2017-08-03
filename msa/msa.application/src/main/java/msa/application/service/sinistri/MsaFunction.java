package msa.application.service.sinistri;

import msa.application.exceptions.InternalMsaException;

/**
 * Created by simon.calabrese on 03/08/2017.
 */
@FunctionalInterface
public interface MsaFunction<A,B> {
    B apply(A a) throws InternalMsaException;
}
