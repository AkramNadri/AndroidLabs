package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab3);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


}
