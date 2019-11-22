package com.example.walkinclinicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;
import java.util.List;

public class Admin4Service extends AppCompatActivity {
    Spinner servicelist,levellist;
    FirebaseDatabase database;
    DatabaseReference ref;
    Query query,query1;
    EditText nameField, descriptionField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin4_service);

        servicelist = findViewById(R.id.Servicelist);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Services");
        nameField = findViewById(R.id.NameField);
        descriptionField = findViewById(R.id.Description);
        levellist = findViewById(R.id.level);

        final List<String> roles = new ArrayList<String>();
        roles.add("Doctor");
        roles.add("Nurse");
        roles.add("Staff");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Admin4Service.this,
                android.R.layout.simple_list_item_1, roles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        levellist.setAdapter(adapter);


        query = ref.orderByChild("type");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<String> titleList = new ArrayList<String>();
                titleList.add("--Choose a Service--");
                titleList.add("Create a Service");
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    String titlename = dataSnapshot1.child("type").getValue(String.class);
                    titleList.add(titlename);
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Admin4Service.this, android.R.layout.simple_spinner_item, titleList);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                servicelist.setAdapter(arrayAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Admin4Service.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }
    public void returnbtn(View view){
        Intent intent = new Intent(getApplicationContext(), Admin1.class);
        startActivityForResult (intent,0);
    }
    public void deletebtn(View view){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query applesQuery = ref.child("Services").orderByChild("type").equalTo(servicelist.getSelectedItem().toString());

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
    public void OnCreatetheAccountButton(View view) {
        if(TextUtils.isEmpty(nameField.getText().toString())){
            Toast.makeText(Admin4Service.this, "Invalid Type.", Toast.LENGTH_SHORT).show();
            return;
        }else if(TextUtils.isEmpty(descriptionField.getText().toString())){
            Toast.makeText(Admin4Service.this, "Invalid Description.", Toast.LENGTH_SHORT).show();
            return;
        }
        final Service user = new Service(nameField.getText().toString(),descriptionField.getText().toString(),levellist.getSelectedItem().toString());
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(user.getType()).exists()){
                    ref.child(user.getType()).setValue(user);
                    Toast.makeText(Admin4Service.this, "The Service already exists.",Toast.LENGTH_SHORT).show();
                }else{

                        ref.child(user.getType()).setValue(user);
                        Toast.makeText(Admin4Service.this, "Successfully Created.", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //
            }
        });
        //Application Context and Activity
        Intent intent = new Intent(getApplicationContext(), Admin4Service.class);
        startActivityForResult (intent,0);
    }


}
