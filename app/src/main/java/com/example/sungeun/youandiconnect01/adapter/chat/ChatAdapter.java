package com.example.sungeun.youandiconnect01.adapter.chat;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.sungeun.youandiconnect01.R;
import com.example.sungeun.youandiconnect01.model.Chat;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;


public class ChatAdapter extends RecyclerView.Adapter<ChatViewHolder> {

    ArrayMap<String, Chat> chatArrayMap = new ArrayMap<>();

    String uid;
    Context context;

    public ChatAdapter(Context context, DatabaseReference databaseReference, String uid) {

        this.uid = uid;
        this.context = context;

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Chat newChat = dataSnapshot.getValue(Chat.class);
                chatArrayMap.put(dataSnapshot.getKey(), newChat);
                notifyItemChanged(getItemCount());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                chatArrayMap.remove(dataSnapshot.getKey());
                notifyItemRemoved(chatArrayMap.indexOfKey(dataSnapshot.getKey()));
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        databaseReference.addChildEventListener(childEventListener);
    }

    @Override
    public int getItemCount() {
        return chatArrayMap.size();
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChatViewHolder(View.inflate(context, R.layout.chat_layout, null));
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        holder.setData(chatArrayMap.valueAt(position), uid);
    }

}
