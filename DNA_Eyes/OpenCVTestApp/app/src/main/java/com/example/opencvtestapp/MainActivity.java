package com.example.opencvtestapp;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import androidx.exifinterface.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    // Global variables
    ImageView imageView;
    Uri imageUri;
    Bitmap grayBitmap, imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);

        OpenCVLoader.initDebug();
    }

    public void openGallery(View v){
        Intent myIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(myIntent, 100);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null){
            // Get image URI
            imageUri = data.getData();

            // Get path of the image
            String path = getPath(imageUri);
            
            // Call load image method
            Mat resizedImage = loadImage(path);
            
            displayImage(resizedImage);

            try{
                imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
            } catch (IOException e){
                e.printStackTrace();
            }

            imageView.setImageURI(imageUri);
        }
    }

    private void displayImage(Mat resizedImage) {
        // Generate bitmap
        Bitmap bitmap = Bitmap.createBitmap(resizedImage.cols(), resizedImage.rows(), Bitmap.Config.RGB_565);

        // Convert mat to bitmap
        Utils.matToBitmap(resizedImage, bitmap);

        imageView.setImageBitmap(bitmap);
    }

    private Mat loadImage(String path) {
        // Load in the image
        Mat originalImage = Imgcodecs.imread(path);

        // Create new destination mat
        Mat rgbImage = new Mat();

        // Convert from BGR to RGB
        Imgproc.cvtColor(originalImage, rgbImage, Imgproc.COLOR_BGR2RGB);

        // Find size of mobile window
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int mobile_width = size.x;
        int mobile_height = size.y;

        // Resize the image to fit into mobile size
        Mat resizedImage = new Mat();
        double downSampleRatio = calculateSubSimpleSize(rgbImage, mobile_width, mobile_height);
        Imgproc.resize(rgbImage, resizedImage, new Size(), downSampleRatio, downSampleRatio, Imgproc.INTER_AREA);

        // Sort out the orientation of the image
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);

            switch (orientation){
                case ExifInterface.ORIENTATION_ROTATE_90:
                    resizedImage = resizedImage.t();
                    Core.flip(resizedImage, resizedImage, 1);
                    break;

                case ExifInterface.ORIENTATION_ROTATE_270:
                    resizedImage = resizedImage.t();
                    Core.flip(resizedImage, resizedImage, 0);
                    break;

            }
        } catch (IOException e){
            e.printStackTrace();
        }

        return resizedImage;
    }

    private double calculateSubSimpleSize(Mat rgbImage, int mobile_width, int mobile_height) {
        final int width = rgbImage.width();
        final int height = rgbImage.height();

        double inSampleSize = 1;
        if (height > mobile_height || width > mobile_width){
            final double heightRatio = (double)mobile_height / (double)height;
            final double widthRatio = (double)mobile_width / (double)width;

            inSampleSize = heightRatio < widthRatio ? height :width;
        }

        return inSampleSize;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String getPath(Uri imageUri) {
        if (imageUri == null){
            return null;
        } else{
            String[] projection = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(imageUri, projection, null, null);

            if (cursor != null) {
                int col_idx = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();

                cursor.close();

                return cursor.getString(col_idx);
            }
        }
        return imageUri.getPath();
    }

    public void convertToGray(View v){
        Mat Rgba = new Mat();
        Mat grayMat = new Mat();

        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inDither = false;
        o.inSampleSize = 4;

        int width = imageBitmap.getWidth();
        int height = imageBitmap.getHeight();

        // Create bitmap
        grayBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

        // Convert bitmap to mat
        Utils.bitmapToMat(imageBitmap, Rgba);

        // Convert colour
        Imgproc.cvtColor(Rgba, grayMat, Imgproc.COLOR_RGB2GRAY);

        // Convert mat back to bitmap to display
        Utils.matToBitmap(grayMat, grayBitmap);

        imageView.setImageBitmap(grayBitmap);
    }
}
