package com.cameo.ckyc.activity;

import static com.cameo.ckyc.utils.AppConstants.APP_DATA;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cameo.ckyc.camerax.CameraxActivity;
import com.cameo.ckyc.databinding.ActivityDocHomeBinding;

import java.io.File;

public class DocHomeActivity extends AppCompatActivity {
    private ActivityDocHomeBinding binding;
    String DIR;
    private final Context context = this;
    String face = "1"; String aadhar = "2"; String voter = "3"; String driving_licence = "4"; String pan = "5";
    String pan_card_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDocHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        DIR = getExternalFilesDir("/").getPath() + "/" + ".cameo/";

        initOnClick();
        loadCKYCDocument();
    }

    private void loadCKYCDocument() {
        loadFaceKYC();
        loadAadharKyc();
        loadVoterKYC();
        loadDrivingLicenceKYC();
        loadPanKYC();
        createDirectory();
    }

    private void createDirectory() {
        File dir = getExternalFilesDir(APP_DATA);
        if(!dir.exists())
        {
            if (!dir.mkdir())
            {
                Toast.makeText(getApplicationContext(), "The folder " + dir.getPath() + "was not created", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void loadPanKYC() {
        File file = new File(DIR, "pan_image" + ".jpg");
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        if (bitmap != null){
            binding.panImg.setImageBitmap(bitmap);
            binding.panImg.setVisibility(View.VISIBLE);
        }
    }

    private void loadDrivingLicenceKYC() {
        File file = new File(DIR, "driving_licence_image" + ".jpg");
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        if (bitmap != null){
            binding.drivingImg.setImageBitmap(bitmap);
            binding.drivingImg.setVisibility(View.VISIBLE);
        }
    }

    private void loadVoterKYC() {
        File file = new File(DIR, "voter_image" + ".jpg");
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        if (bitmap != null){
            binding.voterImg.setImageBitmap(bitmap);
            binding.voterImg.setVisibility(View.VISIBLE);
        }
    }

    private void loadFaceKYC() {
        File file = new File(DIR, "face_image" + ".jpg");
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        if (bitmap != null){
            binding.userPhoto.setImageBitmap(bitmap);
        }
    }

    private void loadAadharKyc() {
        File file = new File(DIR, "aadhar_image" + ".jpg");
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        if (bitmap != null){
            binding.aadtharImg.setImageBitmap(bitmap);
            binding.aadtharImg.setVisibility(View.VISIBLE);
        }
    }

    private void initOnClick() {
        binding.userPhoto.setOnClickListener(view -> {Intent intent = new Intent(context, CameraxActivity.class); intent.putExtra("capture_id", face); startActivity(intent);});
        binding.aadtharCard.setOnClickListener(view -> { Intent intent = new Intent(context, CameraxActivity.class); intent.putExtra("capture_id", aadhar); startActivity(intent);});
        binding.voterId.setOnClickListener(view -> { Intent intent = new Intent(context, CameraxActivity.class); intent.putExtra("capture_id", voter); startActivity(intent); });
        binding.drivingLicense.setOnClickListener(view -> { Intent intent = new Intent(context, CameraxActivity.class); intent.putExtra("capture_id", driving_licence); startActivity(intent);});
        binding.panCard.setOnClickListener(view -> { Intent intent = new Intent(context, CameraxActivity.class); intent.putExtra("capture_id", pan); startActivity(intent); });
        binding.upload.setOnClickListener(view -> { pan_card_no = binding.edPanNo.getText().toString(); if (pan_card_no.isEmpty()){ binding.edPanNo.setError("Enter PAN Card No"); binding.edPanNo.requestFocus();} });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("resume", "DocHomeActivity not closed");
    }
}