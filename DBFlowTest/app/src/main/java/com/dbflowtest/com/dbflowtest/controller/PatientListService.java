package com.dbflowtest.com.dbflowtest.controller;


import com.dbflowtest.com.dbflowtest.models.PatientSearchHolder;
import com.dbflowtest.com.dbflowtest.models.PatientsHolder;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


/**
 * Created by COMP on 15-02-2016.
 */
public interface PatientListService {

    @POST("Patient/FetchPatients_m")
    Call<PatientsHolder> getPatientData(@Body PatientSearchHolder patient);

}
