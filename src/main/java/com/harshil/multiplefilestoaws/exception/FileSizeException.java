package com.harshil.multiplefilestoaws.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PAYLOAD_TOO_LARGE)
public class FileSizeException extends RuntimeException {
    public FileSizeException(final String message) {
        super(message);
    }
}