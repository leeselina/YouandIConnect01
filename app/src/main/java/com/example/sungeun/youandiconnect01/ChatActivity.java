package com.example.sungeun.youandiconnect01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.example.sungeun.youandiconnect01.adapter.chat.ChatAdapter;
import com.example.sungeun.youandiconnect01.model.Chat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        FirebaseDatabase.getInstance();

        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.chat_list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(linearLayoutManager);


        if (firebaseAuth.getCurrentUser() != null)
            recyclerView.setAdapter(new ChatAdapter(this, firebaseAuth.getCurrentUser().getUid()));

        final EditText editText = (EditText) findViewById(R.id.input);

        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editText.getText().toString().isEmpty()) {
                    addChatMessage(editText.getText().toString(), firebaseAuth);
                }
            }
        });

    }

    private void addChatMessage(String content, FirebaseAuth firebaseAuth) {
        new Chat(firebaseAuth.getCurrentUser().getDisplayName(), content, firebaseAuth.getCurrentUser().getUid()).newChat();
    }
}
