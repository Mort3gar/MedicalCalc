package com.example.MedicalCalc.MedicalCalc.DataObjects.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Setter
@Getter
@AllArgsConstructor
@Schema(description = "Объект, содержащий необходимые поля для калькулятора среднего артериального давления")
public class AvgBloodPressureRequest extends BaseRequest{
    @Schema(description = "Систолическое артериальное давление", example = "65")
    @Positive(message = "'Систолическое АД' строго положительное")
    @NotNull(message = "Параметр 'САД' является обязательным")
    @DecimalMin(value = "1.0", message = "Значение параметра 'Систолическое АД' должно быть не меньше 1.0 мм. рт. ст.")
    @Pattern(message = "Параметр 'Систолическое АД' должно содержать только цифры", regexp="^[0-9]+(\\.[0-9]+)?$")
    private String sad;
    @Schema(description = "Диастолическое артериальное давление", example = "65")
    @Positive(message = "'Диастолическое АД' строго положительное")
    @NotNull(message = "Параметр 'ДАД' является обязательным")
    @DecimalMin(value = "1.0", message = "Значение параметра 'Диастолическое АД' должно быть не меньше 1.0 мм. рт. ст.")
    @Pattern(message = "Параметр 'Диастолическое АД' должно содержать только цифры", regexp="^[0-9]+(\\.[0-9]+)?$")
    private String dad;
}
