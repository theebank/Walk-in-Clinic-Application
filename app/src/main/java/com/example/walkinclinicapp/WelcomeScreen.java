package com.example.walkinclinicapp;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.*;


public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        Intent i = getIntent();
        TextView WelcomeMsg = findViewById(R.id.WelcomeMsg);
        WelcomeMsg.setText("Welcome " + i.getStringExtra("Username") + "!" + "You have the role of: "+ i.getStringExtra("Role"));
        String roletype =i.getStringExtra("Role");

        if(roletype.equals("admin")){
            Intent intent = new Intent(getApplicationContext(), Admin1.class);
            startActivityForResult (intent,0);
        }
    }
    public void LogoutButton(View view){
        Toast.makeText(WelcomeScreen.this, "Successful Logout!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult (intent,0);
    }

}
