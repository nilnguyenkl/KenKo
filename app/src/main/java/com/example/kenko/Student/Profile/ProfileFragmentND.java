package com.example.kenko.Student.Profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.kenko.ChangePwdProfile;
import com.example.kenko.InformationProfile;
import com.example.kenko.R;
import com.example.kenko.models.UsersModel;
import com.example.kenko.retrofitutil.ApiClient;
import com.example.kenko.retrofitutil.ApiInterface;
import com.example.kenko.sharedPreferences.DataLocalManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragmentND extends Fragment {
    View view;

    TextView textInfor;
    TextView textChangepwd;

    TextView textnameObject;
    TextView course_label;

    TextView textEmail;
    String emailLocal = DataLocalManager.getStringEmail();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.student_profile,container, false);

        setInfor();
        switchInformation();
        switchChangPwd();

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    private void switchInformation(){
        textInfor = view.findViewById(R.id.textInfor);
        textInfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InformationProfile.class);
                startActivity(intent);
            }
        });
    }
    private void switchChangPwd(){
        textChangepwd = view.findViewById(R.id.textChangepwd);
        textChangepwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangePwdProfile.class);
                startActivity(intent);
            }
        });
    }

    private void setInfor(){
        textEmail = view.findViewById(R.id.textEmail);
        textnameObject = view.findViewById(R.id.textnameObject);
        course_label = view.findViewById(R.id.course_label);

        textEmail.setText(emailLocal);

        Call<UsersModel> call = ApiClient.getApiClient().create(ApiInterface.class).setProfile("student", emailLocal);
        call.enqueue(new Callback<UsersModel>() {
            @Override
            public void onResponse(Call<UsersModel> call, Response<UsersModel> response) {
                if (response.code() == 200){
                    textnameObject.setText(response.body().getFirstname() + " " + response.body().getLastname());
                    course_label.setText(response.body().getSum());
                }
            }

            @Override
            public void onFailure(Call<UsersModel> call, Throwable t) {
                // Write something
            }
        });
    }
}
