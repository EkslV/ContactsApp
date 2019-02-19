package com.android.test.contactapp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.test.contactapp.adapters.MessageAdapter;
import com.android.test.contactapp.models.Message;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Message> messages;
    private RecyclerView recyclerMsgs;
    private EditText editNewMsg;
    private Button sendBtn;
    private MessageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messages = new ArrayList<>();
        recyclerMsgs = findViewById(R.id.recyclerMsgs);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerMsgs.setLayoutManager(manager);

        editNewMsg = findViewById(R.id.editNewMsg);
        sendBtn = findViewById(R.id.sendBtn);
        sendBtn.setOnClickListener(this);

        readDB();
    }

    private void readDB() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("msg");
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.exists()) {
                    Message msg = dataSnapshot.getValue(Message.class);
                    if (adapter == null) {
                        adapter = new MessageAdapter();
                        recyclerMsgs.setAdapter(adapter);
                    }
                    adapter.addMessage(msg);
                    messages.add(msg);
                    recyclerMsgs.smoothScrollToPosition(messages.size() - 1);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d("TAG_J", "onChildChanged: ");
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.sendBtn) {
            if (!String.valueOf(editNewMsg.getText()).isEmpty()) {
                String msg = String.valueOf(editNewMsg.getText());
                editNewMsg.setText("");
                Message message = new Message("Vadim", msg);
                sendToDB(message);
            }
        }
    }

    private void sendToDB(Message message) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("msg");
        reference.push().setValue(message);
    }
}
