package com.example.walkinclinicapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void OnCreateAccountButton(View view) {
        //Application Context and Activity
        Intent intent = new Intent(getApplicationContext(), CreateAccount.class);
        startActivityForResult (intent,0);
    }
    public void OnLoginButton(View view) {
        //Application Context and Activity
        Intent intent = new Intent(getApplicationContext(), WelcomeScreen.class);
        EditText username =findViewById(R.id.UserNameField);
        String name = username.getText().toString();
        intent.putExtra("username",name);
        startActivityForResult (intent,0);

    }
}
