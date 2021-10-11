package com.example.kenko.Teacher.CreateClass;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.fragment.app.Fragment;

import com.example.kenko.R;
import com.example.kenko.models.ClassModel;
import com.example.kenko.retrofitutil.ApiClient;
import com.example.kenko.retrofitutil.ApiInterface;
import com.example.kenko.sharedPreferences.DataLocalManager;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateClassFragment extends Fragment{
    String email = DataLocalManager.getStringEmail();

    View mView;

    Button btnTimeStart;
    TimePickerDialog pickerTimeStart;

    Button btnTimeStop;
    TimePickerDialog pickerTimeStop;

    Button btnStartDay;
    DatePickerDialog pickerDateStart;

    Button btnStopDay;
    DatePickerDialog pickerDateStop;

    EditText editClassName;
    EditText editDescript;
    EditText editAddress;
    EditText editCost;
    EditText editNumMember;

    CheckBox checkBoxMonday;
    CheckBox checkBoxTuesday;
    CheckBox checkBoxWednesday;
    CheckBox checkBoxThursday;
    CheckBox checkBoxFriday;
    CheckBox checkBoxSaturday;
    CheckBox checkBoxSunday;

    Button btnCreateClass;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        mView= inflater.inflate(R.layout.teacher_createclass,container, false);
        timeStart();
        timeStop();
        dayStart();
        dayStop();

        createClass();
        return mView;
    }

    private void timeStart(){
        btnTimeStart = mView.findViewById(R.id.btnTimeStart);
        btnTimeStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minutes = calendar.get(Calendar.MINUTE);

                pickerTimeStart = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                btnTimeStart.setText(String.format("%02d:%02d", sHour, sMinute));
                            }
                }, hour, minutes, true);
                pickerTimeStart.show();
            }
        });
    }

    private void timeStop(){
        btnTimeStop = mView.findViewById(R.id.btnTimeStop);
        btnTimeStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minutes = calendar.get(Calendar.MINUTE);

                pickerTimeStop = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                        btnTimeStop.setText(String.format("%02d:%02d", sHour, sMinute));
                    }
                }, hour, minutes, true);
                pickerTimeStop.show();
            }
        });
    }

    private void dayStart(){
        btnStartDay = mView.findViewById(R.id.btnStartDay);
        btnStartDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                pickerDateStart = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        int monthofYear = month + 1;

                        String dayStr   = String.valueOf(dayOfMonth);
                        String monthStr = String.valueOf(monthofYear);

                        if (dayStr.length() == 1)
                            dayStr = "0" + dayStr;

                        if (monthStr.length() == 1)
                            monthStr = "0" + monthStr;

                        btnStartDay.setText(year+ "-" + monthStr + "-" +dayStr);
                    }
                }, year, month, day);
                pickerDateStart.show();
            }
        });
    }

    private void dayStop(){
        btnStopDay = mView.findViewById(R.id.btnStopDay);
        btnStopDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                pickerDateStop = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        int monthofYear = month + 1;

                        String dayStr   = String.valueOf(dayOfMonth);
                        String monthStr = String.valueOf(monthofYear);

                        if (dayStr.length() == 1)
                            dayStr = "0" + dayStr;

                        if (monthStr.length() == 1)
                            monthStr = "0" + monthStr;

                        btnStopDay.setText(year+ "-" + monthStr + "-" +dayStr);
                    }
                }, year, month, day);
                pickerDateStop.show();
            }
        });
    }

    private void createClass(){
        editClassName = mView.findViewById(R.id.editClassName);
        btnTimeStart = mView.findViewById(R.id.btnTimeStart);
        btnTimeStop = mView.findViewById(R.id.btnTimeStop);
        //
        checkBoxMonday = mView.findViewById(R.id.checkBoxMonday);
        checkBoxTuesday = mView.findViewById(R.id.checkBoxTuesday);
        checkBoxWednesday = mView.findViewById(R.id.checkBoxWednesday);
        checkBoxThursday = mView.findViewById(R.id.checkBoxThursday);
        checkBoxFriday = mView.findViewById(R.id.checkBoxFriday);
        checkBoxSaturday = mView.findViewById(R.id.checkBoxSaturday);
        checkBoxSunday = mView.findViewById(R.id.checkBoxSunday);
        //
        btnStartDay = mView.findViewById(R.id.btnStartDay);
        btnStopDay = mView.findViewById(R.id.btnStopDay);
        editDescript = mView.findViewById(R.id.editDescript);
        editAddress = mView.findViewById(R.id.editAddress);
        editCost = mView.findViewById(R.id.editCost);
        editNumMember = mView.findViewById(R.id.editNumMember);

        btnCreateClass = mView.findViewById(R.id.btnCreateClass);

        btnCreateClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editClassName.getText().toString();
                String timeStart = btnTimeStart.getText().toString();
                String timeStop = btnTimeStop.getText().toString();

                // Xu ly check box
                String dayOfweek = "";
                if (checkBoxMonday.isChecked()){
                    dayOfweek = dayOfweek + checkBoxMonday.getText().toString() + " ";
                }
                if (checkBoxTuesday.isChecked()){
                    dayOfweek = dayOfweek + checkBoxTuesday.getText().toString() + " ";
                }
                if (checkBoxWednesday.isChecked()){
                    dayOfweek = dayOfweek + checkBoxWednesday.getText().toString() + " ";
                }
                if (checkBoxThursday.isChecked()){
                    dayOfweek = dayOfweek + checkBoxThursday.getText().toString() + " ";
                }
                if (checkBoxFriday.isChecked()){
                    dayOfweek = dayOfweek + checkBoxFriday.getText().toString() + " ";
                }
                if (checkBoxSaturday.isChecked()){
                    dayOfweek = dayOfweek + checkBoxSaturday.getText().toString() + " ";
                }
                if (checkBoxSunday.isChecked()){
                    dayOfweek = dayOfweek + checkBoxSunday.getText().toString();
                }

                String dayStart = btnStartDay.getText().toString();
                String dayStop = btnStopDay.getText().toString();
                String descript = editDescript.getText().toString();
                String address = editAddress.getText().toString();
                String cost = editCost.getText().toString();
                String member = editNumMember.getText().toString();
                String status = "prepare";

                Call<ClassModel> call = ApiClient.getApiClient().create(ApiInterface.class)
                                        .addClass(email, name, dayStart, dayStop, timeStart, timeStop, dayOfweek, member, cost, address, descript, status );
                call.enqueue(new Callback<ClassModel>(){

                    @Override
                    public void onResponse(Call<ClassModel> call, Response<ClassModel> response) {
                        if (response.code() == 200){
                            if (response.body().getResultCode() == 1){
                                Log.d("==================", "Duoc roi ne");
                            }else{
                                Log.d("==================", "Khong Duoc");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ClassModel> call, Throwable t) {
                        Log.d("==================", "Sao vo failure roi troi");
                    }
                });
            }
        });



    }


}
