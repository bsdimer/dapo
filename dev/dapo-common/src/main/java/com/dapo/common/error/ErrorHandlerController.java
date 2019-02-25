package com.dapo.common.error;

import com.dapo.common.dto.ErrorDto;
import org.apache.catalina.connector.ClientAbortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Dimitar Dimitrov
 */

@ControllerAdvice
public class ErrorHandlerController {

    private static final Logger log = LoggerFactory.getLogger(ErrorHandlerController.class);

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public void procesHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException ex) {
        log.debug(ex.getMessage(), ex);
    }

    @ExceptionHandler(ClientAbortException.class)
    public void processClientAbortException(ClientAbortException ex) {
        log.debug(ex.getMessage(), ex);
        log.debug("client disconected");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorDto processException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return ErrorDtoService.getErrorDto(ex);
    }

}
