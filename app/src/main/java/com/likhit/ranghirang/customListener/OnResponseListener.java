package com.likhit.ranghirang.customListener;

public interface OnResponseListener<T> {

    void onSuccess(T data);

    void onFailed(String error);

}
