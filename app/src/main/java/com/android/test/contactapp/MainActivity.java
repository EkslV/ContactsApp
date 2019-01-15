package com.android.test.contactapp;

import android.content.Intent;
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
        boolean isLoggedIn = sharedPreferences.getBoolean("IS_LOGGED_IN", false);

        if (isLoggedIn) {
            startActivity(new Intent(this, ProfileActivity.class));
            finish();
        }

        tilName = findViewById(R.id.tilName);
        tilEmail = findViewById(R.id.tilEmail);
        tilPhone = findViewById(R.id.tilPhone);

        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editPhone = findViewById(R.id.editPhone);

        saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(this);

        if (sharedPreferences.getString("NAME", null) != null) {
            editName.setText(sharedPreferences.getString("NAME", null));
            editEmail.setText(sharedPreferences.getString("EMAIL", null));
            editPhone.setText(sharedPreferences.getString("PHONE", null));
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.saveBtn) {
            if (String.valueOf(editName.getText()).isEmpty()) {
                tilName.setError("Data not valid");
            } else if (String.valueOf(editEmail.getText()).isEmpty()) {
                tilEmail.setError("Data not valid");
            } else if (String.valueOf(editPhone.getText()).isEmpty()) {
                tilPhone.setError("Data not valid");
            } else {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("NAME", String.valueOf(editName.getText()));
                editor.putString("EMAIL", String.valueOf(editEmail.getText()));
                editor.putString("PHONE", String.valueOf(editPhone.getText()));
                editor.putBoolean("IS_LOGGED_IN", true);
                editor.apply();

                startActivity(new Intent(this, ProfileActivity.class));
                finish();
            }
        }
    }
}
