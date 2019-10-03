package com.likhit.ranghirang.base;

import androidx.annotation.StringRes;

public interface BaseView {

    void showMessage(String message);

    void showMessage(@StringRes int messageResId);

}
