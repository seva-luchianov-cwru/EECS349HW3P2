package com.example.mycall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText phoneNumberField = findViewById(R.id.PhoneNumber);

        findViewById(R.id.PhoneButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the intent to open the phone app to the specified phone number
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phoneNumberField.getText().toString()));

                // Check if we are allowed to intent into the phone app
                boolean hasPhoneCallPermission = ActivityCompat.checkSelfPermission(
                        MainActivity.this,
                        Manifest.permission.CALL_PHONE
                ) == PackageManager.PERMISSION_GRANTED;

                if (hasPhoneCallPermission) {
                    startActivity(callIntent);
                } else {
                    // Request permission if we are not
                    ActivityCompat.requestPermissions(
                            MainActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            1
                    );
                }
            }
        });

        findViewById(R.id.OpenSMSActivityButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MessageActivity.class));
            }
        });
    }
}
