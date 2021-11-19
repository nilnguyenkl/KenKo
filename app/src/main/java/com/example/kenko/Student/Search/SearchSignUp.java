package com.example.kenko.Student.Search;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kenko.R;
import com.example.kenko.models.CourceDetailsModel;
import com.example.kenko.models.TeacherModel;
import com.example.kenko.retrofitutil.ApiClient;
import com.example.kenko.retrofitutil.ApiInterface;
import com.example.kenko.sharedPreferences.DataLocalManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchSignUp extends AppCompatActivity {

    String idCource = DataLocalManager.getStringIdCource();
    String emailLocal = DataLocalManager.getStringEmail();

    private ImageView imgBack;

    TextView startDay;
    TextView nameCource;
    TextView stopDay;
    TextView startTime;
    TextView stopTime;
    TextView dayofweek;
    TextView address;
    TextView descript;
    TextView price;
    TextView member;
    TextView nameTeacher;

    Button btnSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_search_signup);
        displaySignUpCource();
        signUpToParticipant();
        switchBack();

    }

    private void switchBack(){
        imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void displaySignUpCource(){
        nameCource = findViewById(R.id.nameCource);
        startDay = findViewById(R.id.startDay);
        stopDay = findViewById(R.id.stopDay);
        startTime = findViewById(R.id.startTime);
        stopTime = findViewById(R.id.stopTime);
        dayofweek = findViewById(R.id.dayofweek);
        address = findViewById(R.id.address);
        descript = findViewById(R.id.descript);
        price = findViewById(R.id.price);
        member = findViewById(R.id.member);
        nameTeacher = findViewById(R.id.nameTeacher);

        Call<CourceDetailsModel> call = ApiClient.getApiClient().create(ApiInterface.class).displayCourceToSignUp(idCource);
        call.enqueue(new Callback<CourceDetailsModel>() {

            @Override
            public void onResponse(Call<CourceDetailsModel> call, Response<CourceDetailsModel> response) {
                nameCource.setText(response.body().getNameCource().toString());
                startDay.setText(response.body().getStartDay().toString());
                stopDay.setText(response.body().getStopDay().toString());
                startTime.setText(response.body().getStartTime().toString());
                stopTime.setText(response.body().getStopTime().toString());
                dayofweek.setText(response.body().getDaysOfWeek().toString());
                address.setText(response.body().getAddress().toString());
                descript.setText(response.body().getDescript().toString());
                price.setText(response.body().getPrice().toString());
                member.setText(response.body().getParticipant().toString() + "/" + response.body().getMember().toString());
                nameTeacher.setText(response.body().getFirstname().toString() + " " + response.body().getLastname().toString());

                nameTeacher.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openInforTeacher(Gravity.CENTER, response.body().getEmail());
                        // Toast.makeText(SearchSignUp.this, response.body().getEmail(), Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onFailure(Call<CourceDetailsModel> call, Throwable t) {
                // Write something
            }
        });
    }

    private void openInforTeacher(int gravity, String email){

        final Dialog dialog = new Dialog(SearchSignUp.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.teacher_layout_information);

        Window window = dialog.getWindow();
        if (window == null){
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        if (Gravity.CENTER == gravity){
            dialog.setCancelable(true);
        }else {
            dialog.setCancelable(false);
        }

        TextView textName = dialog.findViewById(R.id.textName);
        TextView textEmail = dialog.findViewById(R.id.textEmail);
        TextView textPhone = dialog.findViewById(R.id.textPhone);
        TextView textAddress = dialog.findViewById(R.id.textAddress);
        TextView textSpecialized = dialog.findViewById(R.id.textSpecialized);
        TextView textDateOfIssue = dialog.findViewById(R.id.textDateOfIssue);
        TextView textPlaceOfIssue = dialog.findViewById(R.id.textPlaceOfIssue);
        TextView textWorkplace = dialog.findViewById(R.id.textWorkplace);

        Call<TeacherModel> call = ApiClient.getApiClient().create(ApiInterface.class).displayInforTeacher(email);
        call.enqueue(new Callback<TeacherModel>() {
            @Override
            public void onResponse(Call<TeacherModel> call, Response<TeacherModel> response) {
                textName.setText(response.body().getFirstname().toString() + " " + response.body().getLastname().toString());
                textEmail.setText(response.body().getEmail().toString());
                textPhone.setText(response.body().getPhone().toString());
                textAddress.setText(response.body().getAddress().toString());
                textSpecialized.setText(response.body().getSpecialize().toString());
                textDateOfIssue.setText(response.body().getDateOfIssue().toString());
                textPlaceOfIssue.setText(response.body().getPlaceOfIssue().toString());
                textWorkplace.setText(response.body().getWorkPlace().toString());
            }

            @Override
            public void onFailure(Call<TeacherModel> call, Throwable t) {
                // Write something
            }
        });

        Button btnCancel = dialog.findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void signUpToParticipant(){
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<CourceDetailsModel> call = ApiClient.getApiClient().create(ApiInterface.class).signUpToParticipant(idCource, emailLocal);
                call.enqueue(new Callback<CourceDetailsModel>(){
                    @Override
                    public void onResponse(Call<CourceDetailsModel> call, Response<CourceDetailsModel> response) {
                        if (response.code() == 200){
                            if (response.body().getStatus_code().equals("kok")){
                                AlertDialog.Builder builder = new AlertDialog.Builder(SearchSignUp.this);
                                builder.setTitle("Message");
                                builder.setMessage("Attended the course");
                                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                });
                                AlertDialog alert = builder.create();
                                alert.show();
                            }else{
                                if (response.body().getStatus_code().equals("ok")){
                                    AlertDialog.Builder builder = new AlertDialog.Builder(SearchSignUp.this);
                                    builder.setTitle("Message");
                                    builder.setMessage("Registered successfully");
                                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish();
                                        }
                                    });
                                    AlertDialog alert = builder.create();
                                    alert.show();
                                }else{
                                    AlertDialog.Builder builder = new AlertDialog.Builder(SearchSignUp.this);
                                    builder.setTitle("Message");
                                    builder.setMessage("Registration failed");
                                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish();
                                        }
                                    });
                                    AlertDialog alert = builder.create();
                                    alert.show();
                                }
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<CourceDetailsModel> call, Throwable t) {
                        // Write something
                    }
                });
            }
        });
    }
}