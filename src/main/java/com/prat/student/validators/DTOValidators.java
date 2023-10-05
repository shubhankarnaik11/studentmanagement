package com.prat.student.validators;

import com.prat.student.config.ValidationsConfig;
import com.prat.student.dto.SubjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.prat.student.validators.baseValidators.NumericValidator.*;
import static com.prat.student.validators.baseValidators.StringValidator.*;
@Component
public class DTOValidators {

    @Autowired
    ValidationsConfig config;

    public  ValidatorObject isSubjectValid(SubjectDto subjectDto){

        System.out.println(config.getSubjectMarkMin());

        ValidatorObject validObj = new ValidatorObject(true, "");
        if(isZero(subjectDto.getMaxMark())){
            validObj.setSuccess(false);
            validObj.setErrorMsg("maxMark must not be zero");
            return validObj;//returning because next step it is in the denominator
        }

        Integer maxPassMark = Double.valueOf(
                Math.ceil((double)(config.getSubjectPassMarksMaxPercent()/subjectDto.getMaxMark())*100)
        ).intValue();

        Integer minPassMark = Double.valueOf(
                Math.ceil((double)(config.getSubjectPassMarksMinPercent()/subjectDto.getMaxMark())*100)
        ).intValue();

        if(patternMismatch(subjectDto.getSubjectName(), config.getSubjectNameRegexp())){
            validObj.setSuccess(false);
            validObj.setErrorMsg("Subject Name is invalid ");
        }

        if(greaterThan(subjectDto.getMaxMark(), config.getSubjectMarkMax())){
            validObj.setSuccess(false);
            validObj.setErrorMsg("maxMark must be less than" + config.getSubjectMarkMax().toString());
        }
        if(lesserThan(subjectDto.getMaxMark(), config.getSubjectMarkMin())){
            validObj.setSuccess(false);
            validObj.setErrorMsg("maxMark must be greater than" + config.getSubjectMarkMax().toString());
        }

        if(greaterThan(subjectDto.getPassMark(), maxPassMark)){
            validObj.setSuccess(false);
            validObj.setErrorMsg("passMark must be lesser than" + maxPassMark);
        }
        if(lesserThan(subjectDto.getPassMark(), minPassMark)){
            validObj.setSuccess(false);
            validObj.setErrorMsg("passMark must be greater than" + minPassMark);
        }

        if(greaterThan(subjectDto.getMaxAttempt(), config.getSubjectAttemptMax())){
            validObj.setSuccess(false);
            validObj.setErrorMsg("passMark must be lesser than" + maxPassMark);
        }
        if(lesserThan(subjectDto.getMaxAttempt(), config.getSubjectAttemptMin())){
            validObj.setSuccess(false);
            validObj.setErrorMsg("passMark must be greater than" + minPassMark);
        }

        return  validObj;
    }

}
