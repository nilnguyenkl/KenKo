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
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kenko.R;
import com.example.kenko.models.StudentModel;
import com.example.kenko.retrofitutil.ApiClient;
import com.example.kenko.retrofitutil.ApiInterface;
import com.example.kenko.sharedPreferences.DataLocalManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemberCource extends Fragment {

    String idCource = DataLocalManager.getStringIdCource();
    View mView;

    private RecyclerView recyclerView;
    private StudentsAdapter adapter;
    private List<StudentModel> studentsdata;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        mView = inflater.inflate(R.layout.teacher_manage_member_cource,container, false);

        displayStudent();

        return mView;
    }

    private void displayStudent(){

        recyclerView = mView.findViewById(R.id.rec);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Call<List<StudentModel>> call = ApiClient.getApiClient().create(ApiInterface.class).displayMembers(idCource);
        call.enqueue(new Callback<List<StudentModel>>() {
            @Override
            public void onResponse(Call<List<StudentModel>> call, Response<List<StudentModel>> response) {
                studentsdata = response.body();
                adapter = new StudentsAdapter(studentsdata, getContext(), new IClickItemStudentListener() {
                    @Override
                    public void deleteItemStudent(StudentModel student) {
                        onClickDelete(student);
                    }

                    @Override
                    public void detailItemStudent(StudentModel student) {
                        onClickDetail(student);
                    }
                });
                recyclerView.setAdapter(adapter);
                RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
                recyclerView.addItemDecoration(itemDecoration);
            }

            @Override
            public void onFailure(Call<List<StudentModel>> call, Throwable t) {
                // Write Something
            }
        });
    }
    private void onClickDelete(StudentModel student){
        Call<StudentModel> call = ApiClient.getApiClient().create(ApiInterface.class).deleteMember(idCource, student.getEmail());
        call.enqueue(new Callback<StudentModel>() {
            @Override
            public void onResponse(Call<StudentModel> call, Response<StudentModel> response) {
                Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<StudentModel> call, Throwable t) {
                // Write something
            }
        });
    }

    private void onClickDetail(StudentModel student){
        openInforMember(Gravity.CENTER, student);
    }

    private void openInforMember(int gravity, StudentModel student){

        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.student_layout_information);

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

        TextView textName = dialog.findViewById(R.id.textName);
        TextView textEmail = dialog.findViewById(R.id.textEmail);
        TextView textPhone = dialog.findViewById(R.id.textPhone);
        TextView textAddress = dialog.findViewById(R.id.textAddress);

        Call<StudentModel> call = ApiClient.getApiClient().create(ApiInterface.class).displayInforMember(student.getEmail().toString());
        call.enqueue(new Callback<StudentModel>() {
            @Override
            public void onResponse(Call<StudentModel> call, Response<StudentModel> response) {
                textName.setText(response.body().getFirstname().toString() + response.body().getLastname().toString());
                textEmail.setText(response.body().getEmail().toString());
                textPhone.setText(response.body().getPhone().toString());
                textAddress.setText(response.body().getAddress().toString());
            }

            @Override
            public void onFailure(Call<StudentModel> call, Throwable t) {
                // Write something
            }
        });

        Button btnCancel = dialog.findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}