package ru.verzhik.coursework2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class IsNotUniqueException extends RuntimeException {

    public IsNotUniqueException() {
    }

    public IsNotUniqueException(String message) {
        super(message);

    }
}
