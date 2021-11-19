package com.example.kenko.Teacher.Manage;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.kenko.R;
import com.example.kenko.models.CourceDetailsModel;
import com.example.kenko.retrofitutil.ApiClient;
import com.example.kenko.retrofitutil.ApiInterface;
import com.example.kenko.sharedPreferences.DataLocalManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformationCource extends Fragment {
    String idCource = DataLocalManager.getStringIdCource();

    View mView;

    EditText startDay;
    EditText nameCource;
    EditText stopDay;
    EditText startTime;
    EditText stopTime;
    EditText dayofweek;
    EditText address;
    EditText descript;
    EditText price;
    EditText member;

    Spinner spinnerOption;

    Button btnSave;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        mView = inflater.inflate(R.layout.teacher_manage_infor_cource,container, false);
        displayCourceDetails();
        return mView;
    }

    private void displayCourceDetails(){

        nameCource = mView.findViewById(R.id.nameCource);
        startDay = mView.findViewById(R.id.startDay);
        stopDay = mView.findViewById(R.id.stopDay);
        startTime = mView.findViewById(R.id.startTime);
        stopTime = mView.findViewById(R.id.stopTime);
        dayofweek = mView.findViewById(R.id.dayofweek);
        address = mView.findViewById(R.id.address);
        descript = mView.findViewById(R.id.descript);
        price = mView.findViewById(R.id.price);
        member = mView.findViewById(R.id.member);

        spinnerOption = mView.findViewById(R.id.spinnerOption);
        List<String> list = new ArrayList<>();
        list.add("open");
        list.add("prepare");
        list.add("finish");

        ArrayAdapter<String> adaptere = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, list);

        spinnerOption.setAdapter(adaptere);


        Call<CourceDetailsModel> call = ApiClient.getApiClient().create(ApiInterface.class).displayCourceDetails(idCource);
        call.enqueue(new Callback<CourceDetailsModel>() {
            @Override
            public void onResponse(Call<CourceDetailsModel> call, Response<CourceDetailsModel> response) {
                if (response.code() == 200){
                    nameCource.setText(response.body().getNameCource().toString());
                    startDay.setText(response.body().getStartDay().toString());
                    stopDay.setText(response.body().getStopDay().toString());
                    startTime.setText(response.body().getStartTime().toString());
                    stopTime.setText(response.body().getStopTime().toString());
                    dayofweek.setText(response.body().getDaysOfWeek().toString());
                    address.setText(response.body().getAddress().toString());
                    descript.setText(response.body().getDescript().toString());
                    price.setText(response.body().getPrice().toString());
                    member.setText(response.body().getMember().toString());
                    Log.d("=====", response.body().getStatus());
                    if (response.body().getStatus().equals("open")){
                        spinnerOption.setSelection(0);
                    }
                    if (response.body().getStatus().equals("prepare")){
                        spinnerOption.setSelection(1);
                    }
                    if (response.body().getStatus().equals("finish")){
                        spinnerOption.setSelection(2);
                    }

                }
            }
            @Override
            public void onFailure(Call<CourceDetailsModel> call, Throwable t) {
                // Write something
            }
        });

        btnSave = mView.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enameCource = nameCource.getText().toString();
                String estartDay = startDay.getText().toString();
                String estopDay = stopDay.getText().toString();
                String estartTime = startTime.getText().toString();
                String estopTime = stopTime.getText().toString();
                String edayofweek = dayofweek.getText().toString();
                String eaddress = address.getText().toString();
                String edescript = descript.getText().toString();
                String eprice = price.getText().toString();
                String emember = member.getText().toString();
                String estatus = spinnerOption.getSelectedItem().toString();

                Call<CourceDetailsModel> call = ApiClient.getApiClient().create(ApiInterface.class).editCourceDetails(idCource, enameCource, edescript, estatus, estartDay, estopDay, estartTime, estopTime, edayofweek, emember, eprice, eaddress);
                call.enqueue(new Callback<CourceDetailsModel>() {
                    @Override
                    public void onResponse(Call<CourceDetailsModel> call, Response<CourceDetailsModel> response) {
                        if (response.code() == 200) {
                            if (response.body().getStatus_code().equals("ok")) {
                                Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "Failure", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<CourceDetailsModel> call, Throwable t) {
                        Toast.makeText(getContext(), "No network", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
