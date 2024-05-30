package com.example.MedicalCalc.MedicalCalc.DataObjects.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Объект, содержащий необходимые поля для калькулятора индекса массы тела")
public class BMIRequest extends BaseRequest{
    @Schema(description = "Вес человека в кг", example = "65")
    @Range(min = 10, max = 500, message = "Вес должен быть в пределах от 10 до 500 кг")
    @NotNull(message = "Параметр 'вес' является обязательным")
    private Double weight;

    @Schema(description = "Рост человека в см", example = "170")
    @Range(min = 10, max = 300, message = "Рост должен быть в пределах от 10 до 300 см")
    @NotNull(message = "Параметр 'рост' является обязательным")
    private Double height;
}
