package com.dbflowtest.com.dbflowtest.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dbflowtest.com.dbflowtest.R;
import com.dbflowtest.com.dbflowtest.models.Patients;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by COMP on 16-02-2016.
 */
public class PatientRecyclerAdapter extends RecyclerView.Adapter<PatientRecyclerAdapter.CustomViewHolder>{

    List<Patients>  patientList;
    public PatientRecyclerAdapter(List<Patients>  PatientList){
        this.patientList =PatientList;
    }

    @Override
    public int getItemCount() {

        return (patientList==null ? 0:patientList.size());
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tabitem, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.name.setText(patientList.get(position).getFirstName());
        holder.number.setText(patientList.get(position).getUR());
        holder.name.setOnClickListener(clickListener);
        holder.number.setOnClickListener(clickListener);
        holder.name.setTag(holder);
        holder.number.setTag(holder);


    }


    /**
     * Custom class to hold views
     */
    public class CustomViewHolder extends  RecyclerView.ViewHolder{

        protected TextView name;
        protected TextView number;


        public CustomViewHolder(View view){
            super(view);
            this.name = (TextView) view.findViewById(R.id.name);
            this.number = (TextView) view.findViewById(R.id.number);

        }

    }
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            CustomViewHolder holder = (CustomViewHolder) view.getTag();
            int position = holder.getLayoutPosition();

            Patients patients = patientList.get(position);
            Log.d("Adapter","Name F "+patients.getFirstName());
            Log.d("Adapter","Name L "+patients.getLastName());
            //Toast.makeText(ac, patients.getFirstName(), Toast.LENGTH_SHORT).show();
        }
    };

}
