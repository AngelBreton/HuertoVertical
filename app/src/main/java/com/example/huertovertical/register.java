package com.example.huertovertical;

import static com.example.huertovertical.R.layout.activity_home;
import static com.example.huertovertical.R.layout.activity_login;
import static com.example.huertovertical.R.layout.activity_register;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class register extends Activity {

    Button btn_register;
    EditText email, password, confirmPassword;

    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_register);

        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        email= findViewById(R.id.correo);
        password = findViewById(R.id.contraseña);
        confirmPassword = findViewById(R.id.confirmaContraseña);
        btn_register = findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailUser = email.getText().toString().trim();
                String passUser = password.getText().toString().trim();
                String passUserConfirm = confirmPassword.getText().toString().trim();
                if(emailUser.isEmpty() && passUser.isEmpty() && passUserConfirm.isEmpty()){
                    Toast.makeText(register.this,"Complete los datos", Toast.LENGTH_SHORT).show();
                }
                if(emailUser.isEmpty() || passUser.isEmpty() || passUserConfirm.isEmpty()){
                    Toast.makeText(register.this,"Completa los datos",Toast.LENGTH_SHORT).show();
                }else{
                    registerUser(emailUser,passUser,passUserConfirm);
                }
            }
        });
    }

    private void registerUser(String emailUser, String passUser, String passUserConfirm) {
        mAuth.createUserWithEmailAndPassword(emailUser,passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                String id = mAuth.getCurrentUser().getUid();
                Map<String,Object> map= new HashMap<>();
                map.put("id",id);
                map.put("email",emailUser);
                map.put("password",passUser);
                mFirestore.collection("user").document(id).set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(register.this,"Usuario registrado con exito",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(register.this, login.class);
                        startActivity(i);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(register.this,"Error al guardar", Toast.LENGTH_SHORT).show();
                    }
                });


                //                if(task.isSuccessful()){
//                    finish();
//                    startActivity(new Intent(register.this, login.class));
//                    Toast.makeText(register.this,"bienvenido",Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(register.this,"error", Toast.LENGTH_SHORT).show();
//                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(register.this,"Error al registrar",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
