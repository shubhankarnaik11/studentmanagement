package com.prat.student.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "validations")
public class ValidationsConfig {
    private String subjectNameRegexp;
    private Integer subjectMarkMax;
    private Integer subjectMarkMin;
    private Integer subjectPassMarksMaxPercent;
    private Integer subjectPassMarksMinPercent;
    private Integer subjectAttemptMax;
    private Integer subjectAttemptMin;




    public String toString(){
        return "subjectNameRegexp : " + subjectNameRegexp + "\n" +
                "subjectMarkMax : " + subjectMarkMax + "\n" +
                "subjectMarkMin : " + subjectMarkMin + "\n" +
                "subjectPassMarksMaxPercent : " + subjectPassMarksMaxPercent + "\n" +
                "subjectPassMarksMinPercent : " + subjectPassMarksMinPercent + "\n" +
                "subjectAttemptMax : " + subjectAttemptMax + "\n" +
                "subjectAttemptMin : " + subjectAttemptMin + "\n";
    }
}
