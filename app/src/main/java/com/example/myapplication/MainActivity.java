package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText emailInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab3);


        emailInput = findViewById(R.id.line2);


        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String sharedEmail = sharedPref.getString(getString(R.string.sharedEmail), "");

        emailInput.setHint(sharedEmail);

    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.sharedEmail), emailInput.getText().toString());
        editor.commit();
    }


}
