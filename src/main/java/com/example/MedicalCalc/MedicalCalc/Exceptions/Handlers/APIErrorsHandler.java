package com.example.MedicalCalc.MedicalCalc.Exceptions.Handlers;

import com.example.MedicalCalc.MedicalCalc.Exceptions.API.Message;
import com.example.MedicalCalc.MedicalCalc.Exceptions.API.NotFound;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@Log4j2
@RestControllerAdvice
public class APIErrorsHandler {
    @ExceptionHandler(NotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Message notFoundException(NotFound exception, WebRequest request) {
        Message message = new Message(
                HttpStatus.NOT_FOUND.value(),
                exception.getTimestamp(), // Используйте timestamp из исключения
                exception.getMessage(),
                request.getDescription(false));
        log.error(message.getMessage());
        return message;
    }
}
