package com.example.MedicalCalc.MedicalCalc.Exceptions.API;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "Объект сообщения об ошибке")
public class Message {
    @Schema(description = "Код состояния http", example = "404")
    private int statusCode;

    @Schema(description = "Дата, время ошибки", example = "25-05-2024 12:00:00")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date timestamp;

    @Schema(description = "Сообщение об ошибке", example = "Калькулятор с идентификатором id не найден")
    private String message;

    @Schema(description = "Описание запроса", example = "Resolved [com.example.MedicalCalculators.exceptions.NotFoundException: Калькулятор с идентификатором id не найден]")
    private String description;
}
