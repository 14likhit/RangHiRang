package com.likhit.ranghirang.ui.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.likhit.ranghirang.R;
import com.likhit.ranghirang.databinding.ActivitySplashBinding;
import com.likhit.ranghirang.utils.ActivityLauncher;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        startTimer();
    }

    private void startTimer() {
        //30 Sec Timer.
        binding.getRoot().postDelayed(new Runnable() {
            @Override
            public void run() {
                ActivityLauncher.launchLoginActivity(SplashActivity.this);
            }
        }, 5000);
    }
}
