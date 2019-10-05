package com.likhit.ranghirang.customListener;

/**
 * Custom response callback listener for network response
 * @param <T> -> Generic Object on Response.
 */
public interface OnResponseListener<T> {

    void onSuccess(T data);

    void onFailed(String error);

}
