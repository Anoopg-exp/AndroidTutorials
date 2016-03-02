package com.dbflowtest.com.dbflowtest.models;

import java.util.ArrayList;

/**
 * Created by COMP on 15-02-2016.
 */
public class PatientDataHolder {

    ArrayList<Patients> PatientList;

    public ArrayList<Patients> getPatientList() {
        return PatientList;
    }

    public void setPatientList(ArrayList<Patients> patientList) {
        PatientList = patientList;
    }
}
