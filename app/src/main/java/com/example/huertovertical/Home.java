package com.example.huertovertical;

import static com.example.huertovertical.R.layout.activity_home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity {
    Button BtnNuevaCosecha;
    Button BtnMonitoreo1;
    Button BtnMonitoreo2;
    Button BtnMonitoreo3;
    Button BtnMonitoreo4;


    private int CurrentProgress = 0;
    private ProgressBar progressBar;
    private Button startProgress;

//    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
//
//    private DatabaseReference mDatabaseReference = mDatabase.getReference();



    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(activity_home);

        BtnNuevaCosecha = (Button) findViewById(R.id.nuevaCosecha_btn);

        progressBar = findViewById(R.id.progressBar);
        startProgress = findViewById(R.id.startProgress);

        BtnNuevaCosecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, SeleccionPlantas.class);
                startActivity(i);
            }
        });

        BtnMonitoreo1 = (Button) findViewById(R.id.monitorea_btn1);
        BtnMonitoreo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, Monitoreo1.class);
                startActivity(i);
            }
        });

        BtnMonitoreo2 = (Button) findViewById(R.id.monitorea_btn2);
        BtnMonitoreo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, Monitoreo2.class);
                startActivity(i);
            }
        });

        BtnMonitoreo3 = (Button) findViewById(R.id.monitorea_btn3);
        BtnMonitoreo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, Monitoreo3.class);
                startActivity(i);
            }
        });

        BtnMonitoreo4 = (Button) findViewById(R.id.monitorea_btn4);
        BtnMonitoreo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, Monitoreo4.class);
                startActivity(i);
            }
        });

        startProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CurrentProgress = CurrentProgress +10;
                progressBar.setProgress(CurrentProgress);
                progressBar.setMax(100);
            }
        });

//        mDatabaseReference = mDatabase.getReference().child("name");
     //   mDatabaseReference.setValue("Donald Duck");


    }
}