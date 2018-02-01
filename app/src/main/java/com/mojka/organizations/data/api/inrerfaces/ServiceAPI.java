package com.mojka.organizations.data.api.inrerfaces;

import com.mojka.organizations.data.model.BaseDataWrapper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceAPI {
    @GET("service")
    Call<BaseDataWrapper<ServiceAPI>> get(@Query("token") String token);
}
