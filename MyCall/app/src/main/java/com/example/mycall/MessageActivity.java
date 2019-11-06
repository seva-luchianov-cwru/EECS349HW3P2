package com.example.mycall;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MessageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        final EditText phoneNumberField = findViewById(R.id.PhoneNumber);
        final EditText messageTextField = findViewById(R.id.MessageText);

        findViewById(R.id.SendSMSButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(phoneNumberField.getText().toString(), messageTextField.getText().toString());
            }
        });

        findViewById(R.id.OpenPhoneActivityButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MessageActivity.this, MainActivity.class));
            }
        });

    }

    private void sendMessage(String inputPhoneNumber, String smsMessage) {
        // Check if we are allowed to intent into the sms app
        boolean hasSMSPermission = ActivityCompat.checkSelfPermission(
                MessageActivity.this,
                Manifest.permission.SEND_SMS
        ) == PackageManager.PERMISSION_GRANTED;

        if (hasSMSPermission) {
            // Intent into the sms app if we are allowed
            SmsManager.getDefault().sendTextMessage(
                    inputPhoneNumber,
                    null,
                    smsMessage,
                    null,
                    null
            );
        } else {
            // Request permission if we are not
            ActivityCompat.requestPermissions(
                    MessageActivity.this,
                    new String[]{Manifest.permission.SEND_SMS},
                    1
            );
        }
    }

}
