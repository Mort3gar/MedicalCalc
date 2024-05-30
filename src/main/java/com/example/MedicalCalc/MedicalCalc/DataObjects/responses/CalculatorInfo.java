package com.example.MedicalCalc.MedicalCalc.DataObjects.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Информация о калькуляторе")
public class CalculatorInfo extends BaseResponse{
    @Schema(description = "Информация о калькуляторе", example = "Этот калькулятор позволяет сделать что-то")
    private String info;
}
