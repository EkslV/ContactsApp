package com.android.test.contactapp;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;

import com.android.test.contactapp.adapters.ListViewAdapter;
import com.android.test.contactapp.adapters.RecyclerContactAdapter;
import com.android.test.contactapp.models.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Contact> contacts;
    RecyclerContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        createContacts();
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        adapter = new RecyclerContactAdapter(contacts);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        RecyclerView.ItemDecoration decoration =
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(decoration);
    }

    private void createContacts() {
        contacts = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            contacts.add(new Contact("name " + i, "", "058111111" + i));
        }
    }

}
