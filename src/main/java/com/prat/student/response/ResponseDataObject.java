package com.prat.student.response;

import org.springframework.http.HttpStatus;


public record ResponseDataObject(HttpStatus statusCode, Object data, String msg, boolean success) { }
