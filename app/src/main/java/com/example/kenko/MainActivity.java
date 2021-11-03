package com.example.kenko;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kenko.Student.StudentDashBoard;
import com.example.kenko.Teacher.TeacherDashBoard;
import com.example.kenko.models.UsersModel;
import com.example.kenko.retrofitutil.ApiClient;
import com.example.kenko.retrofitutil.ApiInterface;
import com.example.kenko.sharedPreferences.DataLocalManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView txtSwitchSignup;
    EditText editEmail;
    EditText editPassword;
    Button btn_login;
    TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        switchRegister();
        login();

        if (!DataLocalManager.getFirstInstall()){
            Toast.makeText(this, "Fist install all", Toast.LENGTH_SHORT).show();
            DataLocalManager.setFirstInstall(true);
        }
    }

    private void switchRegister(){
        txtSwitchSignup = findViewById(R.id.txtSwitchSignup);
        txtSwitchSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void login(){
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        btn_login = findViewById(R.id.btn_login);
        error = findViewById(R.id.error);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();

                Call<UsersModel> call = ApiClient.getApiClient().create(ApiInterface.class).performUserLogin(email, password);
                call.enqueue(new Callback<UsersModel>(){
                    @Override
                    public void onResponse(Call<UsersModel> call, Response<UsersModel> response) {
                        if (response.code() == 200){
                            if (response.body().getResultCode() == 1){
                                DataLocalManager.setStringEmail(response.body().getEmail().toString());
                                if (response.body().getObject().equals("Student")){
                                    editEmail.setText("");
                                    editPassword.setText("");
                                    Intent student = new Intent(MainActivity.this, StudentDashBoard.class);
                                    startActivity(student);
                                    
                                }else{
                                    editEmail.setText("");
                                    editPassword.setText("");
                                    Intent teacher = new Intent(MainActivity.this, TeacherDashBoard.class);
                                    startActivity(teacher);
                                }

                            }else{
                                error.setText("Username or password is invalid");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<UsersModel> call, Throwable t) {
                        // Write Somthing
                    }
                });
            }
        });
    }
}
