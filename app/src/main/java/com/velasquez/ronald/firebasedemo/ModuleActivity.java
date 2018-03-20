package com.velasquez.ronald.firebasedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ModuleActivity extends AppCompatActivity {
    private String module;
    private TextView textView;
    private String data;
    private DynamicKeysModel keysModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);
        module = getIntent().getStringExtra("module");
        textView = findViewById(R.id.textview_data);
        showGson();
        evaluateVersionKeys();
    }

    private void evaluateVersionKeys() {
        Map<String, Integer> map = FirebaseDemoApplication.keysVersion;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getKey().equals(module) && keysModel.getVersion() != entry.getValue()) {
                Log.i("Version " + entry.getKey() + ":", "" + entry.getValue());
                Utils.saveModule(module, entry.getValue());
            }
        }
    }


    private void showGson() {
        File file = new File(getCacheDir(), module + "_keys.json");
        try {
            data = Utils.stringFromFile(file);
            Gson gson = new Gson();
            keysModel = gson.fromJson(data, DynamicKeysModel.class);
            if (!TextUtils.isEmpty(data)) {
                textView.setText(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
