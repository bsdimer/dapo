package com.dapo.common.error;

import com.dapo.common.dto.DefaultErrorDto;
import com.dapo.common.dto.ErrorDto;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dimer on 10.10.17.
 */
public class ErrorDtoService {

    private static Map<String, ErrorDto> errorDtoMap = new HashMap() {{

        put("com.aossia.exceptions.RelationAcceptException", new DefaultErrorDto(5001));

        put("com.aossia.rad.auth.exceptions.IllegalRelationException", new DefaultErrorDto(5002));

    }};

    public static ErrorDto getErrorDto(Exception exception) {
        String exceptionClass = exception.getClass().getCanonicalName();
        return errorDtoMap.containsKey(exceptionClass) ? errorDtoMap.get(exceptionClass).setDescription(exception.getMessage()) : new DefaultErrorDto(exception);
    }
}
