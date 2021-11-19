package com.example.kenko;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

    TextView message;

    EditText editOldPassword;
    EditText editNewPassword;
    EditText editCnfPassword;
    Button btnUpdatePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_changepwd);



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

        message = findViewById(R.id.message);

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
                            if (response.body().getPasswordStt() == 1){
                                AlertDialog.Builder builder = new AlertDialog.Builder(ChangePwdProfile.this);
                                builder.setTitle("Message");
                                builder.setMessage("Successfully uploaded");
                                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                });
                                AlertDialog alert = builder.create();
                                alert.show();
                            }else{
                                if (response.body().getPasswordStt() == 2){
                                    message.setText("Password do not match");
                                }
                                if (response.body().getPasswordStt() == 3){
                                    message.setText("Old password is incorrect");
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<UsersModel> call, Throwable t) {
                        // Write Something
                    }
                });
            }
        });
    }
}

