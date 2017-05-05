package com.example.sungeun.youandiconnect01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.example.sungeun.youandiconnect01.adapter.chat.ChatAdapter;
import com.example.sungeun.youandiconnect01.model.Chat;
import com.example.sungeun.youandiconnect01.model.util.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatActivity extends AppCompatActivity {


    // 시스템으로부터 현재시간(ms) 가져오기
    long now = System.currentTimeMillis();
    // Data 객체에 시간을 저장한다.
    Date date = new Date(now);
    // 각자 사용할 포맷을 정하고 문자열로 만든다.
    SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    String strNow = sdfNow.format(date);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        final Intent intent = getIntent();
        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.chat_list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(linearLayoutManager);

        final DatabaseReference databaseReference = firebaseDatabase.getReference(Constants.CHAT_ROOMS_REF).child(intent.getStringExtra("room_key")).child(Constants.CHAT_MESSAGES_REF);

        if (firebaseAuth.getCurrentUser() != null)
            recyclerView.setAdapter(new ChatAdapter(this, databaseReference, firebaseAuth.getCurrentUser().getUid()));

        final EditText editText = (EditText) findViewById(R.id.input);

        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editText.getText().toString().isEmpty()) {
                    addChatMessage(editText.getText().toString(), databaseReference, firebaseAuth);
                }
            }
        });

    }

    private void addChatMessage(String content, DatabaseReference databaseReference, FirebaseAuth firebaseAuth) {
        DatabaseReference chatRef = databaseReference;
        Chat chat = new Chat();
        chat.setName(firebaseAuth.getCurrentUser().getDisplayName());
        chat.setUid(firebaseAuth.getCurrentUser().getUid());
        chat.setContent(content);
        chat.setIsUser(true);
        chat.setTime(strNow);
        databaseReference.updateChildren(chat.newChat());

    }
}
