package com.example.huertovertical;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Monitoreo2 extends Activity {
    private TextView temp2,cond2,ph2;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference referenceEntrada2 = database.getReference("LECTURAS2");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoreo2);
        temp2 = (TextView) findViewById(R.id.temp2);
        cond2 = (TextView) findViewById(R.id.cond2);
        ph2 = (TextView) findViewById(R.id.ph2);


        referenceEntrada2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String estadoSensor = snapshot.child("cond2").getValue().toString();
                String estadoSensor2 = snapshot.child("ph2").getValue().toString();
                String estadoSensor3 = snapshot.child("temp2").getValue().toString();
                cond2.setText(estadoSensor);
                ph2.setText(estadoSensor2);
                temp2.setText(estadoSensor3);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}