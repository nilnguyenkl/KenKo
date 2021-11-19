package com.example.kenko.Teacher.Manage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kenko.R;
import com.example.kenko.models.EmailModel;
import com.example.kenko.models.NotificationModel;
import com.example.kenko.retrofitutil.ApiClient;
import com.example.kenko.retrofitutil.ApiInterface;
import com.example.kenko.sharedPreferences.DataLocalManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotifytionCource extends Fragment {

    View mView;

    Button btnSubmit;
    EditText editContent;

    String idCource = DataLocalManager.getStringIdCource();
    private RecyclerView recyclerView;
    private NotificationAdapter adapter;
    private List<NotificationModel> notificationsdata;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        mView = inflater.inflate(R.layout.teacher_manage_notification_cource,container, false);
        displayNotification();
        addNotification();
        return mView;
    }

    private void addNotification(){
        btnSubmit = mView.findViewById(R.id.btnSubmit);
        editContent = mView.findViewById(R.id.editContent);

        // String mSubject = ""
        // String mMessage = editContent.getText().toString();


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editContent.getText().toString();
                Call<NotificationModel> call = ApiClient.getApiClient().create(ApiInterface.class).addNotification(idCource, content);
                call.enqueue(new Callback<NotificationModel>() {
                    @Override
                    public void onResponse(Call<NotificationModel> call, Response<NotificationModel> response) {
                        if (response.code() == 200){
                            if (response.body().getResult_code() == 1){
                                if (response.body().getStatus_code().equals("ok")){
                                    ///////////////
                                    Call<EmailModel> call1 = ApiClient.getApiClient().create(ApiInterface.class).getEmailGroup(idCource);
                                    call1.enqueue(new Callback<EmailModel>() {
                                        @Override
                                        public void onResponse(Call<EmailModel> call, Response<EmailModel> response) {
                                            if (response.code() == 200){
                                                if (response.body().getResult_code() == 1 && response.body().getStatusCode().equals("ok")){
                                                    String mEmail = response.body().getEmail_group().toString();
                                                    String mSubject = "Notice from " + response.body().getName_cource().toString() + " class";
                                                    String mMessage = editContent.getText().toString();

                                                    sendEmail(mEmail, mSubject, mMessage);
                                                }
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<EmailModel> call, Throwable t) {
                                            // Write something
                                        }
                                    });
                                    //////////////
                                    displayNotification();
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
    }

    private void sendEmail(String mEmail, String mSubject, String mMessage) {
        JavaMailAPI javaMailAPI = new JavaMailAPI(getContext(), mEmail, mSubject, mMessage);
        javaMailAPI.execute();
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
