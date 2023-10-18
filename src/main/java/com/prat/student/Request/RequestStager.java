package com.prat.student.Request;

public interface RequestStager {

    public void prepareDataAndForwardToRequestHandler(String request);
    public Object convertDataFieldBasedOnEntity(Message data);

}
