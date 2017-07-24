package msa.web.api;

import msa.application.commons.SuperConverter;
import msa.application.config.BaseDTO;
import msa.application.exceptions.BaseException;
import msa.application.exceptions.InternalMsaException;
import msa.application.exceptions.NotFoundMsaException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ClassUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import javax.servlet.http.HttpServletRequest;

/**
 * Created by simon.calabrese on 24/07/2017.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BaseException.class)
    public <E extends BaseException> BaseDTO errorHandler(final HttpServletRequest request, E exception) {
        return new SuperConverter<>(exception,
                e -> {
                    final BaseDTO dto = new BaseDTO();
                    dto.setMessaggi(e.getMessages());
                    if(ClassUtils.isAssignable(e.getClass(), InternalMsaException.class)) {
                        dto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                    } else if (ClassUtils.isAssignable(e.getClass(), NotFoundMsaException.class)) {
                        dto.setStatus(HttpStatus.NOT_FOUND);
                    }
                    return dto;
                }
        ).convert();
    }
}
