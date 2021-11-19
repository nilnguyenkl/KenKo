package com.example.kenko;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kenko.models.UsersModel;
import com.example.kenko.retrofitutil.ApiClient;
import com.example.kenko.retrofitutil.ApiInterface;
import com.example.kenko.sharedPreferences.DataLocalManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformationProfile extends AppCompatActivity {
    String email = DataLocalManager.getStringEmail();
    ImageView imgBack;

    EditText editFirstname;
    EditText editLastname;
    EditText editPhone;
    EditText editAddress;

    Button btnUpdateInfor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_infor);

        editInfor();
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

    private void editInfor(){
        editFirstname = findViewById(R.id.editFirstname);
        editLastname = findViewById(R.id.editLastname);
        editAddress = findViewById(R.id.editAddress);
        editPhone = findViewById(R.id.editPhone);

        btnUpdateInfor = findViewById(R.id.btnUpdateInfor);

        Call<UsersModel> call = ApiClient.getApiClient().create(ApiInterface.class).displayInfor(email);
        call.enqueue(new Callback<UsersModel>() {
            @Override
            public void onResponse(Call<UsersModel> call, Response<UsersModel> response) {
                if (response.code() == 200){
                    editFirstname.setText(response.body().getFirstname().toString());
                    editLastname.setText(response.body().getLastname().toString());
                    editAddress.setText(response.body().getAddress().toString());
                    editPhone.setText(response.body().getPhone().toString());
                }
            }

            @Override
            public void onFailure(Call<UsersModel> call, Throwable t) {

            }
        });

        btnUpdateInfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lastname = editLastname.getText().toString();
                String firstname = editFirstname.getText().toString();
                String address = editAddress.getText().toString();
                String phone = editPhone.getText().toString();

                if (firstname.equals("")){
                    editFirstname.requestFocus();
                    editFirstname.setError("Firstname is not empty");
                    return;
                }
                if (lastname.equals("")){
                    editLastname.requestFocus();
                    editLastname.setError("Lastname is not empty");
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

                Call<UsersModel> call = ApiClient.getApiClient().create(ApiInterface.class).editInfor(email, firstname, lastname, address,phone);
                call.enqueue(new Callback<UsersModel>() {
                    @Override
                    public void onResponse(Call<UsersModel> call, Response<UsersModel> response) {
                        if (response.code() == 200){
                            if (response.body().getResultCode() == 1){
                                AlertDialog.Builder builder = new AlertDialog.Builder(InformationProfile.this);
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

