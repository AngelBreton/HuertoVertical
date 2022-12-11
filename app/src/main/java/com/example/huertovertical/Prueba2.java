package com.example.huertovertical;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Prueba2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prueba2);

        Button btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView text = findViewById(R.id.text);

                EditText numero1 = findViewById(R.id.numero1);
                EditText numero2 = findViewById(R.id.numero2);

                String n1 = numero1.getText().toString();
                String n2 = numero1.getText().toString();

                int valor1 = Integer.parseInt(n1);
                int valor2 = Integer.parseInt(n1);

                int resultado = valor1 + valor2;
            }
        });


    }
}
