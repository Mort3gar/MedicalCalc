package com.example.MedicalCalc.MedicalCalc.Calculators;

import com.example.MedicalCalc.MedicalCalc.DataObjects.requests.AvgBloodPressureRequest;
import com.example.MedicalCalc.MedicalCalc.DataObjects.responses.CalculatorResult;
import lombok.extern.log4j.Log4j2;

import java.text.DecimalFormat;

@Log4j2
public class AvgBloodPressureCalculator extends BaseCalculator<AvgBloodPressureRequest>{

    public AvgBloodPressureCalculator() {
        super(CalculatorType.AVG_BLOOD_PRESSURE,
                """
                        Калькулятор расчета среднего артериального давления по данным систолического и диастолического АД
                        """);
        log.info("Создан калькулятор расчета среднего артериального давления");
    }

    @Override
    public CalculatorResult calculate(AvgBloodPressureRequest request) {
        try{
            int avg = (int) (Double.parseDouble(request.getSad())/3+Double.parseDouble(request.getDad())/3);
            log.debug("Калькулятор САД, результат:" + avg);
            return new CalculatorResult(Integer.toString(avg));
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("Ошибка! {}", e.getMessage());
            return new CalculatorResult((new DecimalFormat("#.###")).format(Double.valueOf("0.0")));
        }
    }
}
