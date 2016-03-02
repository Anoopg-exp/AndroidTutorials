package com.dbflowtest.com.dbflowtest.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dbflowtest.com.dbflowtest.R;
import com.dbflowtest.com.dbflowtest.models.Ant;
import com.dbflowtest.com.dbflowtest.models.Patients;

import java.util.List;

/**
 * Created by COMP on 16-02-2016.
 */
public class AntRecyclerAdapter extends RecyclerView.Adapter<AntRecyclerAdapter.CustomViewHolder>{

    List<Ant>  antList;
    public AntRecyclerAdapter(List<Ant> antList){
        this.antList =antList;
    }

    @Override
    public int getItemCount() {

        return (antList==null ? 0:antList.size());
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ant_item, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.ant_id.setText(String.valueOf(antList.get(position).id));
        holder.ant_type.setText(antList.get(position).type);
        holder.ant_isMale.setText(String.valueOf(antList.get(position).isMale));
        holder.ant_name.setText(antList.get(position).name);
        holder.ant_id.setOnClickListener(clickListener);
        holder.ant_type.setOnClickListener(clickListener);
        holder.ant_isMale.setOnClickListener(clickListener);
        holder.ant_id.setTag(holder);
        holder.ant_type.setTag(holder);
        holder.ant_isMale.setTag(holder);
    }


    /**
     * Custom class to hold views
     */
    public class CustomViewHolder extends  RecyclerView.ViewHolder{

        protected TextView ant_id;
        protected TextView ant_type;
        protected TextView ant_isMale;
        protected TextView ant_name;
        public CustomViewHolder(View view){
            super(view);
            this.ant_id = (TextView) view.findViewById(R.id.ant_id);
            this.ant_type = (TextView) view.findViewById(R.id.ant_type);
            this.ant_isMale = (TextView) view.findViewById(R.id.ant_isMale);
            this.ant_name =(TextView)view.findViewById(R.id.ant_name);
        }

    }
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            CustomViewHolder holder = (CustomViewHolder) view.getTag();
            int position = holder.getLayoutPosition();

            Ant ant = antList.get(position);
            Log.d("Adapter","Ant Id: "+ant.id);
            Log.d("Adapter","Ant Type: "+ant.type);
            //Toast.makeText(ac, patients.getFirstName(), Toast.LENGTH_SHORT).show();
        }
    };

}
