package com.example.MedicalCalc.MedicalCalc.Exceptions.API;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
public class NotFound extends RuntimeException {
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp;

    public NotFound(Date timestamp, String message) {
        super(message);
        this.timestamp = timestamp;
    }
}
