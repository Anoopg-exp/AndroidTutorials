package com.dbflowtest.com.dbflowtest.models;

/**
 * Created by COMP on 15-02-2016.
 */
public class PatientsHolder {

    private String Status;

    private String Message;

    private PatientDataHolder Data;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public PatientDataHolder getData() {
        return Data;
    }

    public void setData(PatientDataHolder data) {
        Data = data;
    }
}
