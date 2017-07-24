package msa.application.exceptions;

import msa.application.config.Message;

import java.util.List;

/**
 * Created by simon.calabrese on 24/07/2017.
 */
public class NotFoundMsaException extends BaseException {
    public NotFoundMsaException(Exception exceptionThrowed, List<Message> messages) {
        super(exceptionThrowed, messages);
    }
}
