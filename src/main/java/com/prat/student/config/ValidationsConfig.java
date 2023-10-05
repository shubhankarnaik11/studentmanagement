package com.prat.student.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
}
