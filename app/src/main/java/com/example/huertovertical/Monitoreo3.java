package com.example.huertovertical;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Monitoreo3 extends Activity {
    private TextView temp3,cond3,ph3;

    public String mEmail,mMessage;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference referenceEntrada3 = database.getReference("LECTURAS3");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoreo3);
        temp3 = (TextView) findViewById(R.id.temp3);
        cond3 = (TextView) findViewById(R.id.cond3);
        ph3 = (TextView) findViewById(R.id.ph3);

        mEmail = Utils.EMAIL;
        mMessage = Utils.MESSAGE;

        referenceEntrada3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String estadoSensor = snapshot.child("cond3").getValue().toString();
                String estadoSensor2 = snapshot.child("ph3").getValue().toString();
                String estadoSensor3 = snapshot.child("temp3").getValue().toString();
                cond3.setText(estadoSensor);
                ph3.setText(estadoSensor2);
                temp3.setText(estadoSensor3);

                //estadoSensor- cond , estadoSensor- ph,  estadoSensor -temp
                if(Double.valueOf(estadoSensor)<200||Double.valueOf(estadoSensor2)<4||Double.valueOf(estadoSensor3)>100){
                    try{
                        String asunto="Nivel 3 fuera de rango";
                        String varError = "conductividad "+ estadoSensor + " ppm, " +estadoSensor2 + " ph, " + estadoSensor3 + " °C temperatura ";
                        sendMail(asunto,varError);

                    }catch (Exception e){
                        Toast.makeText(Monitoreo3.this,"Error al enviar correo",Toast.LENGTH_SHORT).show();
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