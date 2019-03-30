package com.hutechlab.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.hutechlab.lab7extra.R;
import com.hutechlab.model.Task;

public class TaskAdapter extends ArrayAdapter<Task> {
    Activity context;
    int resource;
    CheckBox chkComplete;
    Boolean check;
    TextView txtTaskName;

    public TaskAdapter(Activity context, int resource) {
        super(context, resource);
        this.context= context;
        this.resource= resource;
    }

//    public void getCheckbox(Boolean a){
//        chkComplete.setEnabled(a);
//    }

    @Override
    public View getView(int position,View convertView, ViewGroup parent) {
        LayoutInflater inflatter= this.context.getLayoutInflater();
        final View custom= inflatter.inflate(this.resource, null);

        ImageView imgEmp= custom.findViewById(R.id.imgEmp);
        txtTaskName= custom.findViewById(R.id.txtTaskName);
        TextView txtTaskInfo= custom.findViewById(R.id.txtTaskInfo);
        TextView txtTaskTime= custom.findViewById(R.id.txtTaskTime);
        final CheckBox chkDel= custom.findViewById(R.id.chkDel);
        chkComplete = custom.findViewById(R.id.chkComplete);
        final Task task= getItem(position);
        if(task.getTaskEmp().equals("m")){
            imgEmp.setImageResource(R.drawable.doge);
        }
        else{
            imgEmp.setImageResource(R.drawable.husky);
        }
        txtTaskName.setText(task.getTaskName());
        txtTaskInfo.setText(task.getTaskInfo());
        txtTaskTime.setText("Ngày: "+task.getTaskDate());
        txtTaskTime.setText(txtTaskTime.getText().toString()+ " Giờ: "+ task.getTaskTime());

        chkDel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(chkDel.isChecked()){
                    task.setChecked(true);
                }
                else{
                    task.setChecked(false);
                }
            }
        });

        chkComplete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(chkComplete.isChecked()){
                    task.setChecked(true);

                }
                else{
                    task.setChecked(false);
//                    chkComplete.setEnabled(true);
                }
            }
        });

        return custom;
    }

    public void getName(){
        txtTaskName.setText("Hoan thanh");
    }

}
