package com.danny.exception;

// Runtime exception is a superclass of those exceptions
// that can be thrown during the normal operation of the Java Virtual Machine
// Unchecked exceptions do not need to be declared in a method or constructor's throws clause

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
