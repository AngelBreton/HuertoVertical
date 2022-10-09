package com.example.huertovertical;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private Button notificacion;

    public String mEmail,mMessage,mSubject;

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
        mSubject = Utils.SUBJECT;

        notificacion = (Button) findViewById(R.id.button4);
//        temp.setText("Hola mundo");


        referenceEntrada1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                //String estadoSensor = datasnapshot.getValue().toString();
                String estadoSensor= datasnapshot.child("cond").getValue().toString();
                String estadoSensor2= datasnapshot.child("ph").getValue().toString();
//                    System.out.println("conductividad es "+ estadoSensor);
//                    System.out.println("conductividad es "+ estadoSensor2);
//                for(DataSnapshot snapshot: datasnapshot.getChildren()){
//
//                    String estadoSensor10 = snapshot.child("cond ").getValue().toString();
//                    System.out.println("conductividad es "+ estadoSensor10);
//                }
                //                String estadoSensor2 = snapshot.child("ph").getValue().toString();
                String estadoSensor3 = datasnapshot.child("temp").getValue().toString();
                cond.setText(estadoSensor+"ppm");
                ph.setText(estadoSensor2);
                temp.setText(estadoSensor3+"°C");

                if(Double.valueOf(estadoSensor)<200||Double.valueOf(estadoSensor2)<4||Double.valueOf(estadoSensor3)>100){
                    try{
                        sendMail();
                    }catch (Exception e){
                        Toast.makeText(Monitoreo1.this,"Error al enviar correo",Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        notificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ButtonSendEmail();
            }
        });
    }

    public void sendMail(){
        String mail= mEmail;
        String message = mMessage;
        String subject = mSubject;

        //send mail
        JavaMailAPI javaMailAPI = new JavaMailAPI(this,mail,subject,message);
        javaMailAPI.execute();
    }

    public void ButtonSendEmail(){
        String senderEmail = "angel.breton.1995@gmail.com";
        String recieverEmail = "angel.breton.1995@gmail.com";

        String stringPasswordSenderEmail = "ultra1A_";
        String stringHost = "smtp.gmail.com";

        Properties properties = System.getProperties();

        properties.put("mail.smtp.host",stringHost);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail,stringPasswordSenderEmail);
            }
        });

        MimeMessage mimeMessage = new MimeMessage(session);


        try{
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recieverEmail));
            mimeMessage.setSubject("Subject: Android app email");
            mimeMessage.setText("Soy un correo mamalon \n \n hola");

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Transport.send(mimeMessage);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
            });

            thread.start();


        }catch (AddressException e){
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}