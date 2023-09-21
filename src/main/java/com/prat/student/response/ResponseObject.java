package com.prat.student.response;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;


public class ResponseObject {

    public static ResponseEntity<ResponseDataObject> getResponseObject(ResponseDataObject data){
        return new ResponseEntity<ResponseDataObject>( data, data.statusCode());
    }

}
