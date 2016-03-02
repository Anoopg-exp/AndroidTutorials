package com.dbflowtest.com.dbflowtest.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dbflowtest.com.dbflowtest.R;
import com.dbflowtest.com.dbflowtest.models.Patients;

import java.util.ArrayList;

/**
 * Created by COMP on 15-02-2016.
 */
public class PatientAdapter extends BaseAdapter {


    Activity activity;
    ArrayList<Patients> patientsList;
    LayoutInflater inflater;

    public PatientAdapter(Activity activity, ArrayList<Patients> patientsList) {
        this.activity = activity;
        this.patientsList = patientsList;
        inflater = (LayoutInflater) activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        if (patientsList != null)
            return patientsList.size();
        else
            return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {

            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            convertView = inflater.inflate(R.layout.tabitem, null);

            /****** View Holder Object to contain tabitem.xml file elements ******/

            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.number = (TextView) convertView.findViewById(R.id.number);

            /************  Set holder with LayoutInflater ************/
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

         if(!patientsList.isEmpty()){
            // holder.name.setText(patientsList.get(position).getFirstName());
           //  holder.number.setText(patientsList.get(position).getDoB());
         }

        return convertView;
    }


    class ViewHolder {
        TextView name;
        TextView number;
    }

}
