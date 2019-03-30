package com.hutechlab.lab7extra;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.Time;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.hutechlab.adapter.TaskAdapter;
import com.hutechlab.model.Task;

import static android.graphics.Color.rgb;


//import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button btnDelete, btnSave, btnDate, btnTime, btnComplete;
    ListView lvEmp;
    TabHost host;
    EditText edtTaskName, edtTaskInfo;




    RadioGroup radgEmp;

    TaskAdapter taskAdapter;

    TextView txtDate, txtTime;


    //private TextView dateTimeDisplay;
     Calendar calendar;
     SimpleDateFormat dateFormat;
     String date;
     //calendar = Calendar.getInstance();

    Date currentTime = Calendar.getInstance().getTime();


    String selectedDate, selectedTime;

    private int mYear, mMonth, mDay, mHour, mMinute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadControls();
        addEvents();
    }

    private void addEvents() {
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                builder1.setMessage("Xóa các mục đã chọn?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Vâng",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                int cout= 0;
                                for(int i=taskAdapter.getCount()-1; i>=0; i--){
                                    if(taskAdapter.getItem(i).isChecked()){
                                        taskAdapter.remove(taskAdapter.getItem(i));
                                        cout+=1;
                                    }
                                }
                                Toast.makeText(MainActivity.this, "Đã xóa ("+cout+") mục!", Toast.LENGTH_SHORT).show();
                            }
                        });

                builder1.setNegativeButton(
                        "Thôi",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                return;
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Hoàn thành các mục đã chọn?");
                builder.setCancelable(true);

                builder.setPositiveButton(
                        "Vâng",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                int count= 0;
                                for(int i=taskAdapter.getCount()-1; i>=0; i--){
                                    if(taskAdapter.getItem(i).isChecked()){
                                        //taskAdapter.remove(taskAdapter.getItem(i));
                                        //btnComplete.setEnabled(false);
//                                        lvEmp.(!taskAdapter.getItem(i).isChecked());
//                                        taskAdapter.getCheckbox(!taskAdapter.getItem(i).isChecked());
                                        lvEmp.getChildAt(i).setEnabled(false);
                                        count+=1;
                                    }
                                }
                                Toast.makeText(MainActivity.this, "Đã hoàn thành ("+count+") mục!", Toast.LENGTH_SHORT).show();
                            }
                        });

                builder.setNegativeButton(
                        "Thôi",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                return;
                            }
                        });

                AlertDialog alert11 = builder.create();
                alert11.show();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task task= new Task();
                task.setTaskName(edtTaskName.getText().toString());
                task.setTaskInfo(edtTaskInfo.getText().toString());
                if(radgEmp.getCheckedRadioButtonId() == R.id.radMe){
                    task.setTaskEmp("m");
                }
                else{
                    task.setTaskEmp("b");
                }
                task.setTaskDate(selectedDate);
                task.setTaskTime(selectedTime);

                taskAdapter.add(task);
                taskAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Đã thêm 1 công việc mới", Toast.LENGTH_SHORT).show();

                host.setCurrentTab(1);
            }
        });
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateClicked();
            }
        });

        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeClicked();
            }
        });
    }

    private void TimeClicked() {
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        if(mHour>hourOfDay||mMinute>minute)
                        {
                            txtTime.setText(hourOfDay + ":" + minute +" thời  phải lớn hơn hiện tại");
                            txtTime.setTextColor(rgb(244, 66, 66));
                            //txtTime.setError("Thời gian phải lớn hơn thời gian hiện tại");
                            btnSave.setEnabled(false);
                        }
                        else {
                            btnSave.setEnabled(true);
                            txtTime.setText(hourOfDay + ":" + minute);
                            txtTime.setTextColor(rgb(65, 169, 244));
                            selectedTime= hourOfDay + ":" + minute;

                        }
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    private void DateClicked() {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

//                        calendar = Calendar.getInstance();
//                        dateFormat = new SimpleDateFormat("dd");
//                        date = dateFormat.format(calendar.getTime());
//
//
                        if(mMonth>monthOfYear || mYear>year || mDay>dayOfMonth)
                        {
                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year + " thời  phải lớn hơn hiện tại");
                            txtDate.setTextColor(rgb(244, 66, 66));
                            //txtDate.setError("ngày phải lớn hơn thời điểm hiện tại");
                            btnSave.setEnabled(false);
                        }
                        else {
                            btnSave.setEnabled(true);
                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            txtDate.setTextColor(rgb(65, 169, 244));
                            selectedDate= dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        }


                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void loadControls() {
        btnDelete= findViewById(R.id.btnDelete);
        btnComplete = findViewById(R.id.btnComplete);
        btnSave= findViewById(R.id.btnSave);
        btnDate= findViewById(R.id.btnDate);
        btnTime= findViewById(R.id.btnTime);

        txtDate= findViewById(R.id.txtDate);
        txtTime= findViewById(R.id.txtTime);

        host = findViewById(R.id.tabHost);
        host.setup();
        TabHost.TabSpec spec = host.newTabSpec("Tab One").setIndicator("Tạo công việc");
        spec.setContent(R.id.tabEdit);
        host.addTab(spec);
        //Tab 2
        spec = host.newTabSpec("Tab Two").setIndicator("Danh sách công việc");
        spec.setContent(R.id.tabList);
        host.addTab(spec);

        edtTaskName= findViewById(R.id.edtTaskName);
        edtTaskInfo= findViewById(R.id.edtTaskInfo);

        edtTaskInfo.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if( edtTaskInfo.getText().toString().length() == 0 ){
                    btnSave.setEnabled(false);
                    edtTaskInfo.setError( "Nội dung mô  không được rỗng " );
                }
                if(!s.equals("") ) {
                    //btnSave.setEnabled(true);
                }
            }



            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void afterTextChanged(Editable s) {

            }
        });


        edtTaskName.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if( edtTaskName.getText().toString().length() == 0 ) {
                    edtTaskName.setError("Nội dung tên không được rong ");
                    btnSave.setEnabled(false);
                }
                if(!s.equals("") ) {
                   // btnSave.setEnabled(true);

                }
            }



            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void afterTextChanged(Editable s) {

            }
        });
        radgEmp= findViewById(R.id.radgEmp);
        lvEmp= findViewById(R.id.lvEmp);

        taskAdapter= new TaskAdapter(MainActivity.this, R.layout.listitem);
        lvEmp.setAdapter(taskAdapter);
    }
}
