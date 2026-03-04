package com.minjikim.codecobainbackend.exception;

public class AiRequestSendException extends RuntimeException {
    public AiRequestSendException(String message) {
        super(message);
    }

    public AiRequestSendException(String message, Throwable cause) {
        super(message, cause);
    }
}
