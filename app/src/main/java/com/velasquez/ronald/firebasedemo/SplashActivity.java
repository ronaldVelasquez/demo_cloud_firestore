package com.velasquez.ronald.firebasedemo;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        validateFilesAndCopy();
    }

    private void validateFilesAndCopy() {
        AssetManager assetManager = getAssets();
        String[] files = null;
        try {
            files = assetManager.list("keys");
            for (String filename : files) {
                File file = new File(getCacheDir(), filename);
                if (!file.exists()) {
                    Log.i("File name => ", filename);
                    InputStream in = null;
                    OutputStream out = null;
                    try {
                        in = assetManager.open("keys/" + filename);   // if files resides inside the "Files" directory itself
                        out = new FileOutputStream(getCacheDir() + "/" + filename);
                        copyFile(in, out);
                        in.close();
                        out.flush();
                        out.close();
                    } catch (Exception e) {
                        Log.e("tag", e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }
        goToMainActivity();
    }

    private void goToMainActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }
}
