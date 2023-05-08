package com.cameo.ckyc.activity.face;

import static com.cameo.ckyc.utils.AppConstants.APP_DATA;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.cameo.ckyc.R;
import com.cameo.ckyc.activity.DocHomeActivity;
import com.cameo.ckyc.databinding.ActivityPreviewBinding;
import com.canhub.cropper.CropImage;
import com.canhub.cropper.CropImageView;
import com.jsibbold.zoomage.ZoomageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PreviewActivity extends AppCompatActivity {
    private ActivityPreviewBinding binding;
    String captureId;
    private final Context mContext = this;
    private Bitmap bitmap, croppedBitmap;
    File f, file;
    String DIR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPreviewBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        initOnClickListener();
        captureId = getIntent().getStringExtra("capture_id");
        DIR = getExternalFilesDir("/").getPath() + "/" + ".cameo/";

        loadImage();

        if (bitmap != null){
            binding.cropImageView.setImageBitmap(bitmap);
            binding.cropImageView.setAutoZoomEnabled(false);
            binding.cropImageView.setShowProgressBar(false);
            binding.cropImageView.setGuidelines(CropImageView.Guidelines.OFF);
            binding.cropImageView.setShowCropOverlay(false);
        }
    }

    private void loadImage() {
        switch (Integer.parseInt(captureId)){
            case 1:
                file = new File(DIR, "face_image" + ".jpg");
                bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                break;

            case 2:
                file = new File(DIR, "aadhar_image" + ".jpg");
                bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                break;

            case 3:
                file = new File(DIR, "voter_image" + ".jpg");
                bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                break;

            case 4:
                file = new File(DIR, "driving_licence_image" + ".jpg");
                bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                break;

            case 5:
                file = new File(DIR, "pan_image" + ".jpg");
                bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                break;
        }
    }

    private void initOnClickListener() {
      binding.cropBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              binding.nextBtn.setVisibility(View.INVISIBLE);
              binding.cropOkBtn.setVisibility(View.VISIBLE);

              binding.cropImageView.setAutoZoomEnabled(true);
              binding.cropImageView.setShowProgressBar(true);
              binding.cropImageView.setGuidelines(CropImageView.Guidelines.ON);
              binding.cropImageView.setShowCropOverlay(true);
          }
      });

      binding.cropOkBtn.setOnClickListener(view -> {
          binding.nextBtn.setVisibility(View.VISIBLE);
          binding.cropOkBtn.setVisibility(View.INVISIBLE);

          croppedBitmap = binding.cropImageView.getCroppedImage();

          binding.cropImageView.setImageBitmap(croppedBitmap);

          binding.cropImageView.setAutoZoomEnabled(false);
          binding.cropImageView.setShowProgressBar(false);
          binding.cropImageView.setGuidelines(CropImageView.Guidelines.OFF);
          binding.cropImageView.setShowCropOverlay(false);

          saveImage(captureId);
      });

      binding.nextBtn.setOnClickListener(view -> {
          Intent intent = new Intent(mContext, DocHomeActivity.class);
          intent.putExtra("capture_id", captureId);
          startActivity(intent);
      });
    }

    private void saveImage(String captureId) {
        File dir = getExternalFilesDir(APP_DATA);
        if(!dir.exists())
        {
            if (!dir.mkdir())
            {
                Toast.makeText(getApplicationContext(), "The folder " + dir.getPath() + "was not created", Toast.LENGTH_SHORT).show();
            }
        }

        switch (Integer.parseInt(captureId)){
            case 1:
                  f = new File(dir, "face_image" + ".jpg");
                break;

            case 2:
                 f = new File(dir, "aadhar_image" + ".jpg");
                break;

            case 3:
                f = new File(dir, "voter_image" + ".jpg");
                break;
            case 4:
                f = new File(dir, "driving_licence_image" + ".jpg");
                break;

            case 5:
                f = new File(dir, "pan_image" + ".jpg");
                break;
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        croppedBitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        byte[] bitmapdata = bos.toByteArray();

        try {
            f.createNewFile();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        //write the bytes in file
        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(f);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}