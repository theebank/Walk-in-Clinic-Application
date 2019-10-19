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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;


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
    public static String hash(String password){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }

    }

    private void signIn(final String username,final String password){
        final Intent intent = new Intent(getApplicationContext(),WelcomeScreen.class);
        users.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(username).exists()){
                    if(!username.isEmpty()){
                        User login = dataSnapshot.child(username).getValue(User.class);
                        if (login.getPassword().equals(hash(password))){
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
