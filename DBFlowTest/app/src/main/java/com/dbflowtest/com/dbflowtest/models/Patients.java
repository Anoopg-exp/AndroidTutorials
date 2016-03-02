package com.dbflowtest.com.dbflowtest.models;

import com.dbflowtest.com.dbflowtest.db.ColonyDatabase;

import com.dbflowtest.com.dbflowtest.db.PatientDataBase;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by COMP on 15-02-2016.
 */
@ModelContainer
@Table(database = PatientDataBase.class)
public class Patients extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    private int id;

    @Column
    @SerializedName("PatientId")
    private String PatientId;

    @Column
    @SerializedName("AdmissionDate")
    private String AdmissionDate;

    @Column
    @SerializedName("DischargeDate")
    private String DischargeDate;

    @Column
    @SerializedName("UR")
    private String UR;

    @Column
    @SerializedName("FirstName")
    private String FirstName;

    @Column
    @SerializedName("LastName")
    private String LastName;

    @Column
    @SerializedName("Initials")
    private String Initials;

    @Column
    @SerializedName("Address1")
    private String Address1;

    @Column
    @SerializedName("Address2")
    private String Address2;

    @Column
    @SerializedName("Ward")
    private String Ward;

    @Column
    @SerializedName("speciality")
    private String speciality;

    @Column
    @SerializedName("AdmissionStatus")
    private String AdmissionStatus;

    @Column
    @SerializedName("AdCat")
    private String AdCat;

    @Column
    @SerializedName("Message")
    private String Message;

    @Column
    @SerializedName("InsurancePolicyNo")
    private String InsurancePolicyNo;

    @Column
    @SerializedName("Insurer")
    private String Insurer;

    @Column
    @SerializedName("DoB")
    private String DoB;

    @Column
    @SerializedName("Gender")

    private String Gender;

    @Column
    @SerializedName("RefDate")
    private String RefDate;

    @Column
    @SerializedName("RefDoc")
    private String RefDoc;

    @Column
    @SerializedName("RefDocProviderNo")
    private String RefDocProviderNo;

    @Column
    @SerializedName("Unit")
    private String Unit;

    @Column
    @SerializedName("Bed")
    private String Bed;

    @Column
    @SerializedName("AdDate")
    private String AdDate;

    @Column
    @SerializedName("VisitNo")
    private String VisitNo;

    @Column
    @SerializedName("Medicare")
    private String Medicare;

    @Column
    @SerializedName("Episodes")
    private String Episodes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatientId() {
        return PatientId;
    }

    public void setPatientId(String patientId) {
        PatientId = patientId;
    }

    public String getAdmissionDate() {
        return AdmissionDate;
    }

    public void setAdmissionDate(String admissionDate) {
        AdmissionDate = admissionDate;
    }

    public String getDischargeDate() {
        return DischargeDate;
    }

    public void setDischargeDate(String dischargeDate) {
        DischargeDate = dischargeDate;
    }

    public String getUR() {
        return UR;
    }

    public void setUR(String UR) {
        this.UR = UR;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getInitials() {
        return Initials;
    }

    public void setInitials(String initials) {
        Initials = initials;
    }

    public String getAddress1() {
        return Address1;
    }

    public void setAddress1(String address1) {
        Address1 = address1;
    }

    public String getAddress2() {
        return Address2;
    }

    public void setAddress2(String address2) {
        Address2 = address2;
    }

    public String getWard() {
        return Ward;
    }

    public void setWard(String ward) {
        Ward = ward;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getAdmissionStatus() {
        return AdmissionStatus;
    }

    public void setAdmissionStatus(String admissionStatus) {
        AdmissionStatus = admissionStatus;
    }

    public String getAdCat() {
        return AdCat;
    }

    public void setAdCat(String adCat) {
        AdCat = adCat;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getInsurancePolicyNo() {
        return InsurancePolicyNo;
    }

    public void setInsurancePolicyNo(String insurancePolicyNo) {
        InsurancePolicyNo = insurancePolicyNo;
    }

    public String getInsurer() {
        return Insurer;
    }

    public void setInsurer(String insurer) {
        Insurer = insurer;
    }

    public String getDoB() {
        return DoB;
    }

    public void setDoB(String doB) {
        DoB = doB;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getRefDate() {
        return RefDate;
    }

    public void setRefDate(String refDate) {
        RefDate = refDate;
    }

    public String getRefDoc() {
        return RefDoc;
    }

    public void setRefDoc(String refDoc) {
        RefDoc = refDoc;
    }

    public String getRefDocProviderNo() {
        return RefDocProviderNo;
    }

    public void setRefDocProviderNo(String refDocProviderNo) {
        RefDocProviderNo = refDocProviderNo;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public String getBed() {
        return Bed;
    }

    public void setBed(String bed) {
        Bed = bed;
    }

    public String getAdDate() {
        return AdDate;
    }

    public void setAdDate(String adDate) {
        AdDate = adDate;
    }

    public String getVisitNo() {
        return VisitNo;
    }

    public void setVisitNo(String visitNo) {
        VisitNo = visitNo;
    }

    public String getMedicare() {
        return Medicare;
    }

    public void setMedicare(String medicare) {
        Medicare = medicare;
    }

    public String getEpisodes() {
        return Episodes;
    }

    public void setEpisodes(String episodes) {
        Episodes = episodes;
    }
}
