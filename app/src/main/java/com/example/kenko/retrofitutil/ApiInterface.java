package com.example.kenko.retrofitutil;

import com.example.kenko.models.CourceDetailsModel;
import com.example.kenko.models.CourceModel;
import com.example.kenko.models.DegreeModel;
import com.example.kenko.models.EmailModel;
import com.example.kenko.models.NotificationModel;
import com.example.kenko.models.ResponsePOJO;
import com.example.kenko.models.SearchModel;
import com.example.kenko.models.StudentModel;
import com.example.kenko.models.TeacherModel;
import com.example.kenko.models.UsersModel;

import java.util.List;

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
    @POST("changepwd.php")
    Call<UsersModel> performChangePwd(
            @Field("email") String email,
            @Field("oldpassword") String oldpassword,
            @Field("newpassword") String newpassword,
            @Field("cnfnewpassword") String cnfnewpassword
    );

    @FormUrlEncoded
    @POST("user_infor.php")
    Call<UsersModel> displayInfor(
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("edit_user_infor.php")
    Call<UsersModel> editInfor(
            @Field("email") String email,
            @Field("firstname") String firstname,
            @Field("lastname") String lastname,
            @Field("address") String address,
            @Field("phone") String phone
    );

    @FormUrlEncoded
    @POST("infor_degree.php")
    Call<DegreeModel> displayDegree(
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("edit_degree.php")
    Call<DegreeModel> editDegree(
            @Field("email") String email,
            @Field("specialize") String specialize,
            @Field("date_of_issue") String date_of_issue,
            @Field("place_of_issue") String place_of_issue,
            @Field("workplace") String workplace
    );

    @FormUrlEncoded
    @POST("create_cource.php")
    Call<CourceModel> createCource(
            @Field("email") String email,
            @Field("name_cource") String name_cource,
            @Field("descript_cource") String descript_cource,
            @Field("status") String status,
            @Field("startday") String startday,
            @Field("stopday") String stopday,
            @Field("starttime") String starttime,
            @Field("stoptime") String stoptime,
            @Field("daysofweek") String daysofweek,
            @Field("member") String member,
            @Field("price") String price,
            @Field("address") String address
    );

    @FormUrlEncoded
    @POST("display_cource.php")
    Call<List<CourceModel>> displayCource(
            @Field("email") String email,
            @Field("status") String status
    );

    @FormUrlEncoded
    @POST("delete_cource.php")
    Call<CourceModel> deleteCource(
            @Field("id_cource") String id_cource
    );

    @FormUrlEncoded
    @POST("display_cource_details.php")
    Call<CourceDetailsModel> displayCourceDetails(
            @Field("id_cource") String id_cource
    );

    @FormUrlEncoded
    @POST("edit_cource_detail.php")
    Call<CourceDetailsModel> editCourceDetails(
            @Field("id_cource") String id_cource,
            @Field("name_cource") String name_cource,
            @Field("descript_cource") String descript_cource,
            @Field("status") String status,
            @Field("startday") String startday,
            @Field("stopday") String stopday,
            @Field("starttime") String starttime,
            @Field("stoptime") String stoptime,
            @Field("daysofweek") String daysofweek,
            @Field("member") String member,
            @Field("price") String price,
            @Field("address") String address
    );

    @FormUrlEncoded
    @POST("get_email_participant.php")
    Call<EmailModel> getEmailGroup(
            @Field("id_cource") String id_cource
    );

    @FormUrlEncoded
    @POST("display_member.php")
    Call<List<StudentModel>> displayMembers(
            @Field("id_cource") String id_cource
    );

    @FormUrlEncoded
    @POST("upload_image.php")
    Call<ResponsePOJO> uploadImage(
            @Field("EN_IMAGE") String encodedImage,
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("delete_member.php")
    Call<StudentModel> deleteMember(
            @Field("id_cource") String id_cource,
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("display_notification.php")
    Call<List<NotificationModel>> displayNotification(
            @Field("id_cource") String id_cource
    );

    @FormUrlEncoded
    @POST("create_notification.php")
    Call<NotificationModel> addNotification(
            @Field("id_cource") String id_cource,
            @Field("content") String content
    );

    @FormUrlEncoded
    @POST("delete_notification.php")
    Call<NotificationModel> deleteNotification(
            @Field("id_notification") String id_notification
    );

    @FormUrlEncoded
    @POST("display_infor_member.php")
    Call<StudentModel> displayInforMember(
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("display_all_cource.php")
    Call<List<SearchModel>> displayAllCource(
            @Field("status") String status,
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("display_cource_to_signup.php")
    Call<CourceDetailsModel> displayCourceToSignUp(
            @Field("id_cource") String id_cource
    );

    @FormUrlEncoded
    @POST("signup_to_participant.php")
    Call<CourceDetailsModel> signUpToParticipant(
            @Field("id_cource") String id_cource,
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("display_infor_teacher.php")
    Call<TeacherModel> displayInforTeacher(
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("getcount_and_getname.php")
    Call<UsersModel> setProfile(
            @Field("object") String object,
            @Field("email") String email
    );


}
