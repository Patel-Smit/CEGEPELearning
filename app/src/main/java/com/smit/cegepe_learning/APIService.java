package com.smit.cegepe_learning;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers({
            "Content-Type:application/json",
            "Authorization:key=AAAAnal7u_0:APA91bHZwTWywhga3jpaGUVxcQ_yXuOvoyxI_Oys6mOicEA2wCdyX2bdDvZir8ec6PbunU0Xk6uxrS-3Cmg0EZHxf4sdor_RnM9M1GAxX74GK4pKc5IVQaQ3WVZTdZFxYslLOHxdCPnH"
    })
    @POST("fcm/send")
    Call<MyRespone> sendNotification(@Body Sender body);
}
