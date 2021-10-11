package com.example.kenko.Teacher.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.kenko.R;
import com.example.kenko.sharedPreferences.DataLocalManager;

public class ProfileFragment extends Fragment {
    View view;
    String emailLocal = DataLocalManager.getStringEmail();

    TextView textInfor;
    TextView textDegree;
    TextView textChangepwd;

    TextView textEmail;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.teacher_profile,container, false);

        setEmail();

        switchInformation();
        switchDegree();
        switchChangPwd();

        return view;
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
    private void switchDegree(){
        textDegree = view.findViewById(R.id.textDegree);
        textDegree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DegreeProfile.class);
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

    private void setEmail(){
        textEmail = view.findViewById(R.id.textEmail);
        textEmail.setText(emailLocal);
    }
}
