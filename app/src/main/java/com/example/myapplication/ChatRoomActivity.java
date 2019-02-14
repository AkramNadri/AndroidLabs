package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;

public class ChatRoomActivity extends AppCompatActivity {

    EditText chatMess;
    ListView theList;
    ArrayList<Message> messages;
    MyOwnAdapter adapter;

    // query goes in here
    public void sendMessage(View v) {
        String text = chatMess.getText().toString();
        if (!text.equals("")) {
            Message m = new Message(text, true);
            messages.add(m);
            theList.setAdapter(adapter);
            chatMess.setText("");
        }
    }

    // query goes in here
    public void receiveMessaage(View v) {
        String text = chatMess.getText().toString();
        if (!text.equals("")) {
            Message m = new Message(text, false);

            messages.add(m);
            theList.setAdapter(adapter);
            chatMess.setText("");
        }
    }

    // query goes in here
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        chatMess = findViewById(R.id.chatMess);

        theList = findViewById(R.id.the_list);

        messages = new ArrayList<>();

        adapter = new MyOwnAdapter(messages);

        theList.setAdapter(adapter);

    }

    protected class MyOwnAdapter extends BaseAdapter {
        private ArrayList<Message> messages;

        public MyOwnAdapter (ArrayList<Message> messages) {
            this.messages = messages;
        }

        @Override
        public int getCount() {
            return messages.size();
        }

        @Override
        public Object getItem(int position) {
            return messages.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View v = inflater.inflate(R.layout.single_row, parent, false);

            ImageView sendPic = v.findViewById(R.id.sendPic);
            ImageView receivePic = v.findViewById(R.id.receivePic);
            TextView messShow = v.findViewById(R.id.messShow);

            Message m = messages.get(position);
            messShow.setText(m.getText());

            if (m.isSend() == true) {
                receivePic.setVisibility(View.INVISIBLE);
                messShow.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            } else sendPic.setVisibility(View.INVISIBLE);

            return v;
        }
    }


}