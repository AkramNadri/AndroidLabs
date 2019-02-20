package com.example.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        chatMess = findViewById(R.id.chatMess);

        theList = findViewById(R.id.the_list);

        messages = new ArrayList<>();

        adapter = new MyOwnAdapter(messages);

        theList.setAdapter(adapter);


        //Get the fields from the screen:
        EditText chatMessage = (EditText) findViewById(R.id.chatMess);
        Button receiveMessage = (Button) findViewById(R.id.receive);
        Button sendMessage = (Button) findViewById(R.id.send);
        ListView theList = (ListView) findViewById(R.id.the_list);


        //get a database:
        MyDatabaseOpenHelper dbOpener = new MyDatabaseOpenHelper(this);
        SQLiteDatabase db = dbOpener.getWritableDatabase();

        //query all the results from the database:
        String[] columns = {MyDatabaseOpenHelper.COL_ID, MyDatabaseOpenHelper.COL_MESSAGES};
        Cursor results = db.query(false, MyDatabaseOpenHelper.TABLE_NAME, columns, null, null, null, null, null, null);

        //find the column indices:
        int messageColumnIndex = results.getColumnIndex(MyDatabaseOpenHelper.COL_MESSAGES);
        int idColIndex = results.getColumnIndex(MyDatabaseOpenHelper.COL_ID);

        //iterate over the results, return true if there is a next item:
        while (results.moveToNext()) {
            String messages = results.getString(messageColumnIndex);
            long id = results.getLong(idColIndex);

            //add the new Contact to the array list:
            Message.add(new Contact(messages, id));

        }


        // query goes in here
        public void sendMessage (View v){
            String text = chatMess.getText().toString();
            if (!text.equals("")) {
                Message m = new Message(text, true);
                messages.add(m);
                theList.setAdapter(adapter);
                chatMess.setText("");
            }
        }

        // query goes in here
        public void receiveMessage (View v){
            String text = chatMess.getText().toString();
            if (!text.equals("")) {
                Message m = new Message(text, false);

                messages.add(m);
                theList.setAdapter(adapter);
                chatMess.setText("");
            }
        }


        protected class MyOwnAdapter extends BaseAdapter {
            private ArrayList<Message> messages;

            public MyOwnAdapter(ArrayList<Message> messages) {
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
}