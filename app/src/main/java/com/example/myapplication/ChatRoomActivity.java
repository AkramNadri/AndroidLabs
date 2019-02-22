package com.example.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.myapplication.Message;
import com.example.myapplication.MyDatabaseOpenHelper;
import com.example.myapplication.R;

import java.util.ArrayList;

public class ChatRoomActivity extends AppCompatActivity {


    EditText messageTyped;
    Button sendBtn;
    Button receiveBtn;
    ListView theList;
    ArrayList<Message> messagesList = new ArrayList<>();
    MyOwnAdapter adapter;
    public static int ACTIVITY_VIEW_MESSAGE = 33;
    int positionClicked;


    /*
        public void sendMessage(View view) {

            String text = messageTyped.getText().toString();

            if (!text.equals("")) {

                Message message = new Message(text, true);
                messagesList.add(message);
                theList.setAdapter(adapter);
                messageTyped.setText("");

            }

        }//end

        public void receiveMessage(View view) {

            String text = messageTyped.getText().toString();

            if (!text.equals("")) {
                Message message = new Message(text, false);
                messagesList.add(message);
                theList.setAdapter(adapter);
                messageTyped.setText("");
            }

        }//end
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);


        //Fields
        messageTyped = findViewById(R.id.chatMess);
        theList = findViewById(R.id.the_list);
        sendBtn = findViewById(R.id.send);
        receiveBtn = findViewById(R.id.receive);
        ImageView sendPic = findViewById(R.id.sendPic);
        TextView messageRow = findViewById(R.id.messShow);
        ImageView receivePic = findViewById(R.id.receivePic);

        //Database
        MyDatabaseOpenHelper dbOpener = new MyDatabaseOpenHelper(this);
        SQLiteDatabase db = dbOpener.getWritableDatabase();

        //Query results from database
        String [] columns = {MyDatabaseOpenHelper.COL_ID, MyDatabaseOpenHelper.COL_MESSAGES};
        Cursor results = db.query(MyDatabaseOpenHelper.TABLE_NAME, columns,
                null, null, null, null, null);

        //Find column indices
        int idColIndex = results.getColumnIndex(MyDatabaseOpenHelper.COL_ID);
        int messageColIndex = results.getColumnIndex(MyDatabaseOpenHelper.COL_MESSAGES);

        //maybe if send btn then isSend true.
        //iterate over the results, return true if there is a next item:
        while(results.moveToNext()){

            long id = results.getLong(idColIndex);
            String message = results.getString(messageColIndex);

            // adapter.getItem(1);
//            if(adapter.getItem(1).isSend() == true){
//                messagesList.add(new Message(id, message, true));
//            }
//            else{
            messagesList.add(new Message(message, id));
            //}

        }

        //Create adapter and send to list
        adapter = new MyOwnAdapter();
        theList.setAdapter(adapter);

        //listen for send button clicked
        sendBtn.setOnClickListener(click -> {

            String message = messageTyped.getText().toString();

            //add message to database and get new ID
            ContentValues newRowValues = new ContentValues();

            //put string message in the message column
            newRowValues.put(MyDatabaseOpenHelper.COL_MESSAGES, message);
            long newId = db.insert(MyDatabaseOpenHelper.TABLE_NAME, null, newRowValues);

            //Create new message with new ID
            Message newMessage = new Message(newId, message, true);

            //add new message to list
            messagesList.add(newMessage);

            //update the listView
            adapter.notifyDataSetChanged();

            //clear the message field
            messageTyped.setText("");

            //show notification
            Toast.makeText(this, "sendBtn!", Toast.LENGTH_SHORT).show();

        });

        //listen for receive button clicked
        receiveBtn.setOnClickListener(click -> {

            String message = messageTyped.getText().toString();

            //add message to database and get new ID
            ContentValues newRowValue = new ContentValues();

            //put string message in the message column
            newRowValue.put(MyDatabaseOpenHelper.COL_MESSAGES, message);
            long newId = db.insert(MyDatabaseOpenHelper.TABLE_NAME, null, newRowValue);

            //Create new message with new ID
            Message newMessage = new Message(newId, message, false);

            //add new message to list
            messagesList.add(newMessage);

            //update the listView
            adapter.notifyDataSetChanged();

            //clear the message field
            messageTyped.setText("");

            String text = messageTyped.getText().toString();

            //show notification
            Toast.makeText(this, "receiveMessage!", Toast.LENGTH_SHORT).show();

        });


    }//End onCreate

    protected class MyOwnAdapter extends BaseAdapter {

        @Override
        public int getCount() {

            return messagesList.size();

        }

        @Override
        public Message getItem(int position){

            return messagesList.get(position);

        }

        @Override
        public long getItemId(int position) {

            return position;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = getLayoutInflater();

            View newView = inflater.inflate(R.layout.single_row, parent, false);

            Message thisMessageRow = getItem(position);
            ImageView sendPic = newView.findViewById(R.id.sendPic);
            TextView messageRow = newView.findViewById(R.id.messShow);
            ImageView receivePic = newView.findViewById(R.id.receivePic);

            messageRow.setText(thisMessageRow.getText());

            if (thisMessageRow.isSend() == true) {
                receivePic.setVisibility(View.INVISIBLE);
                messageRow.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            }
            else sendPic.setVisibility(View.INVISIBLE);

            return newView;

        }

    }//End MyOwnAdapter


    public void printCursor(Cursor c){

    }

    public void saveMessage(){

    }

}