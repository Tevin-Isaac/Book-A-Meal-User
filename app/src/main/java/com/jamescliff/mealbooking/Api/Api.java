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

    @FormUrlEncoded
    @POST("models/v1/customer")
    Call<ResponseBody> createCustomer(
            @Field("customer") int customer,
            @Field("address") String address,
            @Field("contact") String contact,
            @Field("orders") int orders,
            @Field("total_sale") int total_sale
    );

    @FormUrlEncoded
    @POST("models/v1/order")
    Call<ResponseBody> makeOrder(
            @Field("customer") int customer,
            @Field("payment_status") String payment_status,
            @Field("delivery_status") String delivery_status,
            @Field("if_cancelled") boolean if_cancelled,
            @Field("total_amount") int total_amount,
            @Field("payment_method") String payment_method,
            @Field("location") String location,
            @Field("delivery_boy") String delivery_boy
    );

    @GET("models/v1/food")
    Call<ResponseBody> getMenu();

    @GET("models/v1/cart")
    Call<ResponseBody> getCart();

    @GET("models/v1/order")
    Call<ResponseBody> getOrder();

    @FormUrlEncoded
    @POST("models/v1/cart")
    Call<ResponseBody> addToCart(
            @Field("food") int food,
            @Field("user") int user,
            @Field("amount") int amount,
            @Field("image") String image
    );

    @POST("auth/v1/login/")
    @FormUrlEncoded
    Call<LoginResponse> login(
            @Field("username") String username,
            @Field("password") String password);

//    @FormUrlEncoded
//    @POST("getpaymentbyaccountnumber")
//    Call<LoginResponse> getPaymentByAccountNumber(@Field("account_number") String str);
//
//    @FormUrlEncoded
//    @POST("userlogin")
//    Call<LoginResponse> userLogin(@Field("number") String str, @Field("email") String str2);
}
