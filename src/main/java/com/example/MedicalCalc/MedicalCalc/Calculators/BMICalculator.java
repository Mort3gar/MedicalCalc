package com.example.MedicalCalc.MedicalCalc.Calculators;

import com.example.MedicalCalc.MedicalCalc.DataObjects.requests.BMIRequest;
import com.example.MedicalCalc.MedicalCalc.DataObjects.responses.CalculatorResult;
import lombok.extern.log4j.Log4j2;

import java.text.DecimalFormat;

@Log4j2
public class BMICalculator extends BaseCalculator<BMIRequest> {
    public BMICalculator() {
        super(CalculatorType.BMI,
                """
                Этот калькулятор позволяет быстро и просто рассчитать индекс массы тела(ИМТ). \n
                """);
        log.info("Создан калькулятор индекса массы тела");
    }

    @Override
    public CalculatorResult calculate(BMIRequest body) {
        try {
            double bmi = Double.parseDouble(body.getWeight())/ Math.pow(Double.parseDouble(body.getHeight())/100, 2);
            log.debug("Калькулятор ИМТ, результат:" + bmi);
            return new CalculatorResult(new DecimalFormat("#.##").format(bmi));
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error("Ошибка! {}", e.getMessage());
            return new CalculatorResult((new DecimalFormat("#.###")).format(Double.valueOf("0.0")));
        }
    }
}
