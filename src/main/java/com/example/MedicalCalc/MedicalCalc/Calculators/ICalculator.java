package com.example.MedicalCalc.MedicalCalc.Calculators;

import com.example.MedicalCalc.MedicalCalc.DataObjects.responses.CalculatorInfo;
import com.example.MedicalCalc.MedicalCalc.DataObjects.responses.CalculatorFullInfo;
import com.example.MedicalCalc.MedicalCalc.DataObjects.responses.CalculatorResult;

public interface ICalculator<BaseRequest> {
    CalculatorInfo getInfo();

    CalculatorFullInfo getFullInfo();

    CalculatorResult calculate(BaseRequest request);
}
