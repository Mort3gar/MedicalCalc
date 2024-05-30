package com.example.MedicalCalc.MedicalCalc.DataObjects.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Полная информация о калькуляторе", contentMediaType = "application/json")
public class CalculatorFullInfo extends CalculatorInfo{
    @Schema(description = "Идентификатор", example = "1")
    private Long id;

    @Schema(description = "Название калькулятора", example = "ИМС")
    private String name;

    public CalculatorFullInfo(Long id, String name, String info) {
        super(info);
        this.id = id;
        this.name = name;
    }
}
