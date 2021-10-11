package com.example.kenko.Teacher.Profile;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kenko.R;
import com.example.kenko.models.UsersModel;
import com.example.kenko.retrofitutil.ApiClient;
import com.example.kenko.retrofitutil.ApiInterface;
import com.example.kenko.sharedPreferences.DataLocalManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePwdProfile extends AppCompatActivity {
    String email = DataLocalManager.getStringEmail();
    ImageView imgBack;

    EditText editOldPassword;
    EditText editNewPassword;
    EditText editCnfPassword;
    Button btnUpdatePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_profile_changepwd);



        goBack();
        changePassword();
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

    private void changePassword(){
        editOldPassword = findViewById(R.id.editOldPassword);
        editNewPassword = findViewById(R.id.editNewPassword);
        editCnfPassword = findViewById(R.id.editCnfPassword);

        btnUpdatePassword = findViewById(R.id.btnUpdatePassword);
        btnUpdatePassword.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String oldpassword = editOldPassword.getText().toString();
                String newpassword = editNewPassword.getText().toString();
                String cnfnewpassword = editCnfPassword.getText().toString();

                Call<UsersModel> call = ApiClient.getApiClient().create(ApiInterface.class).performChangePwd(email, oldpassword, newpassword, cnfnewpassword);
                call.enqueue(new Callback<UsersModel>(){
                    @Override
                    public void onResponse(Call<UsersModel> call, Response<UsersModel> response) {
                        if (response.code() == 200){
                            if (response.body().getPassword() == 1){
                                Log.d("==============","Thanh Cong");
                            }else{
                                if (response.body().getPassword() == 2){
                                    Log.d("==============","Mat Khau Xac Nhan Khong Khop");
                                }else{
                                    Log.d("==============","Mat Khau Cu Khong Dung");
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<UsersModel> call, Throwable t) {

                    }
                });
            }
        });
    }
}

