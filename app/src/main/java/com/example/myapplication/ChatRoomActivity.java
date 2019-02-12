package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class ChatRoomActivity extends AppCompatActivity {


    EditText chatBox;
    EditText typeHere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);


        chatBox = findViewById(R.id.typeHere);


        String sharedEmail = sharedPref.getString(getString(R.string.sharedEmail), "");

        chatBox.setHint(sharedEmail);

        Button nextButton =(Button) findViewById(R.id.button);
        nextButton.setOnClickListener ((btn)->{

            Intent nextPage = new Intent(ChatRoomActivity.this, Message.class);
            typeHere = (EditText) findViewById(R.id.typeHere);
            nextPage.putExtra("emailTyped", typeHere.getText().toString());
//            startActivityForResult(nextPage, 345);
            startActivity(nextPage);
        });



    }
}
