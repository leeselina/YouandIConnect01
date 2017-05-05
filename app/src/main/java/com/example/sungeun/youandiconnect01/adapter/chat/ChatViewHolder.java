package com.example.sungeun.youandiconnect01.adapter.chat;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sungeun.youandiconnect01.R;
import com.example.sungeun.youandiconnect01.model.Chat;


public class ChatViewHolder extends RecyclerView.ViewHolder {


    TextView content, time, name;
    LinearLayout container;

    public ChatViewHolder(View itemView) {
        super(itemView);
        content = (TextView) itemView.findViewById(R.id.chat_content);
        name = (TextView) itemView.findViewById(R.id.chat_name);
        time = (TextView) itemView.findViewById(R.id.chat_time);
        container = (LinearLayout) itemView.findViewById(R.id.chat_container);
    }

    void setData(Chat chat, String uid) {

        if (chat.getUid().equals(uid)) {
            content.setBackgroundResource(R.color.colorNonAccent);
            content.setTextColor(Color.BLACK);
        }

        name.setText(chat.getName());
        content.setText(chat.getContent());
        time.setText(chat.getTime());

    }


}
