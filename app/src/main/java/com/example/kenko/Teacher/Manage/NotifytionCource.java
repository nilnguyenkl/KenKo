package com.example.kenko.Teacher.Manage;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kenko.R;
import com.example.kenko.models.NotificationModel;
import com.example.kenko.retrofitutil.ApiClient;
import com.example.kenko.retrofitutil.ApiInterface;
import com.example.kenko.sharedPreferences.DataLocalManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotifytionCource extends Fragment {

    View mView;

    FloatingActionButton addNotification;

    String idCource = DataLocalManager.getStringIdCource();
    private RecyclerView recyclerView;
    private NotificationAdapter adapter;
    private List<NotificationModel> notificationsdata;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        mView = inflater.inflate(R.layout.teacher_manage_notification_cource,container, false);
        displayNotification();
        addNotificationCource();
        return mView;
    }
    private void addNotificationCource(){
        addNotification = mView.findViewById(R.id.addNotification);
        addNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddNotification(Gravity.CENTER);
            }
        });
    }
    private  void openAddNotification(int gravity){
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.teacher_add_notification_feedback);

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

        EditText editContent = dialog.findViewById(R.id.editContent);
        Button btnCancel = dialog.findViewById(R.id.btnCancel);
        Button btnSubmit = dialog.findViewById(R.id.btnSubmit);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(getContext(), "=============" + idCource, Toast.LENGTH_SHORT).show();
                String content = editContent.getText().toString();
                Call<NotificationModel> call = ApiClient.getApiClient().create(ApiInterface.class).addNotification(idCource, content);
                call.enqueue(new Callback<NotificationModel>() {
                    @Override
                    public void onResponse(Call<NotificationModel> call, Response<NotificationModel> response) {
                        if (response.code() == 200){
                            if (response.body().getResult_code() == 1){
                                if (response.body().getStatus_code().equals("ok")){
                                    // Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                                    displayNotification();
                                    dialog.dismiss();
                                }else{
                                    Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(getContext(), "Something was wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<NotificationModel> call, Throwable t) {
                        // Write Something
                    }
                });
            }
        });

        dialog.show();
    }

    private void displayNotification(){
        recyclerView = mView.findViewById(R.id.rec);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Call<List<NotificationModel>> call = ApiClient.getApiClient().create(ApiInterface.class).displayNotification(idCource);
        call.enqueue(new Callback<List<NotificationModel>>() {
            @Override
            public void onResponse(Call<List<NotificationModel>> call, Response<List<NotificationModel>> response) {
                notificationsdata = response.body();
                adapter = new NotificationAdapter(notificationsdata, getContext(), new IClickItemNotificationListener() {
                    @Override
                    public void deleteItemNotification(NotificationModel notification) {
                        onClickDelete(notification);
                    }
                });
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<NotificationModel>> call, Throwable t) {
                // Write Something
            }
        });
    }

    private void onClickDelete(NotificationModel notification){
        Call<NotificationModel> call = ApiClient.getApiClient().create(ApiInterface.class).deleteNotification(notification.getId_notification());
        call.enqueue(new Callback<NotificationModel>() {
            @Override
            public void onResponse(Call<NotificationModel> call, Response<NotificationModel> response) {
                if (response.code() == 200){
                    if (response.body().getStatus_code().equals("ok")){
                        Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<NotificationModel> call, Throwable t) {
                // Write Something
            }
        });
    }
}
