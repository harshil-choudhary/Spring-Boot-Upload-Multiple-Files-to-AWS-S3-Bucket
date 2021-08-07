package com.harshil.multiplefilestoaws.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class FileTypeDetectionException extends RuntimeException {
    public FileTypeDetectionException(final String message) {
        super(message);
    }
}