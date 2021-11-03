package com.example.kenko.Teacher.Manage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.kenko.R;
import com.example.kenko.models.CourceDetailsModel;
import com.example.kenko.retrofitutil.ApiClient;
import com.example.kenko.retrofitutil.ApiInterface;
import com.example.kenko.sharedPreferences.DataLocalManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformationCource extends Fragment {
    String idCource = DataLocalManager.getStringIdCource();

    View mView;

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
                }
            }

            @Override
            public void onFailure(Call<CourceDetailsModel> call, Throwable t) {
                // Write something
            }
        });
    }
}
