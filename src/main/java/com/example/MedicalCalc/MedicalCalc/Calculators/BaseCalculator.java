package com.example.MedicalCalc.MedicalCalc.Calculators;

import com.example.MedicalCalc.MedicalCalc.DataObjects.responses.CalculatorFullInfo;
import com.example.MedicalCalc.MedicalCalc.DataObjects.responses.CalculatorInfo;
import com.example.MedicalCalc.MedicalCalc.DataObjects.responses.CalculatorResult;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class BaseCalculator<BaseRequest> {
    private final CalculatorType type;
    private final String description;

    public CalculatorInfo getInfo() {return new CalculatorInfo(getDescription());}

    public CalculatorFullInfo getFullInfo() {return new CalculatorFullInfo(type.getId(), type.getDescription(), getDescription());}

    public abstract CalculatorResult calculate(BaseRequest request);

}
