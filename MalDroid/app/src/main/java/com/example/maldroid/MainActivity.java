package com.example.maldroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.InvokeMyCallActivity).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent goToTarget = getPackageManager().getLaunchIntentForPackage("com.example.mycall");
                startActivity(goToTarget);
            }
        });

    }
}
