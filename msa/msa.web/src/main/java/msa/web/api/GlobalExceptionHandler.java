package msa.web.api;

import msa.application.commons.SuperConverter;
import msa.application.config.BaseDTO;
import msa.application.exceptions.BaseException;
import msa.application.exceptions.InternalMsaException;
import msa.application.exceptions.NotFoundMsaException;
import org.apache.commons.lang3.ClassUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

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

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public BaseDTO generalRuntimeException(HttpServletResponse response, HttpServletResponse request, Exception e) {
        return generalException(response,request,new InternalMsaException());
    }
}
