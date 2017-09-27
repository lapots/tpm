package com.lapots.breed.platform.tpm.core.api.exception;

public class TpmException extends RuntimeException {

    public TpmException(String message) {
        super(message);
    }

    public TpmException(String message, Throwable e) {
        super(message, e);
    }
}
