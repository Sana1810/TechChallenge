package com.example.techchallenge;

import com.example.techchallenge.model.GetOTPResponse;
import com.example.techchallenge.model.VerifyOtpResponse;
import com.example.techchallenge.model.notes_list.ProfileListResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("phone_number_login")
    Call<GetOTPResponse> getOTP(@Field("number") String number);

    @FormUrlEncoded
    @POST("verify_otp")
    Call<VerifyOtpResponse> getVerifyOtp(@Field("number") String number,
                                         @Field("otp") String otp);

    @GET("test_profile_list")
    Call<ProfileListResponse> getProfileList(@Header("Authorization") String token);
}
