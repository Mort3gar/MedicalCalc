package com.example.MedicalCalc.MedicalCalc.Services;

import com.example.MedicalCalc.MedicalCalc.Calculators.*;
import com.example.MedicalCalc.MedicalCalc.DataObjects.requests.AvgBloodPressureRequest;
import com.example.MedicalCalc.MedicalCalc.DataObjects.requests.BMIRequest;
import com.example.MedicalCalc.MedicalCalc.DataObjects.requests.TitrationRequest;
import com.example.MedicalCalc.MedicalCalc.DataObjects.responses.CalculatorFullInfo;
import com.example.MedicalCalc.MedicalCalc.DataObjects.responses.CalculatorInfo;
import com.example.MedicalCalc.MedicalCalc.Exceptions.API.NotFound;
import com.example.MedicalCalc.MedicalCalc.DataObjects.responses.CalculatorResult;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;

@Log4j2
@Service
@Validated
public class CalculatorService {
    private final Map<CalculatorType, BaseCalculator> calculators = new HashMap<>();

    public CalculatorService() {
        calculators.put(CalculatorType.BMI, new BMICalculator());
        calculators.put(CalculatorType.AVG_BLOOD_PRESSURE, new AvgBloodPressureCalculator());
        calculators.put(CalculatorType.TITRATION, new TitrationCalculator());
        log.info("Создан сервис калькулятора");
    }

    private CalculatorFullInfo findCalculatorById(Long id) {
        for (Map.Entry<CalculatorType, BaseCalculator> entry : calculators.entrySet()) {
            if (entry.getKey().getId() == id) {
                log.debug("Калькулятор был найден с помощью метода: findCalculatorById");
                return entry.getValue().getFullInfo();
            }
        }
        log.warn("Для метода: findCalculatorById будет выдано исключение");
        throw new NotFound(new Date(), "Калькулятор с идентификатором " + id + " не был найден");
    }

    private CalculatorInfo findCalculatorByName(String description) {
        for (Map.Entry<CalculatorType, BaseCalculator> entry : calculators.entrySet()) {
            if (Objects.equals(entry.getKey().getDescription(), description)) {
                log.debug("Калькулятор был найден с помощью метода: findCalculatorByName");
                return entry.getValue().getInfo();
            }
        }
        log.warn("Для метода: findCalculatorByName будет выдано исключени");
        throw new NotFound(new Date(), "Калькулятор с названием " + description + " не был найден");
    }

    public CalculatorFullInfo getOne(@Min(value = 0, message = "Идентификатор должен быть не меньше 0") Long id) {
        CalculatorFullInfo calculatorInfoFull = findCalculatorById(id);
        if (calculatorInfoFull == null) {
            log.warn("Для метода: getOne будет выдано исключение");
            throw new NotFound(new Date(), "Калькулятор с идентификатором " + id + " не был найден");
        }
        log.debug("Калькулятор был найден с помощью метода: getOne");
        return calculatorInfoFull;
    }

    public List<CalculatorFullInfo> getAll() {
        List<CalculatorFullInfo> calculatorsInfo = new ArrayList<>();
        for (Map.Entry<CalculatorType, BaseCalculator> entry : calculators.entrySet()) {
            log.debug("В калькуляторах есть по крайней мере один калькулятор");
            calculatorsInfo.add(entry.getValue().getFullInfo());
        }
        log.debug("Информация о калькуляторах была возвращена");
        return calculatorsInfo;
    }

    public CalculatorInfo getInfo(@NotBlank(message = "Имя калькулятора не может быть пустым") String name) {
        CalculatorInfo calculatorInfoFull = findCalculatorByName(name);
        if (calculatorInfoFull == null) {
            log.warn("Для метода: getInfo будет выдано исключение");
            throw new NotFound(new Date(), "Калькулятор с названием " + name + " не был найден");
        }
        log.debug("Калькулятор был найден с помощью метода: getInfo");
        return calculatorInfoFull;

    }

    public CalculatorResult getBMIResult(@Valid BMIRequest request) {
        log.debug("Вызвано: метод getBMIResult");
        return calculators.get(CalculatorType.BMI).calculate(request);
    }

    public CalculatorResult getABPResult(@Valid AvgBloodPressureRequest request){
        log.debug("Вызвано: метод getABPResult");
        return calculators.get(CalculatorType.AVG_BLOOD_PRESSURE).calculate(request);
    }

    public CalculatorResult getTitrationResult(@Valid TitrationRequest request) {
        log.debug("Вызвано: метод getTitrationResult");
        return calculators.get(CalculatorType.TITRATION).calculate(request);
    }
//
//    public CalculatorResult getRIDDResult(@Valid RIDDCalculatorRequest calculatorRequest) {
//        log.debug("Запущен метод getRIDDResult");
//        return calculators.get(CalculatorType.RATE_INTRAVENOUS_DRIP_DRUG).calculate(calculatorRequest);
//    }
}
