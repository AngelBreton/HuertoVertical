package com.example.huertovertical;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.security.auth.Subject;




public class Monitoreo1 extends Activity {


    private TextView temp,cond,ph;


    public String mEmail,mMessage;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference referenceEntrada1 = database.getReference().child("LECTURAS");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoreo1);
        temp = (TextView) findViewById(R.id.temp);
        cond = (TextView) findViewById(R.id.cond);
        ph = (TextView) findViewById(R.id.ph);

        mEmail = Utils.EMAIL;
        mMessage = Utils.MESSAGE;




        referenceEntrada1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                String estadoSensor= datasnapshot.child("cond").getValue().toString();
                String estadoSensor2= datasnapshot.child("ph").getValue().toString();
                String estadoSensor3 = datasnapshot.child("temp").getValue().toString();
                cond.setText(estadoSensor+"ppm");
                ph.setText(estadoSensor2);
                temp.setText(estadoSensor3+"°C");

                //estadoSensor- cond , estadoSensor- ph,  estadoSensor -temp
                if(Double.valueOf(estadoSensor)<200||Double.valueOf(estadoSensor2)<4||Double.valueOf(estadoSensor3)>100){
                    try{
                        String asunto="Nivel 1 fuera de rango";
                        String varError = "conductividad "+ estadoSensor + " ppm, " +estadoSensor2 + " ph, " + estadoSensor3 + " °C temperatura ";
                        sendMail(asunto,varError);

                    }catch (Exception e){
                        Toast.makeText(Monitoreo1.this,"Error al enviar correo",Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }

    public void sendMail(String subject ,String varError){
        String mail= mEmail;
        String message = mMessage;




        //send mail
        JavaMailAPI javaMailAPI = new JavaMailAPI(this,mail,subject,message,varError);
        javaMailAPI.execute();
    }




}