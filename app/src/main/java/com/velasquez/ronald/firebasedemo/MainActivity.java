package com.velasquez.ronald.firebasedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_pdp).setOnClickListener(this);
        findViewById(R.id.btn_clp).setOnClickListener(this);
        findViewById(R.id.btn_blp).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String module = "";
        switch (view.getId()) {
            case R.id.btn_pdp:
                module = "pdp";
                break;
            case R.id.btn_clp:
                module = "clp";
                break;
            case R.id.btn_blp:
                module = "blp";
                break;
        }
        showInfo(module);
    }

    private void showInfo(String module) {
        Intent intent = new Intent(this, ModuleActivity.class);
        intent.putExtra("module", module);
        startActivity(intent);
    }
}
