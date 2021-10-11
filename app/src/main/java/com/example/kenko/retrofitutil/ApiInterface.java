package com.example.kenko.retrofitutil;

import com.example.kenko.models.ClassModel;
import com.example.kenko.models.DegreeModel;
import com.example.kenko.models.UsersModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("register.php")
    Call<UsersModel> performUserSignIn(
            @Field("email") String email,
            @Field("object") String object,
            @Field("password") String password,
            @Field("firstname") String firstname,
            @Field("lastname") String lastname,
            @Field("address") String address,
            @Field("phone") String phone
    );

    @FormUrlEncoded
    @POST("login.php")
    Call<UsersModel> performUserLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("changepassword.php")
    Call<UsersModel> performChangePwd(
            @Field("email") String email,
            @Field("oldpassword") String oldpassword,
            @Field("newpassword") String newpassword,
            @Field("cnfnewpassword") String cnfnewpassword
    );

    @FormUrlEncoded
    @POST("information.php")
    Call<UsersModel> displayInfor(
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("editinfor.php")
    Call<UsersModel> editInfor(
            @Field("email") String email,
            @Field("firstname") String firstname,
            @Field("lastname") String lastname,
            @Field("address") String address,
            @Field("phone") String phone
    );

    @FormUrlEncoded
    @POST("degree.php")
    Call<DegreeModel> displayDegree(
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("editdegree.php")
    Call<DegreeModel> editDegree(
            @Field("email") String email,
            @Field("specialize") String specialize,
            @Field("date_of_issue") String date_of_issue,
            @Field("place_of_issue") String place_of_issue,
            @Field("workplace") String workplace
    );



    @FormUrlEncoded
    @POST("addclass.php")
    Call<ClassModel> addClass(
            @Field("email") String email,
            @Field("name") String name,
            @Field("startday") String startday,
            @Field("stopday") String stopday,
            @Field("starttime") String starttime,
            @Field("stoptime") String stoptime,
            @Field("daysofweek") String daysofweek,
            @Field("member") String member,
            @Field("cost") String cost,
            @Field("address") String address,
            @Field("descript") String descript,
            @Field("status") String status
    );



}
