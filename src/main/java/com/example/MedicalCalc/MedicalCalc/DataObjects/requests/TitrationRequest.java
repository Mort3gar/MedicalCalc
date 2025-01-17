package com.example.MedicalCalc.MedicalCalc.DataObjects.requests;

import com.fasterxml.jackson.databind.ser.Serializers;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Объект, содержащий необходимые поля для калькулятора скорости титрования")
public class TitrationRequest extends BaseRequest {
    @Schema(description = "Вес человека в кг", example = "50")
    @Range(min = 10, max = 500, message = "'Вес' не должен быть меньше 10 кг и не должен превышать 500 кг")
    @NotNull(message = "Параметр 'вес' человека обязательный для заполнения")
    @Pattern(message = "Параметр 'вес' должно содержать только цифры", regexp="^[0-9]+(\\.[0-9]+)?$")
    private String weight;

    @Schema(description = "Дозировка препарата в мкг*кг/мин или мл/час", example = "2")
    @Positive(message = "'Дозировка препарата' строго положительная")
    @DecimalMin(value = "0.1", message = "Значение параметра 'дозировка препарата' должно " +
            "быть не меньше 0.1 мкг*кг/мин или мл/час")
    @NotNull(message = "Параметр 'дозировка препарата' обязательный для заполнения")
    @Pattern(message = "Параметр 'дозировка препарата' должно содержать только цифры", regexp="^[0-9]+(\\.[0-9]+)?$")
    private String dosage;

    @Schema(description = "Количество препарата в мг", example = "10")
    @Positive(message = "Значение 'количества препарата' является строго положительным")
    @Min(value = 1, message = "Минимальное значение 'количества препарата' составляет 1 мг")
    @NotNull(message = "Параметр 'количество препарата' обязательный для заполнения")
    @Pattern(message = "Параметр 'количество препарата' должно содержать только цифры", regexp="^[0-9]+(\\.[0-9]+)?$")
    private String amountOfDrug;

    @Schema(description = "Общий объем раствора в мл", example = "50")
    @Positive(message = "Значение 'общего объема раствора' является строго положительным")
    @Min(value = 1, message = "Минимальное значение 'общего объема раствора' составляет 1 мл")
    @NotNull(message = "Параметр 'общий объем раствора' обязательный для заполнения")
    @Pattern(message = "Параметр 'общий объем раствора' должно содержать только цифры", regexp="^[0-9]+(\\.[0-9]+)?$")
    private String volumeOfSolution;

    @Schema(description = "Дозировка в мл/час?", example = "true")
    @NotNull(message = "Параметр 'дозировка в мл/час?' обязательный для заполнения")
    private Boolean isMlInHour;
}
