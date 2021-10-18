package com.jamescliff.mealbooking.Api;



import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {
    @FormUrlEncoded
    @POST("auth/v1/register")
    Call<ResponseBody> createUser(
            @Field("email") String email,
            @Field("username") String username,
            @Field("password") String password
    );

    @GET("/models/v1/food")
    Call<ResponseBody> getMenu();

//    @FormUrlEncoded
//    @POST("getpaymentbyaccountnumber")
//    Call<LoginResponse> getPaymentByAccountNumber(@Field("account_number") String str);
//
//    @FormUrlEncoded
//    @POST("userlogin")
//    Call<LoginResponse> userLogin(@Field("number") String str, @Field("email") String str2);
}
