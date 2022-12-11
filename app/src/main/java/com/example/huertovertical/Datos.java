package com.example.huertovertical;

import static com.example.huertovertical.R.layout.activity_home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Datos extends Activity {


//    private DatabaseReference mDatabase;
//    private EditText editText1;

 //   FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
// ...

    private DatabaseReference mDatabase;
// ...


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datos);

//        editText1 = findViewById(R.id.edit_id);
//
//        mDatabase = FirebaseDatabase.getInstance().getReference();

//        DatabaseReference mDatabaseReference = mDatabase.getReference();
//        mDatabaseReference = mDatabase.getReference().child("nombre"); mDatabaseReference.setValue("Pato Donald");

        mDatabase = FirebaseDatabase.getInstance().getReference();

        writeNewUser("1","juan","juan123456@gmail.com");


    }

//    public void sendData(View view){
//            writeNewUser();
//    }
//
//    public void writeNewUser(){
//        User user = new User(editText1.getText().toString(),editText1.getText().toString());
//
//        mDatabase.child("users").child("hola");
//    }

    public void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);

        mDatabase.child("users").child(userId).setValue(user);
    }
}
