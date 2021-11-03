package com.example.kenko.Teacher.CreateCource;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.kenko.R;
import com.example.kenko.models.CourceModel;
import com.example.kenko.retrofitutil.ApiClient;
import com.example.kenko.retrofitutil.ApiInterface;
import com.example.kenko.sharedPreferences.DataLocalManager;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateCourceFragment extends Fragment{

    String email = DataLocalManager.getStringEmail();
    TextView message;
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
        mView= inflater.inflate(R.layout.teacher_createcource,container, false);

        timeStart();
        timeStop();
        dayStart();
        dayStop();

        createCource();
        return mView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
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

    private void createCource(){

        message = mView.findViewById(R.id.message);

        editClassName = mView.findViewById(R.id.editClassName);
        btnTimeStart = mView.findViewById(R.id.btnTimeStart);
        btnTimeStop = mView.findViewById(R.id.btnTimeStop);

        checkBoxMonday = mView.findViewById(R.id.checkBoxMonday);
        checkBoxTuesday = mView.findViewById(R.id.checkBoxTuesday);
        checkBoxWednesday = mView.findViewById(R.id.checkBoxWednesday);
        checkBoxThursday = mView.findViewById(R.id.checkBoxThursday);
        checkBoxFriday = mView.findViewById(R.id.checkBoxFriday);
        checkBoxSaturday = mView.findViewById(R.id.checkBoxSaturday);
        checkBoxSunday = mView.findViewById(R.id.checkBoxSunday);

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

                // Check Empty
                if (name.equals("")){
                    message.setText("Classname is empty");
                    return;
                }
                /*
                if (timeStart.equals("HH:MM")){
                    message.setText("Starttime is empty");
                    return;
                }
                if (timeStop.equals("HH:MM")){
                    message.setText("Stoptime is empty");
                    return;
                }
                */

                if (dayOfweek.equals("")){
                    message.setText("Please choose day of week");
                    return;
                }
                /*
                if (dayStart.equals("YYYY-MM-DD")){
                    message.setText("Startday is empty");
                    return;
                }
                if (dayStop.equals("YYYY-MM-DD")){
                    message.setText("Stopday is empty");
                    return;
                }
                */
                if (descript.equals("")){
                    message.setText("Descript is empty");
                    return;
                }
                if (address.equals("")){
                    message.setText("Address is empty");
                    return;
                }
                if (cost.equals("")){
                    message.setText("Price is empty");
                    return;
                }
                if (member.equals("")){
                    message.setText("Member is empty");
                    return;
                }

                Call<CourceModel> call = ApiClient.getApiClient().create(ApiInterface.class)
                                        .createCource(email, name, descript, status, dayStart, dayStop, timeStart, timeStop, dayOfweek, member, cost, address);
                call.enqueue(new Callback<CourceModel>(){

                    @Override
                    public void onResponse(Call<CourceModel> call, Response<CourceModel> response) {
                        if (response.code() == 200){

                            if (response.body().getResultCode() == 1){

                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setTitle("Message");
                                builder.setMessage("Successfully created");

                                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        editClassName.setText("");
                                        btnTimeStart.setText("HH:MM");
                                        btnTimeStop.setText("HH:MM");
                                        // Set Check Box
                                        checkBoxMonday.setChecked(false);
                                        checkBoxTuesday.setChecked(false);
                                        checkBoxWednesday.setChecked(false);
                                        checkBoxThursday.setChecked(false);
                                        checkBoxFriday.setChecked(false);
                                        checkBoxSaturday.setChecked(false);
                                        checkBoxSunday.setChecked(false);

                                        btnStartDay.setText("YYYY-MM-DD");
                                        btnStopDay.setText("YYYY-MM-DD");
                                        editDescript.setText("");
                                        editAddress.setText("");
                                        editCost.setText("");
                                        editNumMember.setText("");

                                    }
                                });

                                AlertDialog alert = builder.create();
                                alert.show();

                            }else{

                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setTitle("Message");
                                builder.setMessage("Action is failed. Please check field");
                                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // finish();
                                    }
                                });

                                AlertDialog alert = builder.create();
                                alert.show();

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<CourceModel> call, Throwable t) {
                        // Write Something
                    }

                });
            }
        });
    }

}
