package com.cameo.ckyc.camera2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.cameo.ckyc.R;

public class CameraMainActivity  extends AppCompatActivity {
    private Camera2BasicFragment camera2BasicFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_main);

        if (null == savedInstanceState){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, Camera2BasicFragment.newInstance())
                    .commit();
        }
    }
}
