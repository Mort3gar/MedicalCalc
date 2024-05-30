package com.example.MedicalCalc.MedicalCalc.Calculators;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CalculatorType {
    BMI("body-mass-index"),
    AVG_BLOOD_PRESSURE("average-blood-pressure"),
    TITRATION("titration-speed");

    private final String description;

    public Long getId() {return (long) ordinal()+1;}
}
