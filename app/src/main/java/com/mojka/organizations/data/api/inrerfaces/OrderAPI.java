package com.mojka.organizations.data.api.inrerfaces;

import com.mojka.organizations.data.model.BaseDataWrapper;
import com.mojka.organizations.data.model.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OrderAPI {
    @GET("orders/active")
    Call<BaseDataWrapper<List<Order>>> getActive(@Query("token") String token);

    @GET("orders/history")
    Call<BaseDataWrapper<List<Order>>> getHistory(@Query("token") String token);
}
