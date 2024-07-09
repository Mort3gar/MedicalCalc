package com.example.MedicalCalc.MedicalCalc.Calculators;

import com.example.MedicalCalc.MedicalCalc.DataObjects.requests.TitrationRequest;
import com.example.MedicalCalc.MedicalCalc.DataObjects.responses.CalculatorResult;
import lombok.extern.log4j.Log4j2;

import java.text.DecimalFormat;

@Log4j2
public class TitrationCalculator extends BaseCalculator<TitrationRequest>{

    public TitrationCalculator() {
        super(CalculatorType.TITRATION,
                """
                        Этот калькулятор позволяет расчитать скорость инфузии препарата через линеомат\n
                        (скорость титрования в мл/час) при известном количестве препарата в милиграммах\n
                        в известном объеме раствора. Также необходимо указать вес пациента и дозировку,\n
                        определяемую либо в мкг*кг/мин, либо в мл/час.
                        """);
        log.info("Создан калькулятор индекса массы тела");
    }

    @Override
    public CalculatorResult calculate(TitrationRequest request) {
        try {
            double dosage = 0;
            if(!request.getIsMlInHour()){
                dosage = Double.parseDouble(request.getDosage()) * 0.001 * Double.parseDouble(request.getVolumeOfSolution())
                        / (Double.parseDouble(request.getWeight()) * 1000 * 60);
            }
            else{
                dosage = Double.parseDouble(request.getDosage());
            }
            double infusionSpeed = Double.parseDouble(request.getWeight()) * dosage /
                    (Double.parseDouble(request.getAmountOfDrug()) * (1000 /
                            Double.parseDouble(request.getVolumeOfSolution()))) * 60;
            log.debug("Калькулятор титрования, результат:" + infusionSpeed);
            return new CalculatorResult((new DecimalFormat("#.###")).format(infusionSpeed));
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error("Ошибка! {}", e.getMessage());
            return new CalculatorResult((new DecimalFormat("#.###")).format(Double.valueOf("0.0")));
        }

    }
}
