package com.likhit.ranghirang.utils;

import android.content.Context;
import android.content.Intent;

import com.likhit.ranghirang.ui.home.ColorsListActivity;
import com.likhit.ranghirang.ui.login.LoginActivity;

public class ActivityLauncher {

    public static void launchLoginActivity(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    public static void launchColorListActivity(Context context) {
        context.startActivity(new Intent(context, ColorsListActivity.class));
    }

}
