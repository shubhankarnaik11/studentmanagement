package com.prat.student.validators;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//@check if all the annotations are required in the code before submission
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class ValidatorObject {
    boolean success;
    String errorMsg;

    public void setErrorMsg(String errorMsg){
        this.errorMsg += "\n" + errorMsg;
    }

    public String toString(){
        return "Success : " + this.success + "\n" + "Error Message : " + this.errorMsg;
    }

}
