package com.dbflowtest.com.dbflowtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.dbflowtest.com.dbflowtest.Supporter.DbUtils;
import com.dbflowtest.com.dbflowtest.adapters.PatientRecyclerAdapter;
import com.dbflowtest.com.dbflowtest.controller.PatientListService;
import com.dbflowtest.com.dbflowtest.models.PatientSearchDto;
import com.dbflowtest.com.dbflowtest.models.PatientSearchHolder;
import com.dbflowtest.com.dbflowtest.models.Patients;
import com.dbflowtest.com.dbflowtest.models.PatientsHolder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by COMP on 15-02-2016.
 */

public class RetrofitTestActivity extends AppCompatActivity {

    String LOG_TAG = getClass().getName();
    RecyclerView patientRecyclerView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        patientRecyclerView = (RecyclerView) findViewById(R.id.patientRecycleView);
        patientRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar = (ProgressBar) findViewById(R.id.pgbar);


        Log.i(LOG_TAG,"URL from staging and production" +Hosts.BASE_URL);
        fetchPatientList();

    }

    private void fetchPatientList() {
        progressBar.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        PatientListService service = retrofit.create(PatientListService.class);
        Call<PatientsHolder> call = service.getPatientData(createDummyData());
        call.enqueue(new Callback<PatientsHolder>() {
            @Override
            public void onResponse(Call<PatientsHolder> call, Response<PatientsHolder> response) {
                PatientsHolder patientsHolder = response.body();
                Log.d(LOG_TAG, "data :" + patientsHolder);

                if (patientsHolder.getData() != null) {
                    initDataBase(patientsHolder.getData().getPatientList());
                }

                getAllPatientFromDb();
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<PatientsHolder> call, Throwable t) {
                Log.e(LOG_TAG, "data :", t);
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    private void initDataBase(ArrayList<Patients> patientList) {
        DbUtils.addPatients(patientList);
    }

    private void getAllPatientFromDb() {
        List<Patients> patientsArrayList = DbUtils.getAllPatients();
        PatientRecyclerAdapter adapter = new PatientRecyclerAdapter(
                patientsArrayList);
        patientRecyclerView.setAdapter(adapter);
    }

    /**
     * Creates dummy data to fetch patient list...
     * @return
     */
    private PatientSearchHolder createDummyData() {

        PatientSearchHolder patient = new PatientSearchHolder();
        PatientSearchDto dto = new PatientSearchDto();
        dto.setAppCode("BillingApplication");
        dto.setSiteCode("MHS");
        dto.setUR(null);
        dto.setUserId("8d6f53fa-2e8b-43b1-8bc9-212efe1dd695");
        patient.setPatientSearchDTO(dto);
        return patient;
    }


}
