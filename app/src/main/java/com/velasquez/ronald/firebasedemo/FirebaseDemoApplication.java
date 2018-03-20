package com.velasquez.ronald.firebasedemo;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ronaldvelasquez on 19/02/18.
 */

public class FirebaseDemoApplication extends Application {
    public static Context appContext;
    public static Map<String, Integer> keysVersion;
    private static DatabaseReference mDatabase;
    public static FirebaseFirestore firebaseFirestore;


    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        firebaseFirestore = FirebaseFirestore.getInstance();
        mDatabase.child("dynamic_keys").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("Data firebase", dataSnapshot.toString());
                GenericTypeIndicator<Map<String, Integer>> indicator = new GenericTypeIndicator<Map<String, Integer>>(){};
                keysVersion = dataSnapshot.getValue(indicator);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
