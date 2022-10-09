package com.example.huertovertical;

import static com.example.huertovertical.R.layout.activity_login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class login extends Activity {
    Button BtnLogin2;
    EditText emailLog, passwordLog;

    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_login);

        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        emailLog= findViewById(R.id.correoLogin);
        passwordLog = findViewById(R.id.contraseñaLogin);
        BtnLogin2 = (Button) findViewById(R.id.login_btn2);

        BtnLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailUser = emailLog.getText().toString().trim();
                String passUser = passwordLog.getText().toString().trim();

                if(emailUser.isEmpty() || passUser.isEmpty()){
                    Toast.makeText(login.this,"Completa los datos por favor",Toast.LENGTH_SHORT).show();
                }else{
                    loginUser(emailUser,passUser);

                }
//                Intent i = new Intent(login.this, Home.class);
//                startActivity(i);
            }
        });
    }

    private void loginUser(String emailUser, String passUser) {
        mAuth.signInWithEmailAndPassword(emailUser,passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    finish();
                    startActivity(new Intent(login.this , Home.class));
                    Toast.makeText(login.this,"Bienvenido",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(login.this, Home.class);
                    startActivity(i);
                }else{
                    Toast.makeText(login.this,"Error al iniciar sesion",Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(login.this,"Error al iniciar sesión", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
//public class Your_Class_Name extends Activity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.Your_XML_FileName);
//        ...
//    }
//}