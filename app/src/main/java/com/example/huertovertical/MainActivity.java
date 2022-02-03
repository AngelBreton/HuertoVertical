package com.example.huertovertical;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button BtnLogin;
    Button BtnRegister;

    //private Button BtnRegistro;
    //private Button BtnEntrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //btnSignIn = (Button)findViewById(R.id.btnSignIn);

        BtnLogin = (Button) findViewById(R.id.login_btn);
        BtnRegister = (Button) findViewById(R.id.registro_btn);
        //Button Registrar = findViewById(R.id.registro_btn);
        //Button entrar = findViewById(R.id.entrar_btn);

        //BtnLogin.setOnClickListener(this);
        //BtnRegistro.setOnClickListener(this);
        //BtnEntrar.setOnClickListener(this);

        //metodo escucha login
        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, login.class);
                startActivity(i);
            }
        });

        //metodo escucha registrar
        BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(MainActivity.this, register.class);
                startActivity(i2);
            }
        });
    }
}



//    //metodo boton entrar
//    public void Siguiente(View view){
//        Intent i = new Intent(this, SeleccionPlantas.class);
//        startActivity(i);
//    }
//    //metodo boton Login
//    public void Login(View a){
//        Intent i2 = new Intent(this, login.class);
//        startActivity(i2);
//    }
//    //metodo boton Registrar
//    public void Registrar(View b){
//        Intent i3 = new Intent(this, register.class);
//        startActivity(i3);
//    }

    /*@Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn:
                    Intent i = new Intent(this, login.class);
                    startActivity(i);
                break;
            case R.id.registro_btn:
                Intent i3 = new Intent(this, register.class);
                startActivity(i3);
                break;
            case R.id.entrar_btn:
                Intent i4 = new Intent(this, SeleccionPlantas.class);
                startActivity(i4);
                break;
        }
    }*/
