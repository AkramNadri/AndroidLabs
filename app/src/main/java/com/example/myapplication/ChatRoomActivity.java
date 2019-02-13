package com.example.myapplication.;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ChatRoomActivity extends AppCompatActivity {

    int numObjects = 10;
    EditText chatbox;
    EditText message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        ListAdapter adt = new MyOwnAdapter();

        ListView theList = (ListView) findViewById(R.id.the_list);

        theList.setAdapter(adt);

        Button nextButton =(Button) findViewById(R.id.send);
        nextButton.setOnClickListener ((btn)->{

            Intent nextPage = new Intent(ChatRoomActivity.this, Message.class);
            message = (EditText) findViewById(R.id.message);
            nextPage.putExtra("send", message.getText().toString());
//            startActivityForResult(nextPage, 345);
            startActivity(nextPage);
        });
    }

    protected class MyOwnAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return numObjects;
        }

        public Object getItem(int position){
            return android.R.id.message + position;
        }

        public View getView(int position, View old, ViewGroup parent)
        {
            LayoutInflater inflater = getLayoutInflater();

            View newView = inflater.inflate(R.layout.activity_chat_room, parent, false );


            TextView rowText = (TextView)newView.findViewById(R.id.typeHere);
            String stringToShow = getItem(position).toString();
            rowText.setText( stringToShow );
            //return the row:
            return newView;
        }

        public long getItemId(int position)
        {
            return position;
        }
    }



}