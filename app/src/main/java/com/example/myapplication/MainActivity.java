package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText emailInput;
    EditText line2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab3_mainactivity);
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);


        emailInput = findViewById(R.id.line2);


        String sharedEmail = sharedPref.getString(getString(R.string.sharedEmail), "");

        emailInput.setHint(sharedEmail);

        Button nextButton =(Button) findViewById(R.id.button);
        nextButton.setOnClickListener ((btn)->{

            Intent nextPage = new Intent(MainActivity.this, ProfileActivity.class);
            line2 = (EditText) findViewById(R.id.line2);
            nextPage.putExtra("emailTyped", line2.getText().toString());
//            startActivityForResult(nextPage, 345);
            startActivity(nextPage);
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.sharedEmail), emailInput.getText().toString());

       // will write to disk
        editor.commit();
    }

}

