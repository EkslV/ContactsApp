package com.android.test.contactapp;

import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences sharedPreferences;
    private TextInputLayout tilName, tilEmail, tilPhone;
    private EditText editName, editEmail, editPhone;
    private Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("MY_PREFS", MODE_PRIVATE);
        boolean isLogedIn = sharedPreferences.getBoolean("IS_LOGED_IN", false);

        if (isLogedIn) {
            // todo start next screen with user data.
            // finish();
        }

        tilName = findViewById(R.id.tilName);
        tilEmail = findViewById(R.id.tilEmail);
        tilPhone = findViewById(R.id.tilPhone);

        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editPhone = findViewById(R.id.editPhone);

        saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.saveBtn) {

        }
    }
}
