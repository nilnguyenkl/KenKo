package com.example.kenko;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kenko.models.UsersModel;
import com.example.kenko.retrofitutil.ApiClient;
import com.example.kenko.retrofitutil.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterActivity extends AppCompatActivity {
    ImageView imgBack;

    EditText editEmail;
    EditText editPassword;
    RadioGroup GRbtn;
    RadioButton radioButton;

    EditText editLastname;
    EditText editFirstname;
    EditText editAddress;
    EditText editPhone;

    Button btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        register();
    }
    private void register(){

        editLastname = findViewById(R.id.editLastname);
        editFirstname = findViewById(R.id.editFirstname);
        editAddress = findViewById(R.id.editAddress);
        editPhone = findViewById(R.id.editPhone);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        GRbtn = findViewById(R.id.GRbtn);

        btn_signup = findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = GRbtn.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                String object = radioButton.getText().toString();
                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();
                String firstname = editFirstname.getText().toString();
                String lastname = editLastname.getText().toString();
                String phone = editPhone.getText().toString();
                String address = editAddress.getText().toString();

                if (lastname.equals("")){
                    editLastname.requestFocus();
                    editLastname.setError("Lastname is not empty");
                    return;
                }
                if (firstname.equals("")){
                    editFirstname.requestFocus();
                    editFirstname.setError("Firstname is not empty");
                    return;
                }
                if (email.equals("")){
                    editEmail.requestFocus();
                    editEmail.setError("Email is not empty");
                    return;
                }
                if (phone.equals("")){
                    editPhone.requestFocus();
                    editPhone.setError("Phone is not empty");
                    return;
                }
                if (address.equals("")){
                    editAddress.requestFocus();
                    editAddress.setError("Address is not empty");
                    return;
                }
                if (password.equals("")){
                    editPassword.requestFocus();
                    editPassword.setError("Password is not empty");
                    return;
                }

                Call<UsersModel> call = ApiClient.getApiClient().create(ApiInterface.class).performUserSignIn(email, object, password, firstname, lastname, address, phone);
                call.enqueue(new Callback<UsersModel>(){

                    @Override
                    public void onResponse(Call<UsersModel> call, Response<UsersModel> response) {
                        if (response.code() == 200){
                            if (response.body().getEmail().equals("")){
                                editEmail.requestFocus();
                                editEmail.setError("Email already exists");
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setTitle("Message");
                                builder.setMessage("Successfully registered an account");
                                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                });
                                AlertDialog alert = builder.create();
                                alert.show();
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<UsersModel> call, Throwable t) {
                        // Write something
                    }
                });
            }
        });
    }
}
