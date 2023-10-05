package com.prat.student.validators.baseValidators;

public class NumericValidator {
    public static boolean isNotInRange(Number value, Number minValue, Number maxValue){
        return value.doubleValue() < minValue.doubleValue() || value.doubleValue() > maxValue.doubleValue();
    }

    public static boolean isZero(Number value){ return value.doubleValue() == 0; }

}
