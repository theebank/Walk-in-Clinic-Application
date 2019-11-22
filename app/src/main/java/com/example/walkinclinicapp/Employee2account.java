package com.example.walkinclinicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
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

import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Employee2account extends AppCompatActivity {
    Spinner cliniclist,insurancelist;
    FirebaseDatabase database;
    DatabaseReference ref,users;
    Query query,query1;
    EditText addressfield,phonenumfield;
    CheckBox checkcb,cashcb,creditcb,debitcb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee2account);

        cliniclist = findViewById(R.id.spinnerclinic);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Clinics");

        insurancelist = findViewById(R.id.spinnerinsurance);

        addressfield = findViewById(R.id.addy);
        phonenumfield = findViewById(R.id.phonenum);

        checkcb = findViewById(R.id.Checkcb);
        cashcb = findViewById(R.id.Cashcb);
        creditcb = findViewById(R.id.Creditcb);
        debitcb = findViewById(R.id.Debitcb);

        //Firebase
        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");

        final List<String> roles = new ArrayList<String>();
        roles.add("InsuranceType1");
        roles.add("InsuranceType2");
        roles.add("InsuranceType3");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Employee2account.this,
                android.R.layout.simple_list_item_1, roles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        insurancelist.setAdapter(adapter);


        query = ref.orderByChild("name");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<String> titleList = new ArrayList<String>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    String titlename = dataSnapshot1.child("name").getValue(String.class);
                    titleList.add(titlename);
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Employee2account.this, android.R.layout.simple_spinner_item, titleList);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                cliniclist.setAdapter(arrayAdapter);
                cliniclist.setSelection(0);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Employee2account.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }
    public void OnUpdateAccountInfobtn(View view) {
        final Intent i = getIntent();
        final String[] empty = {""};
        final List emptylist = new ArrayList<String>(Arrays.asList(empty));
        final String[] empty7 = {"","","","","","",""};
        final List emptyhours = new ArrayList<String>(Arrays.asList(empty7));

        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child((i.getStringExtra("Username"))).exists()){
                    User username = dataSnapshot.child(i.getStringExtra("Username")).getValue(User.class);
                    if(validCellPhone(phonenumfield.getText().toString())) {
                        final Employee emp = new Employee(addressfield.getText().toString(), phonenumfield.getText().toString(), cliniclist.getSelectedItem().toString(), insurancelist.getSelectedItem().toString(), creditcb.isChecked(), debitcb.isChecked(), cashcb.isChecked(), checkcb.isChecked(), username.getUsername(), username.getPassword(), username.getRole(), emptylist,emptyhours);
                        users.child(emp.getUsername()).setValue(emp);
                        //Application Context and Activity
                        Intent intent = new Intent(getApplicationContext(), Employee1.class);
                        intent.putExtra("hide","1");
                        startActivityForResult (intent,0);
                    }else{
                        Toast.makeText(Employee2account.this, "Invalid Phone Number.",Toast.LENGTH_SHORT).show();
                        return;
                    }

                }else{

                    Toast.makeText(Employee2account.this, "Successfully Registered.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //
            }
        });

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
    public boolean validCellPhone(String number) {
        return android.util.Patterns.PHONE.matcher(number).matches();
    }
}
