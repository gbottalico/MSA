package msa.web.api;

import msa.application.commons.SuperConverter;
import msa.application.config.BaseDTO;
import msa.application.config.Message;
import msa.application.config.enumerator.MessageType;
import msa.application.exceptions.BaseException;
import msa.application.exceptions.InternalMsaException;
import msa.application.exceptions.NotFoundMsaException;
import org.apache.commons.lang3.ClassUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by simon.calabrese on 24/07/2017.
 */
public abstract class GlobalExceptionHandler {



    @ResponseBody
    @ExceptionHandler(value = BaseException.class)
    public BaseDTO generalException(HttpServletResponse response, HttpServletResponse request, BaseException e) {
        return new SuperConverter<>(e,
                exception -> {
                    final BaseDTO dto = new BaseDTO();
                    dto.setMessaggi(exception.getMessages());
                    if(ClassUtils.isAssignable(exception.getClass(), InternalMsaException.class)) {
                        dto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                    } else if (ClassUtils.isAssignable(exception.getClass(), NotFoundMsaException.class)) {
                        dto.setStatus(HttpStatus.NOT_FOUND);
                    }
                    return dto;
                }
        ).convert();
    }

    private static final Supplier<List<Message>> standardMessage = () -> Collections.singletonList(
            new Message(
                    MessageType.ERROR,
                    "Attenzione: Sono presenti degli errori di sistema"
            ));

    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public BaseDTO generalException(HttpServletResponse response, HttpServletResponse request, RuntimeException e) {
        return new SuperConverter<>(e, ex -> {
            final BaseDTO dto = new BaseDTO();
            dto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            dto.setMessaggi(standardMessage.get());
            return dto;
        }).convert();
    }


}
