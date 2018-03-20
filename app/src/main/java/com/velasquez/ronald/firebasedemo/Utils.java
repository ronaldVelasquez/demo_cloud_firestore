package com.velasquez.ronald.firebasedemo;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * Created by ronaldvelasquez on 20/02/18.
 */

public class Utils {
    private static final Gson gson = new Gson();
    public static void saveModule(final String module, final int version) {
        FirebaseDemoApplication.firebaseFirestore.collection("liverpool_keys")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                if (documentSnapshot.getId().equals(module)) {
                                    Log.i("Firestore", documentSnapshot.toString());
                                    DynamicKeysModel dynamicKeysModel = new DynamicKeysModel();
                                    dynamicKeysModel.setKeys(documentSnapshot.getData());
                                    dynamicKeysModel.setVersion(version);
                                    File file = new File(FirebaseDemoApplication.appContext.getCacheDir(), module + "_keys.json");
                                    String data = gson.toJson(dynamicKeysModel);
                                    try {
                                        writeToFile(file, data);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        } else {
                            Log.e("Save " + module + " data", "Error a");
                        }
                    }
                });
    }

    public static String stringFromStream(InputStream is) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null)
                sb.append(line).append("\n");
            reader.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String stringFromFile(File f) throws IOException {
        FileInputStream fis = new FileInputStream(f);
        String str = stringFromStream(fis);
        fis.close();
        return str;
    }

    public static void writeToFile(File f, String str) throws IOException {
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(str.getBytes());
        fos.close();

    }

    public static void writeToFile(String fileName, String str) {
        try {
            FileOutputStream fos = FirebaseDemoApplication.appContext.openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(str.getBytes(), 0, str.length());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String stringFromAsset(String assetFileName) {
        AssetManager am = FirebaseDemoApplication.appContext.getAssets();
        try {
            InputStream is = am.open(assetFileName);
            String result = Utils.stringFromStream(is);
            is.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
