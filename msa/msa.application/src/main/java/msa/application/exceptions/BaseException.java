package msa.application.exceptions;

import msa.application.config.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by simon.calabrese on 24/07/2017.
 */
public class BaseException extends Exception {

    private Exception exceptionThrowed;
    private List<Message> messages;
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseException.class);

    public BaseException(Exception exceptionThrowed, List<Message> messages) {
        LOGGER.error(exceptionThrowed != null ? Arrays.stream(exceptionThrowed
                .getStackTrace())
                .map(StackTraceElement::toString)
                .collect(Collectors.joining(",\n"))
                : "");
        this.exceptionThrowed = exceptionThrowed;
        this.messages = messages;

    }

    public BaseException() {
    }

    public BaseException(List<Message> messages) {

        this.messages = messages;
    }

    public Exception getExceptionThrowed() {
        return exceptionThrowed;
    }

    public List<Message> getMessages() {
        return messages;
    }

}
