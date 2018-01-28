package com.mojka.organizations.data.api.inrerfaces;


import com.mojka.organizations.data.model.BaseDataWrapper;
import com.mojka.organizations.data.model.BaseErrorResponse;
import com.mojka.organizations.data.model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ProfileAPI {
    @GET("profile")
    Call<BaseDataWrapper<User>> get(@Query("token") String token);

    @FormUrlEncoded
    @POST("account/restore")
    Call<BaseErrorResponse> restorePassword(@Field("phone") String phone,
                                            @Field("new_password") String newPassword);
}