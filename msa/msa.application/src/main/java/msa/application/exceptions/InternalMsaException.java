package msa.application.exceptions;

import msa.application.config.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by simon.calabrese on 24/07/2017.
 */
public class InternalMsaException extends BaseException {
    public InternalMsaException(Exception exceptionThrowed, List<Message> messages) {
        super(exceptionThrowed, messages);
    }

    public InternalMsaException(List<Message> messages) {
        super(null,messages);
    }
}
