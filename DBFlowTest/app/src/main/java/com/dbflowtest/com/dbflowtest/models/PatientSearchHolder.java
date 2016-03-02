package com.dbflowtest.com.dbflowtest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by COMP on 16-02-2016.
 */
public class PatientSearchHolder {

    @SerializedName("Ward")
    ArrayList<String> Ward;
    @SerializedName("Speciality")
    ArrayList<String> Speciality;
    @SerializedName("patientSearchDTO")
    PatientSearchDto patientSearchDTO;

    public ArrayList<String> getWard() {
        return Ward;
    }

    public void setWard(ArrayList<String> ward) {
        Ward = ward;
    }

    public ArrayList<String> getSpeciality() {
        return Speciality;
    }

    public void setSpeciality(ArrayList<String> speciality) {
        Speciality = speciality;
    }

    public PatientSearchDto getPatientSearchDTO() {
        return patientSearchDTO;
    }

    public void setPatientSearchDTO(PatientSearchDto patientSearchDTO) {
        this.patientSearchDTO = patientSearchDTO;
    }
}
