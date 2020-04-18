package com.example.rohinijsonparsingdemo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApi {


        @GET("stam/api/getst_m_tedata")
        Call<Network> getImageData();

}
