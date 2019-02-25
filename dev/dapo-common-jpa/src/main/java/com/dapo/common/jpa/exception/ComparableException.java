package com.dapo.common.jpa.exception;

/**
 * Created by dimomass on 15.02.19.
 */
public class ComparableException extends RuntimeException {
    public ComparableException() {
    }

    public ComparableException(String message) {
        super(message);
    }

    public ComparableException(String message, Throwable cause) {
        super(message, cause);
    }

    public ComparableException(Throwable cause) {
        super(cause);
    }

    public ComparableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
