package com.example.walkinclinicapp;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;



public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        Intent i = getIntent();
        TextView WelcomeMsg = findViewById(R.id.WelcomeMsg);
        WelcomeMsg.setText("Welcome " + i.getStringExtra("Username") + "!" + "You have the role of: "+ i.getStringExtra("Role"));
    }

}
