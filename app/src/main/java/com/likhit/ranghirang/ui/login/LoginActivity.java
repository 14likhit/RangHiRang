package com.likhit.ranghirang.ui.login;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.likhit.ranghirang.R;
import com.likhit.ranghirang.base.BaseActivity;
import com.likhit.ranghirang.databinding.ActivityLoginBinding;
import com.likhit.ranghirang.utils.ActivityLauncher;

public class LoginActivity extends BaseActivity {

    private static final String TAG = "LoginActivity";

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        initView();
    }

    private void initView() {

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityLauncher.launchColorListActivity(LoginActivity.this);
            }
        });

    }
}
