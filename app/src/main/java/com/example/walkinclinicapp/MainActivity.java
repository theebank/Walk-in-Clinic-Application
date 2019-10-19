package com.example.walkinclinicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Firebase
    FirebaseDatabase database;
    DatabaseReference users;

    EditText UsernameField, PasswordField;
    Button loginbtn;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Firebase
        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");

        UsernameField = findViewById(R.id.UserNameField);
        PasswordField = findViewById(R.id.PasswordField);
        loginbtn = findViewById(R.id.Loginbtn);



    }

    public void OnCreateAccountButton(View view) {
        //Application Context and Activity
        Intent intent = new Intent(getApplicationContext(), CreateAccount.class);
        startActivityForResult (intent,0);
    }
    public void OnLoginButton(View view) {
        signIn(UsernameField.getText().toString(),PasswordField.getText().toString());
    }


    private void signIn(final String username,final String password){
        final Intent intent = new Intent(getApplicationContext(),WelcomeScreen.class);
        users.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(username).exists()){
                    if(!username.isEmpty()){
                        User login = dataSnapshot.child(username).getValue(User.class);
                        if (login.getPassword().equals(password)){
                            Toast.makeText(MainActivity.this, "Successful Login!", Toast.LENGTH_LONG).show();
                            users = FirebaseDatabase.getInstance().getReference().child("Users").child(username);
                            users.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    String usernameds = dataSnapshot.child("username").getValue().toString();
                                    String passwordds = dataSnapshot.child("password").getValue().toString();
                                    String roleds = dataSnapshot.child("role").getValue().toString();

                                    intent.putExtra("Username",usernameds);
                                    intent.putExtra("Password", passwordds);
                                    intent.putExtra("Role",roleds);
                                    startActivityForResult (intent,0);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }else{
                            Toast.makeText(MainActivity.this, "Password is wrong.", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "Unregistered Username.", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
