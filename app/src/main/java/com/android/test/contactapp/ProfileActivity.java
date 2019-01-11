package com.android.test.contactapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    private TextView nameTxt, emailTxt, phoneTxt;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        nameTxt = findViewById(R.id.nameTxt);
        emailTxt = findViewById(R.id.emailTxt);
        phoneTxt = findViewById(R.id.phoneTxt);

        sharedPreferences = getSharedPreferences("MY_PREFS", MODE_PRIVATE);
        nameTxt.setText(String.valueOf(nameTxt.getText()
                + sharedPreferences.getString("NAME", null)));
        emailTxt.setText(String.valueOf(emailTxt.getText()
                + sharedPreferences.getString("EMAIL", null)));
        phoneTxt.setText(String.valueOf(phoneTxt.getText()
                + sharedPreferences.getString("PHONE", null)));

    }
}
