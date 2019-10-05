package com.likhit.ranghirang.data.remote;

import androidx.annotation.NonNull;

import com.likhit.ranghirang.customListener.OnResponseListener;
import com.likhit.ranghirang.data.model.ColorList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Repository Class to fetch response from network.
 */
public class RemoteDataSourceClass implements RemoteDataSource {

    private static final String TAG = "RemoteDataSourceClass";

    private static RemoteDataSource mInstance;
    private ApiService service;

    private RemoteDataSourceClass(ApiService apiService) {
        this.service = apiService;
    }

    public static RemoteDataSource getInstance(ApiService apiService) {
        if (mInstance == null) {
            mInstance = new RemoteDataSourceClass(apiService);
        }

        return mInstance;
    }

    @Override
    public void getColors(int page, final OnResponseListener<ColorList> onResponseListener) {

        final Call<ColorList> request = service.getColors(page);
        request.enqueue(new Callback<ColorList>() {
            @Override
            public void onResponse(@NonNull Call<ColorList> call, @NonNull Response<ColorList> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        onResponseListener.onSuccess(response.body());
                    } else {
                        onResponseListener.onFailed(response.message());
                    }
                } else {
                    onResponseListener.onFailed(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ColorList> call, @NonNull Throwable t) {
                onResponseListener.onFailed(t.getMessage());
            }
        });
    }
}
