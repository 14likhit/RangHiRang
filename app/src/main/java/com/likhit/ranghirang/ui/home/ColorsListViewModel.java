package com.likhit.ranghirang.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.likhit.ranghirang.customListener.OnResponseListener;
import com.likhit.ranghirang.data.model.ColorList;
import com.likhit.ranghirang.data.remote.ApiClient;
import com.likhit.ranghirang.data.remote.ApiService;
import com.likhit.ranghirang.data.remote.RemoteDataSourceClass;

public class ColorsListViewModel extends AndroidViewModel implements OnResponseListener<ColorList> {

    MutableLiveData<ColorList> colorListMutableLiveData = new MutableLiveData<>();

    private RemoteDataSourceClass remoteDataSourceClass;

    public ColorsListViewModel(@NonNull Application application) {
        super(application);
    }

    public void getColors(int page) {
        RemoteDataSourceClass.getInstance(ApiClient.getRetrofitInstance().create(ApiService.class)).getColors(page, this);
    }

    @Override
    public void onSuccess(ColorList data) {
        colorListMutableLiveData.setValue(data);
    }

    @Override
    public void onFailed(String error) {
        colorListMutableLiveData.setValue(null);
    }
}
