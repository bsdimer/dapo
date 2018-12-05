package com.dapo.common.exception;

/**
 * Created by dimomass on 10.10.18.
 */
public class TemplateEngineException extends RuntimeException {
    public TemplateEngineException() {
    }

    public TemplateEngineException(String message) {
        super(message);
    }

    public TemplateEngineException(String message, Throwable cause) {
        super(message, cause);
    }

    public TemplateEngineException(Throwable cause) {
        super(cause);
    }

    public TemplateEngineException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
