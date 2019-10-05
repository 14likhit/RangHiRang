package com.likhit.ranghirang.data.remote;

import com.likhit.ranghirang.customListener.OnResponseListener;
import com.likhit.ranghirang.data.model.ColorList;

public interface RemoteDataSource {

    void getColors(int page, OnResponseListener<ColorList> onResponseListener);

}
