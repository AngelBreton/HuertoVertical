package com.example.huertovertical;

import static com.example.huertovertical.R.layout.activity_home;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

public class Home extends AppCompatActivity {
    //obtiene fecha Actual
    private int diaActual, mesActual, añoActual;
    String diaA,mesA,añoA;

    //obtiene fecha Inicio
    private int diaInicio, mesInicio, añoInicio,fechaInicio;
    String diaI,mesI,añoI,fechaI;

    //obtiene fecha Final
    private int diaFinal, mesFinal, añoFinal,fechaFinal;
    String diaF,mesF,añoF,fechaF;


    //botones niveles
    Button BtnNuevaCosecha;
    Button BtnMonitoreo1;
    Button BtnMonitoreo2;
    Button BtnMonitoreo3;
    Button BtnMonitoreo4;


    public String mEmail,mMessage,mSubject;

    //lectura fecha
    private TextView LeeFecha;

    //progreso fecha
    private TextView avance1;

    //barras progreso
    private int CurrentProgress = 0;
    private ProgressBar progressBar;
    private Button startProgress;

    String g1;
    String g2;
    String g3;
    String g4;

    //tiempos cosechas
    int tiempoCilantro=14;

//    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
//
//    private DatabaseReference mDatabaseReference = mDatabase.getReference();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference referenceFecha1 = database.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(activity_home);

        LeeFecha = (TextView) findViewById(R.id.LeeFecha);


        BtnNuevaCosecha = (Button) findViewById(R.id.nuevaCosecha_btn);

        progressBar = findViewById(R.id.progressBar);
        startProgress = findViewById(R.id.startProgress);

        //texto progreso
        avance1 = findViewById(R.id.idProgreso1);

        Calendar fechaActual=Calendar.getInstance();
        diaActual=fechaActual.get(Calendar.DAY_OF_MONTH);
        mesActual=fechaActual.get(Calendar.MONTH);
        añoActual=fechaActual.get(Calendar.YEAR);

        diaA= String.valueOf(diaActual);
        mesA= String.valueOf(mesActual);
        añoA= String.valueOf(añoActual);


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


        //metodo progreso1



        //barra de progreso borrar ??
        /*
        LeeFecha.addValueEventListener(new  ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                int ig1= Integer.parseInt(g1);
                CurrentProgress = ig1 +10;
                progressBar.setProgress(CurrentProgress);
                progressBar.setMax(tiempoCilantro);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
         */


        //evento si cambia la referencia
        referenceFecha1.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                //fecha hoy
                String fecha1= (String) datasnapshot.child("FECHAACTUAL").getValue();

                String[] fechaDividida = new String[3];
                fechaDividida[0] = fecha1.substring(0,2);
                fechaDividida[1] = fecha1.substring(2,4);
                fechaDividida[2] = fecha1.substring(4,8);

                //fecha hortaliza
                String fecha2= (String) datasnapshot.child("FECHA1").getValue();

                String[] fechaDividida2 = new String[3];
                fechaDividida2[0] = fecha2.substring(0,2);
                fechaDividida2[1] = fecha2.substring(2,4);
                fechaDividida2[2] = fecha2.substring(4,8);

                String diferencia = Long.toString(diasDiferencia(fechaDividida[2], fechaDividida[1], fechaDividida[0],
                        fechaDividida2[2], fechaDividida2[1], fechaDividida2[0]));




//                fechaInicio= Integer.parseInt(fecha1);
//
//
//                CurrentProgress = ig1 +10;
//                progressBar.setProgress(CurrentProgress);
//                progressBar.setMax(tiempoCilantro);







                LeeFecha.setText(fecha1);
//                avance1.setText(fechaDividida[0]+fechaDividida[1]+fechaDividida[2]+"%");
                avance1.setText("Faltan"+ diferencia+"días");


                //Descomentar para enviar correo
//                if(Double.valueOf(estadoSensor)<200D){
//                    try{
//                        sendMail();
//                    }catch (Exception e){
//                        Toast.makeText(Home.this,"Error al enviar correo",Toast.LENGTH_SHORT).show();
//                    }
//                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



//        mDatabaseReference = mDatabase.getReference().child("name");
     //   mDatabaseReference.setValue("Donald Duck");


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public long diasDiferencia(String añoI, String mesI, String diaI, String añoF, String mesF, String diaF){
        String dateBeforeString = añoI+"-"+mesI+"-"+diaI;
        String dateAfterString = añoF+"-"+mesF+"-"+diaF;
//
//        String dateBeforeString = "2017-05-24";
//        String dateAfterString = "2017-07-29";

        //Convertir a tipo Fecha
        LocalDate dateBefore = LocalDate.parse(dateBeforeString);
        LocalDate dateAfter = LocalDate.parse(dateAfterString);

        //calcula dias diferencia
        long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);

        //displaying the number of days
        System.out.println(noOfDaysBetween);

        return noOfDaysBetween;
    }

    public void sendMail(){
        String mail= mEmail;
        String message = mMessage;
        String subject = mSubject;

        //send mail
        JavaMailAPI javaMailAPI = new JavaMailAPI(this,mail,subject,message);
        javaMailAPI.execute();
    }

    public void ObtenerMes(){

        switch (mesInicio) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                diaInicio = 31;
                break;
            case 4: case 6: case 9: case 11:
                diaInicio = 30;
                break;
            case 2:
                diaInicio = 28;
                break;
        }

        switch (mesFinal) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                diaFinal = 31;
                break;
            case 4: case 6: case 9: case 11:
                diaFinal = 30;
                break;
            case 2:
                diaFinal = 28;
                break;
        }


    }

//    public void DiasRestantes(){
//        fechaInicio=diaInicio+diaInicio;
//        int diasInicio=diaActual+mesActual;
//        int ig1= Integer.parseInt(g1);
//        CurrentProgress = ig1 +10;
//        progressBar.setProgress(CurrentProgress);
//        progressBar.setMax(tiempoCilantro);
//
//    }
}