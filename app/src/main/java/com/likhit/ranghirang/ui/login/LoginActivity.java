package com.likhit.ranghirang.ui.login;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.likhit.ranghirang.R;
import com.likhit.ranghirang.base.BaseActivity;
import com.likhit.ranghirang.databinding.ActivityLoginBinding;
import com.likhit.ranghirang.sharedPreference.PreferenceHelper;
import com.likhit.ranghirang.utils.ActivityLauncher;
import com.likhit.ranghirang.utils.Utils;

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

        binding.loginIdEditTextInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && !validLoginId()) {
                    binding.loginIdEditTextInput.setError(getString(R.string.login_id_error));
                }
            }
        });

        binding.loginPasswordEditTextInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && !validLoginId()) {
                    binding.loginPasswordEditTextInput.setError(getString(R.string.password_error));
                }
            }
        });

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validLoginId() && validPassword()) {
                    Utils.hideSoftKeyboard(LoginActivity.this);
                    PreferenceHelper.getInstance().saveUserLoggedIn(true);
                    ActivityLauncher.launchColorListActivity(LoginActivity.this);
                    finish();
                } else {
                    showMessage(R.string.login_error);
                }
            }
        });

    }

    private boolean validLoginId() {
        return !binding.loginIdEditTextInput.getText().toString().equals("");
    }

    private boolean validPassword() {
        return !binding.loginPasswordEditTextInput.getText().toString().equals("");
    }

}
