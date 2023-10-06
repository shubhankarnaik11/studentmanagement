package com.prat.student.validators;

import com.prat.student.config.ValidationsConfig;
import com.prat.student.dto.GradeDto;
import com.prat.student.dto.SubjectDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.prat.student.validators.baseValidators.NumericValidator.*;
import static com.prat.student.validators.baseValidators.StringValidator.*;

@Component
public class DTOValidators {

    @Autowired
    ValidationsConfig config;

    public ValidatorObject isSubjectValid(SubjectDto subjectDto){
        ValidatorObject validObj = new ValidatorObject(true, "");
        if(isZero(subjectDto.getMaxMark())){
            validObj.setSuccess(false);
            validObj.setErrorMsg("maxMark must not be zero");
            return validObj;//returning because next step it is in the denominator
        }

        Integer maxPassMark = Double.valueOf(
                Math.ceil(config.getSubjectPassMarksMaxPercent()/100.00*subjectDto.getMaxMark())
        ).intValue();

        Integer minPassMark = Double.valueOf(
                Math.ceil(config.getSubjectPassMarksMinPercent()/100.00*subjectDto.getMaxMark())
        ).intValue();

        if(patternMismatch(subjectDto.getSubjectName(), config.getSubjectNameRegexp())){
            validObj.setSuccess(false);
            validObj.setErrorMsg("Subject Name is invalid ");
        }


        if(isNotInRange(subjectDto.getMaxMark(), config.getSubjectMarkMin(), config.getSubjectMarkMax())){
            validObj.setSuccess(false);
            validObj.setErrorMsg("maxMark range is [" + config.getSubjectMarkMin().toString() + " , " + config.getSubjectMarkMax().toString() + "]");
        }

        if(isNotInRange(subjectDto.getPassMark(), minPassMark, maxPassMark)){
            validObj.setSuccess(false);
            validObj.setErrorMsg("passMark range is [" + minPassMark.toString() + " , " + maxPassMark.toString() + "]");
        }

        if(isNotInRange(subjectDto.getMaxAttempt(), config.getSubjectAttemptMin(), config.getSubjectAttemptMax())){
            validObj.setSuccess(false);
            validObj.setErrorMsg("maxAttempt range is [" +config.getSubjectAttemptMin() + " , " + config.getSubjectAttemptMax() + "]");
        }

        System.out.println(validObj.toString());
        return  validObj;
    }

    public ValidatorObject isGradeValid(GradeDto gradeDto){
        ValidatorObject validObj = new ValidatorObject(true, "");
        if(isNotInRange(gradeDto.getGradeNo(), config.getGradeNoMin(), config.getGradeNoMax())){
            validObj.setSuccess(false);
            validObj.setErrorMsg("gradeNo range is [" +config.getGradeNoMin() + " , " + config.getGradeNoMax() + "]");
        }
        return validObj;
    }

}
