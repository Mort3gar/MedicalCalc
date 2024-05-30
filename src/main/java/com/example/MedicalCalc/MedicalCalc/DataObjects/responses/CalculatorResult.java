package com.example.MedicalCalc.MedicalCalc.DataObjects.responses;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Результат вычислений калькулятора", contentMediaType = "application/json")
public class CalculatorResult extends BaseResponse{
    @Schema(description = "Результат", example = "20,984")
    String result;
}
