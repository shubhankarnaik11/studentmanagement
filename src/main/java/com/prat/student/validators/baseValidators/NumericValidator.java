package com.prat.student.validators.baseValidators;

public class NumericValidator {
    public static boolean greaterThan(Number value, Number minValue){
        return minValue.doubleValue() < value.doubleValue();
    }

    public static boolean lesserThan(Number value, Number maxValue){
        return maxValue.doubleValue()   > value.doubleValue();
    }

    public static boolean isNotInRange(Number value, Number minValue, Number maxValue){
        return value.doubleValue() <= minValue.doubleValue() || value.doubleValue() >= maxValue.doubleValue();
    }

    public static boolean isZero(Number value){ return value.doubleValue() == 0; }

}
