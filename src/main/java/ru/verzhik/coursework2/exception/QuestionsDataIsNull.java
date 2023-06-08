package ru.verzhik.coursework2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class QuestionsDataIsNull extends RuntimeException {
    public QuestionsDataIsNull() {
    }

    public QuestionsDataIsNull(String message) {
        super(message);
    }
}
