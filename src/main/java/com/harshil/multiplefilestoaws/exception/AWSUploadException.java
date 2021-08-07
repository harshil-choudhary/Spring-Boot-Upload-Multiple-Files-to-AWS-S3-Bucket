package com.harshil.multiplefilestoaws.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
public class AWSUploadException extends RuntimeException {
    public AWSUploadException(final String message) {
        super(message);
    }
}