package com.likhit.ranghirang.data.remote;

import com.likhit.ranghirang.data.model.ColorList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

//Service to make network request.
public interface ApiService {

    @GET("unknown/")
    Call<ColorList> getColors(@Query("page") int page);
}
