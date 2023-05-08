package com.cameo.ckyc.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;

import com.cameo.ckyc.camerax.CameraxActivity;
import com.cameo.ckyc.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {
    private ActivitySplashBinding binding;
    Handler mHandler;
    Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mHandler=new Handler();
        mHandler.postDelayed(() -> {
            Intent intent=new Intent(mContext, DocHomeActivity.class);
            startActivity(intent);
            finish();
        },150); // 1500
    }
}