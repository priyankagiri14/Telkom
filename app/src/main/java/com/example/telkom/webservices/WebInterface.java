package com.example.telkom.webservices;

import com.example.telkom.model.customerinforesponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface WebInterface {
    //Cutomer Info
    @Headers("Content-Type: application/json")
    @POST("api/v1/?method=customer&do=customer_info")
    Call<customerinforesponse> Customerinfo(@Body RequestBody customerInfoBody);
}
