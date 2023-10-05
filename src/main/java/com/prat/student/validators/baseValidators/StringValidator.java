package com.prat.student.validators.baseValidators;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class StringValidator {
    public static boolean patternMismatch(String value, String pattern){
        return !value.matches(pattern);
    }
}