package com.example.kenko.Teacher.Profile;

import static android.app.Activity.RESULT_OK;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.kenko.ChangePwdProfile;
import com.example.kenko.InformationProfile;
import com.example.kenko.R;
import com.example.kenko.models.ResponsePOJO;
import com.example.kenko.models.UsersModel;
import com.example.kenko.retrofitutil.ApiClient;
import com.example.kenko.retrofitutil.ApiInterface;
import com.example.kenko.sharedPreferences.DataLocalManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    private int IMG_REQUEST = 21;
    public static final String TAG = ProfileFragment.class.getName();
    View view;
    String emailLocal = DataLocalManager.getStringEmail();

    TextView textInfor;
    TextView textDegree;
    TextView textChangepwd;

    TextView textnameObject;
    TextView course_label;

    TextView textEmail;

    CircleImageView profile_image;
    FloatingActionButton btnSelectImage;

    private Bitmap bitmap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.teacher_profile,container, false);

        handle();
        setInfor();

        switchInformation();
        switchDegree();
        switchChangPwd();

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null){
            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), path);
                profile_image.setImageBitmap(bitmap);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void handle(){
        profile_image = view.findViewById(R.id.profile_image);
        btnSelectImage = view.findViewById(R.id.btnSelectImage);
        btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, IMG_REQUEST);

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Message");
                builder.setMessage("Are you sure that you want to change your avatar?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        uploadImage();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // getActivity().finish();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    private void uploadImage(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 75, byteArrayOutputStream);
        byte[] imageInByte = byteArrayOutputStream.toByteArray();

        String encodedImage = Base64.getEncoder().encodeToString(imageInByte);

        Call<ResponsePOJO> call = ApiClient.getApiClient().create(ApiInterface.class).uploadImage(encodedImage, emailLocal);
        call.enqueue(new Callback<ResponsePOJO>() {
            @Override
            public void onResponse(Call<ResponsePOJO> call, Response<ResponsePOJO> response) {
                Toast.makeText(getActivity(), response.body().getRemarks(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponsePOJO> call, Throwable t) {
                Toast.makeText(getActivity(), "Network Failure", Toast.LENGTH_SHORT).show();
            }
        });
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

    private void setInfor(){
        textEmail = view.findViewById(R.id.textEmail);
        textnameObject = view.findViewById(R.id.textnameObject);
        course_label = view.findViewById(R.id.course_label);

        textEmail.setText(emailLocal);

        Call<UsersModel> call = ApiClient.getApiClient().create(ApiInterface.class).setProfile("teacher", emailLocal);
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
