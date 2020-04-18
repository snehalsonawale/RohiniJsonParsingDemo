package com.example.rohinijsonparsingdemo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestInterface {

    @GET("stam/api/getst_m_tedata")
    Call<ResponceObject> getJSON();
}
