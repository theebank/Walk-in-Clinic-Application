package com.example.walkinclinicapp;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.os.Bundle;

public class CreateAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        Spinner role = findViewById(R.id.RoleSelect);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CreateAccount.this,
            android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        role.setAdapter(adapter);
    }
    public void OnCreatetheAccountButton(View view) {
        //Application Context and Activity
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult (intent,0);
    }
}
