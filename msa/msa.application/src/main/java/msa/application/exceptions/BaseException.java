package msa.application.exceptions;

import msa.application.config.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by simon.calabrese on 24/07/2017.
 */
public class BaseException extends Exception {

    private Exception exceptionThrowed;
    private List<Message> messages;
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseException.class);
    public BaseException(Exception exceptionThrowed, List<Message> messages) {
        LOGGER.error(exceptionThrowed.getMessage());
        this.exceptionThrowed = exceptionThrowed;
        this.messages = messages;

    }

    public Exception getExceptionThrowed() {
        return exceptionThrowed;
    }

    public List<Message> getMessages() {
        return messages;
    }

}
