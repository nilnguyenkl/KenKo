package com.example.kenko.Teacher.Profile;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kenko.R;
import com.example.kenko.models.DegreeModel;
import com.example.kenko.retrofitutil.ApiClient;
import com.example.kenko.retrofitutil.ApiInterface;
import com.example.kenko.sharedPreferences.DataLocalManager;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DegreeProfile extends AppCompatActivity {
    String email = DataLocalManager.getStringEmail();

    ImageView imgBack;

    EditText editSpecialized;

    EditText editDateOfIssue;
    DatePickerDialog pickerDateOfIssue;

    EditText editPlaceOfIssue;
    EditText editWorkplace;

    Button btnUpdateDegree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_profile_degree);
        showDateOfIssue();
        updateDegree();

        goBack();
    }

    private void goBack(){
        imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void showDateOfIssue(){
        editDateOfIssue = findViewById(R.id.editDateOfIssue);
        editDateOfIssue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                pickerDateOfIssue = new DatePickerDialog( DegreeProfile.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        int monthofYear = month + 1;

                        String dayStr   = String.valueOf(dayOfMonth);
                        String monthStr = String.valueOf(monthofYear);

                        if (dayStr.length() == 1)
                            dayStr = "0" + dayStr;

                        if (monthStr.length() == 1)
                            monthStr = "0" + monthStr;

                        editDateOfIssue.setText(year+ "-" + monthStr + "-" +dayStr);
                    }
                }, year, month, day);
                pickerDateOfIssue.show();
            }
        });
    }

    private void updateDegree(){
        editDateOfIssue = findViewById(R.id.editDateOfIssue);
        editSpecialized = findViewById(R.id.editSpecialized);
        editPlaceOfIssue = findViewById(R.id.editPlaceOfIssue);
        editWorkplace = findViewById(R.id.editWorkplace);

        btnUpdateDegree = findViewById(R.id.btnUpdateDegree);

        Call<DegreeModel> call = ApiClient.getApiClient().create(ApiInterface.class).displayDegree(email);
        call.enqueue(new Callback<DegreeModel>(){

            @Override
            public void onResponse(Call<DegreeModel> call, Response<DegreeModel> response) {
                if (response.code() == 200){
                    if (response.body().getResultCode() == 1){
                        editDateOfIssue.setText(response.body().getDateOfIssue().toString());
                        editSpecialized.setText(response.body().getSpecialize().toString());
                        editPlaceOfIssue.setText(response.body().getPlaceOfIssue().toString());
                        editWorkplace.setText(response.body().getWorkPlace().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<DegreeModel> call, Throwable t) {
                // Write something
            }
        });

        btnUpdateDegree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String specialize = editSpecialized.getText().toString();
                String date_of_issue = editDateOfIssue.getText().toString();
                String place_of_issue = editPlaceOfIssue.getText().toString();
                String workplace = editWorkplace.getText().toString();

                Call<DegreeModel> call = ApiClient.getApiClient().create(ApiInterface.class).editDegree(email, specialize, date_of_issue, place_of_issue, workplace);
                call.enqueue(new Callback<DegreeModel>(){

                    @Override
                    public void onResponse(Call<DegreeModel> call, Response<DegreeModel> response) {
                        if (response.code() == 200){
                            if (response.body().getResultCode() == 1){
                                AlertDialog.Builder builder = new AlertDialog.Builder(DegreeProfile.this);
                                builder.setTitle("Message");
                                builder.setMessage("Successfully uploaded");
                                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // finish();
                                    }
                                });
                                AlertDialog alert = builder.create();
                                alert.show();
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(DegreeProfile.this);
                                builder.setTitle("Message");
                                builder.setMessage("It is failed");
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
                    public void onFailure(Call<DegreeModel> call, Throwable t) {
                        // Write something
                    }
                });
            }
        });
    }
}

