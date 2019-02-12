package com.android.test.contactapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView nameTxt, emailTxt, phoneTxt;
    private SharedPreferences sharedPreferences;
    private Button showContactsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        nameTxt = findViewById(R.id.nameTxt);
        emailTxt = findViewById(R.id.emailTxt);
        phoneTxt = findViewById(R.id.phoneTxt);

        showContactsBtn = findViewById(R.id.showContactsBtn);
        showContactsBtn.setOnClickListener(this);

        sharedPreferences = getSharedPreferences("MY_PREFS", MODE_PRIVATE);
        nameTxt.setText(String.valueOf(nameTxt.getText()
                + sharedPreferences.getString("NAME", null)));
        emailTxt.setText(String.valueOf(emailTxt.getText()
                + sharedPreferences.getString("EMAIL", null)));
        phoneTxt.setText(String.valueOf(phoneTxt.getText()
                + sharedPreferences.getString("PHONE", null)));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.editFieldsMenu:
                sharedPreferences.edit().putBoolean("IS_LOGGED_IN", false).apply();
                goToFirstScreen();
                break;
            case R.id.logoutMenu:
                sharedPreferences.edit().clear().apply();
                goToFirstScreen();
                break;
        }
        return true;
    }

    private void goToFirstScreen() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.showContactsBtn:
                startActivity(new Intent(this, ContactsActivity.class));
                finish();
                break;
        }
    }
}
