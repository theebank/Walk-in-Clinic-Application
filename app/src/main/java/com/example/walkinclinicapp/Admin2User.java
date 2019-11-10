package com.example.walkinclinicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.Query;

import java.util.ArrayList;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;
import java.util.List;

public class Admin2User extends AppCompatActivity {
    Spinner userlist;
    FirebaseDatabase database;
    DatabaseReference ref;
    Query query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin2_user);

        TextView admin2user = findViewById(R.id.admin2user);
        admin2user.setText("Welcome to the User Administration Page.");

        userlist = findViewById(R.id.spinner2);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Users");


        query = ref.orderByChild("username");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<String> titleList = new ArrayList<String>();
                titleList.add("--Choose an Account--");
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    String titlename = dataSnapshot1.child("username").getValue(String.class);
                    titlename = titlename + " | "+ dataSnapshot1.child("role").getValue(String.class);
                    titleList.add(titlename);
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Admin2User.this, android.R.layout.simple_spinner_item, titleList);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                userlist.setAdapter(arrayAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Admin2User.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
    public void returnbtn(View view){
        Intent intent = new Intent(getApplicationContext(), Admin1.class);
        startActivityForResult (intent,0);
    }
    public void deletebtn(View view){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query applesQuery = ref.child("Users").orderByChild("username").equalTo(userlist.getSelectedItem().toString());

        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                    appleSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    public void OnCreateAccountButton(View view) {
        //Application Context and Activity
        Intent intent = new Intent(getApplicationContext(), CreateAccount.class);
        startActivityForResult (intent,0);
    }
}
