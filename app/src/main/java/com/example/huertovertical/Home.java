package com.example.huertovertical;

import static com.example.huertovertical.R.layout.activity_home;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class Home extends AppCompatActivity {
    //obtiene las imagenes
    ImageView img1,img2,img3,img4;

    //obtiene fecha Actual
    private int diaActual, mesActual, añoActual;
    String diaA,mesA,añoA;

    //botones niveles
    Button BtnNuevaCosecha;
    Button BtnMonitoreo1;
    Button BtnMonitoreo2;
    Button BtnMonitoreo3;
    Button BtnMonitoreo4;

    public String mEmail,mMessage,mSubject;

    //lectura fecha
    private TextView LeeFecha;
    private TextView LeeFecha2;
    private TextView LeeFecha3;
    private TextView LeeFecha4;

    //progreso fecha
    private TextView avance1;
    private TextView avance2;
    private TextView avance3;
    private TextView avance4;

    //barras progreso
    private int CurrentProgress = 0;
    private ProgressBar progressBar;
    private ProgressBar progressBar2;
    private ProgressBar progressBar3;
    private ProgressBar progressBar4;

    //tiempos cosechas
    int tiempoHortaliza;

//    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
//    private DatabaseReference mDatabaseReference = mDatabase.getReference();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference referenceFecha1 = database.getReference();
    DatabaseReference referenceFecha2 = database.getReference();
    DatabaseReference referenceFecha3 = database.getReference();
    DatabaseReference referenceFecha4 = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(activity_home);

        mEmail = Utils.EMAIL;
        mMessage = Utils.MESSAGE;

        LeeFecha = (TextView) findViewById(R.id.LeeFecha);
        LeeFecha2 = (TextView) findViewById(R.id.LeeFecha2);
        LeeFecha3 = (TextView) findViewById(R.id.LeeFecha3);
        LeeFecha4 = (TextView) findViewById(R.id.LeeFecha4);


        BtnNuevaCosecha = (Button) findViewById(R.id.nuevaCosecha_btn);

        progressBar = findViewById(R.id.progressBar);
        progressBar2 = findViewById(R.id.progressBar2);
        progressBar3 = findViewById(R.id.progressBar3);
        progressBar4 = findViewById(R.id.progressBar4);

        //texto progreso
        avance1 = findViewById(R.id.idProgreso1);
        avance2 = findViewById(R.id.idProgreso2);
        avance3 = findViewById(R.id.idProgreso3);
        avance4 = findViewById(R.id.idProgreso4);

        Calendar fechaActual=Calendar.getInstance();
        diaActual=fechaActual.get(Calendar.DAY_OF_MONTH);
        mesActual=fechaActual.get(Calendar.MONTH);
        añoActual=fechaActual.get(Calendar.YEAR);

        diaA= String.valueOf(diaActual);
        mesA= String.valueOf(mesActual);
        añoA= String.valueOf(añoActual);

        img1= findViewById(R.id.image);
        img2= findViewById(R.id.image2);
        img3= findViewById(R.id.image3);
        img4= findViewById(R.id.image4);

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

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                referenceFecha1.addValueEventListener(new ValueEventListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                        //lee tipo de hortaliza
                        String h1= (String) datasnapshot.child("Hortaliza1").getValue();

                        //Asigna Imagen
                        switch (h1) {
                            case "Cilantro ":
                                //tiempoHortaliza = 45;
                                img1.setImageResource(R.mipmap.cilantro);
                                break;
                            case "Lechuga ":
                                img1.setImageResource(R.mipmap.lechuga);
                                break;
                            case "Brocoli ":
                                img1.setImageResource(R.mipmap.brocoli);
                                break;
                            case "Ajo ":
                                img1.setImageResource(R.mipmap.cilantro);
                                break;
                        }


                        //Valor Hortaliza
                        int t1=diasCosecha(h1);

                        //fecha hoy
                        String fecha1= (String) datasnapshot.child("FECHAACTUAL").getValue();
                        //obtiene dd/mm/aaaa
                        String[] fechaDividida = new String[3];

                        if(fecha1.length()==8){

                            fechaDividida[0] = fecha1.substring(0,2);
                            fechaDividida[1] = fecha1.substring(2,4);
                            fechaDividida[2] = fecha1.substring(4,8);
                        }
                        else{

                            fechaDividida[0] = fecha1.substring(0,2);
                            fechaDividida[1] = "0"+fecha1.substring(2,3);
                            fechaDividida[2] = fecha1.substring(3,7);

                        }



                        //fecha Inicio
                        String fecha2= (String) datasnapshot.child("FECHA1").getValue();

                        String[] fechaDividida2 = new String[3];
                        if(fecha2.length()==8){
                            fechaDividida2[0] = fecha2.substring(0,2);
                            fechaDividida2[1] = fecha2.substring(2,4);
                            fechaDividida2[2] = fecha2.substring(4,8);
                        }
                        else{
                            fechaDividida2[0] = fecha2.substring(0,2);
                            fechaDividida2[1] = "0"+fecha2.substring(2,3);
                            fechaDividida2[2] = fecha2.substring(3,7);

                        }

                        //Obtener Fecha final
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR, Integer.parseInt(fechaDividida2[2]));
                        calendar.set(Calendar.MONTH, Integer.parseInt(fechaDividida2[1])-1);
                        calendar.set(Calendar.DATE, Integer.parseInt(fechaDividida2[0]));
                        Date date = calendar.getTime();
                        calendar.add(Calendar.DAY_OF_YEAR, t1);
                        Date date2=calendar.getTime();

                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        String strDate = formatter.format(date2);

                        String[] fechaDividida3 = strDate.split("/");

                        //Si fecha hoy < fecha inicio
                        int compara = (int) diasDiferencia(fechaDividida2[2], fechaDividida2[1], fechaDividida2[0], fechaDividida[2], fechaDividida[1], fechaDividida[0]);
                        //si hoy es mas grande que la fecha inicial
                        if(compara>0){
                            int fechaCambiante = (int) diasDiferencia(fechaDividida[2], fechaDividida[1], fechaDividida[0], fechaDividida3[2], fechaDividida3[1], fechaDividida3[0])-t1;
                            int fechaCambianteAbs=Math.abs(fechaCambiante);

                            float porcentaje= (fechaCambianteAbs*100)/t1;
                            String porcentajeTexto = String.valueOf(porcentaje);
                            avance1.setText(porcentajeTexto +" %");

                            CurrentProgress = fechaCambianteAbs;
                            progressBar.setProgress(CurrentProgress);
                            progressBar.setMax(t1);
                        }
                        //si la fecha inicial es mas grande que hoy
                        else{

                            //fecha cambiante
                            int fechaCambiante = (int) diasDiferencia(fechaDividida2[2], fechaDividida2[1], fechaDividida2[0], fechaDividida3[2], fechaDividida3[1], fechaDividida3[0])-t1;
                            int fechaCambianteAbs=Math.abs(fechaCambiante);

                            float porcentaje= (fechaCambianteAbs*100)/t1;
                            String porcentajeTexto = String.valueOf(porcentaje);
                            avance1.setText(porcentajeTexto +" %");

                            CurrentProgress = fechaCambianteAbs;
                            progressBar.setProgress(CurrentProgress);
                            progressBar.setMax(t1);

                        }


                        //dias restantes
                        int fechaCambiante2 = (int) diasDiferencia(fechaDividida[2], fechaDividida[1], fechaDividida[0], fechaDividida3[2], fechaDividida3[1], fechaDividida3[0]);
                        String fechaCambianteTexto = String.valueOf(fechaCambiante2);


                        //imprime Datos
                        LeeFecha.setText("faltan "+fechaCambianteTexto+" días");

                        //Descomentar para enviar correo
                        if(fechaCambiante2==0){
                            try{
                                String asunto="Cosecha nivel 1 Finalizada";
                                String mnsj="La cosecha de  "+ h1+ " en el nivel 1 ha finalizado";
                                sendMail(asunto,mnsj);
                            }catch (Exception e){
                                Toast.makeText(Home.this,"Error al enviar correo",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                return null;
            }
        }.execute();

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                referenceFecha2.addValueEventListener(new ValueEventListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                        //lee tipo de hortaliza
                        String h1= (String) datasnapshot.child("Hortaliza2").getValue();

                        //Asigna Imagen
                        switch (h1) {
                            case "Cilantro ":
                                //tiempoHortaliza = 45;
                                img2.setImageResource(R.mipmap.cilantro);
                                break;
                            case "Lechuga ":
                                img2.setImageResource(R.mipmap.lechuga);
                                break;
                            case "Brocoli ":
                                img2.setImageResource(R.mipmap.brocoli);
                                break;
                            case "Ajo ":
                                img2.setImageResource(R.mipmap.cilantro);
                                break;
                        }


                        //Valor Hortaliza
                        int t1=diasCosecha(h1);

                        //fecha hoy
                        String fecha1= (String) datasnapshot.child("FECHAACTUAL").getValue();

                        //obtiene dd/mm/aaaa
                        String[] fechaDividida = new String[3];
                        if(fecha1.length()==8) {

                            fechaDividida[0] = fecha1.substring(0, 2);
                            fechaDividida[1] = fecha1.substring(2, 4);
                            fechaDividida[2] = fecha1.substring(4, 8);
                        }
                        else{
                            fechaDividida[0] = fecha1.substring(0,2);
                            fechaDividida[1] = "0"+fecha1.substring(2,3);
                            fechaDividida[2] = fecha1.substring(3,7);
                        }
                        //fecha Inicio
                        String fecha2= (String) datasnapshot.child("FECHAS2").getValue();

                        String[] fechaDividida2 = new String[3];

                        if(fecha2.length()==8) {
                            fechaDividida2[0] = fecha2.substring(0, 2);
                            fechaDividida2[1] = fecha2.substring(2, 4);
                            fechaDividida2[2] = fecha2.substring(4, 8);
                        }
                        else{
                            fechaDividida2[0] = fecha2.substring(0,2);
                            fechaDividida2[1] = "0"+fecha2.substring(2,3);
                            fechaDividida2[2] = fecha2.substring(3,7);
                        }
                        //Obtener Fecha final
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR, Integer.parseInt(fechaDividida2[2]));
                        calendar.set(Calendar.MONTH, Integer.parseInt(fechaDividida2[1])-1);
                        calendar.set(Calendar.DATE, Integer.parseInt(fechaDividida2[0]));
                        Date date = calendar.getTime();
                        calendar.add(Calendar.DAY_OF_YEAR, t1);
                        Date date2=calendar.getTime();

                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        String strDate = formatter.format(date2);

                        String[] fechaDividida3 = strDate.split("/");

                        //Si fecha hoy < fecha inicio
                        int compara = (int) diasDiferencia(fechaDividida2[2], fechaDividida2[1], fechaDividida2[0], fechaDividida[2], fechaDividida[1], fechaDividida[0]);
                        //si hoy es mas grande que la fecha inicial
                        if(compara>0){
                            int fechaCambiante = (int) diasDiferencia(fechaDividida[2], fechaDividida[1], fechaDividida[0], fechaDividida3[2], fechaDividida3[1], fechaDividida3[0])-t1;
                            int fechaCambianteAbs=Math.abs(fechaCambiante);

                            float porcentaje= (fechaCambianteAbs*100)/t1;
                            String porcentajeTexto = String.valueOf(porcentaje);
                            avance2.setText(porcentajeTexto +" %");

                            CurrentProgress = fechaCambianteAbs;
                            progressBar2.setProgress(CurrentProgress);
                            progressBar2.setMax(t1);


                        }
                        //si la fecha inicial es mas grande que hoy
                        else{

                            //fecha cambiante
                            int fechaCambiante = (int) diasDiferencia(fechaDividida2[2], fechaDividida2[1], fechaDividida2[0], fechaDividida3[2], fechaDividida3[1], fechaDividida3[0])-t1;
                            int fechaCambianteAbs=Math.abs(fechaCambiante);

                            float porcentaje= (fechaCambianteAbs*100)/t1;
                            String porcentajeTexto = String.valueOf(porcentaje);
                            avance2.setText(porcentajeTexto +" %");

                            CurrentProgress = fechaCambianteAbs;
                            progressBar2.setProgress(CurrentProgress);
                            progressBar2.setMax(t1);

                        }


                        //dias restantes
                        int fechaCambiante2 = (int) diasDiferencia(fechaDividida[2], fechaDividida[1], fechaDividida[0], fechaDividida3[2], fechaDividida3[1], fechaDividida3[0]);
                        String fechaCambianteTexto = String.valueOf(fechaCambiante2);



                        //imprime Datos
                        LeeFecha2.setText("faltan "+fechaCambianteTexto+" días");



                        //Descomentar para enviar correo
                        if(fechaCambiante2==0){
                            try{
                                String asunto="Cosecha nivel 2 Finalizada";
                                String mnsj="La cosecha de "+ h1+ " en el nivel 2 ha finalizado";
                                sendMail(asunto,mnsj);
                            }catch (Exception e){
                                Toast.makeText(Home.this,"Error al enviar correo",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                return null;
            }
        }.execute();

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                referenceFecha3.addValueEventListener(new ValueEventListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                        //lee tipo de hortaliza
                        String h1= (String) datasnapshot.child("Hortaliza3").getValue();

                        //Asigna Imagen
                        switch (h1) {
                            case "Cilantro ":
                                //tiempoHortaliza = 45;
                                img3.setImageResource(R.mipmap.cilantro);
                                break;
                            case "Lechuga ":
                                img3.setImageResource(R.mipmap.lechuga);
                                break;
                            case "Brocoli ":
                                img3.setImageResource(R.mipmap.brocoli);
                                break;
                            case "Ajo ":
                                img3.setImageResource(R.mipmap.cilantro);
                                break;
                        }


                        //Valor Hortaliza
                        int t1=diasCosecha(h1);

                        //fecha hoy
                        String fecha1= (String) datasnapshot.child("FECHAACTUAL").getValue();

                        //obtiene dd/mm/aaaa
                        String[] fechaDividida = new String[3];
                        if(fecha1.length()==8) {
                            fechaDividida[0] = fecha1.substring(0, 2);
                            fechaDividida[1] = fecha1.substring(2, 4);
                            fechaDividida[2] = fecha1.substring(4, 8);
                        }
                        else{
                            fechaDividida[0] = fecha1.substring(0,2);
                            fechaDividida[1] = "0"+fecha1.substring(2,3);
                            fechaDividida[2] = fecha1.substring(3,7);
                        }
                        //fecha Inicio
                        String fecha2= (String) datasnapshot.child("FECHAS3").getValue();

                        String[] fechaDividida2 = new String[3];
                        if(fecha1.length()==8) {
                            fechaDividida2[0] = fecha2.substring(0, 2);
                            fechaDividida2[1] = fecha2.substring(2, 4);
                            fechaDividida2[2] = fecha2.substring(4, 8);
                        }
                        else{
                            fechaDividida2[0] = fecha2.substring(0,2);
                            fechaDividida2[1] = "0"+fecha2.substring(2,3);
                            fechaDividida2[2] = fecha2.substring(3,7);
                        }

                        //Obtener Fecha final
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR, Integer.parseInt(fechaDividida2[2]));
                        calendar.set(Calendar.MONTH, Integer.parseInt(fechaDividida2[1])-1);
                        calendar.set(Calendar.DATE, Integer.parseInt(fechaDividida2[0]));
                        Date date = calendar.getTime();
                        calendar.add(Calendar.DAY_OF_YEAR, t1);
                        Date date2=calendar.getTime();

                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        String strDate = formatter.format(date2);

                        String[] fechaDividida3 = strDate.split("/");

                        //Si fecha hoy < fecha inicio
                        int compara = (int) diasDiferencia(fechaDividida2[2], fechaDividida2[1], fechaDividida2[0], fechaDividida[2], fechaDividida[1], fechaDividida[0]);
                        //si hoy es mas grande que la fecha inicial
                        if(compara>0){
                            int fechaCambiante = (int) diasDiferencia(fechaDividida[2], fechaDividida[1], fechaDividida[0], fechaDividida3[2], fechaDividida3[1], fechaDividida3[0])-t1;
                            int fechaCambianteAbs=Math.abs(fechaCambiante);

                            float porcentaje= (fechaCambianteAbs*100)/t1;
                            String porcentajeTexto = String.valueOf(porcentaje);
                            avance3.setText(porcentajeTexto +" %");

                            CurrentProgress = fechaCambianteAbs;
                            progressBar3.setProgress(CurrentProgress);
                            progressBar3.setMax(t1);


                        }
                        //si la fecha inicial es mas grande que hoy
                        else{

                            //fecha cambiante
                            int fechaCambiante = (int) diasDiferencia(fechaDividida2[2], fechaDividida2[1], fechaDividida2[0], fechaDividida3[2], fechaDividida3[1], fechaDividida3[0])-t1;
                            int fechaCambianteAbs=Math.abs(fechaCambiante);

                            float porcentaje= (fechaCambianteAbs*100)/t1;
                            String porcentajeTexto = String.valueOf(porcentaje);
                            avance3.setText(porcentajeTexto +" %");

                            CurrentProgress = fechaCambianteAbs;
                            progressBar3.setProgress(CurrentProgress);
                            progressBar3.setMax(t1);

                        }



                        //dias restantes
                        int fechaCambiante2 = (int) diasDiferencia(fechaDividida[2], fechaDividida[1], fechaDividida[0], fechaDividida3[2], fechaDividida3[1], fechaDividida3[0]);
                        String fechaCambianteTexto = String.valueOf(fechaCambiante2);

                        //imprime Datos
                        LeeFecha3.setText("faltan "+fechaCambianteTexto+" días");

                        //Descomentar para enviar correo
                        if(fechaCambiante2==0){
                            try{
                                String asunto="Cosecha nivel 3 Finalizada";
                                String mnsj="La cosecha de "+ h1+ " en el nivel 3 ha finalizado";
                                sendMail(asunto,mnsj);
                            }catch (Exception e){
                                Toast.makeText(Home.this,"Error al enviar correo",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                return null;
            }
        }.execute();

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                referenceFecha3.addValueEventListener(new ValueEventListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                        //lee tipo de hortaliza
                        String h1= (String) datasnapshot.child("Hortaliza3").getValue();

                        //Asigna Imagen
                        switch (h1) {
                            case "Cilantro ":
                                //tiempoHortaliza = 45;
                                img3.setImageResource(R.mipmap.cilantro);
                                break;
                            case "Lechuga ":
                                img3.setImageResource(R.mipmap.lechuga);
                                break;
                            case "Brocoli ":
                                img3.setImageResource(R.mipmap.brocoli);
                                break;
                            case "Ajo ":
                                img3.setImageResource(R.mipmap.cilantro);
                                break;
                        }


                        //Valor Hortaliza
                        int t1=diasCosecha(h1);

                        //fecha hoy
                        String fecha1= (String) datasnapshot.child("FECHAACTUAL").getValue();

                        //obtiene dd/mm/aaaa
                        String[] fechaDividida = new String[3];
                        if(fecha1.length()==8) {
                            fechaDividida[0] = fecha1.substring(0, 2);
                            fechaDividida[1] = fecha1.substring(2, 4);
                            fechaDividida[2] = fecha1.substring(4, 8);
                        }
                        else{
                            fechaDividida[0] = fecha1.substring(0,2);
                            fechaDividida[1] = "0"+fecha1.substring(2,3);
                            fechaDividida[2] = fecha1.substring(3,7);
                        }
                        //fecha Inicio
                        String fecha2= (String) datasnapshot.child("FECHAS3").getValue();

                        String[] fechaDividida2 = new String[3];
                        if(fecha1.length()==8) {
                            fechaDividida2[0] = fecha2.substring(0, 2);
                            fechaDividida2[1] = fecha2.substring(2, 4);
                            fechaDividida2[2] = fecha2.substring(4, 8);
                        }
                        else{
                            fechaDividida2[0] = fecha2.substring(0,2);
                            fechaDividida2[1] = "0"+fecha2.substring(2,3);
                            fechaDividida2[2] = fecha2.substring(3,7);
                        }

                        //Obtener Fecha final
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR, Integer.parseInt(fechaDividida2[2]));
                        calendar.set(Calendar.MONTH, Integer.parseInt(fechaDividida2[1])-1);
                        calendar.set(Calendar.DATE, Integer.parseInt(fechaDividida2[0]));
                        Date date = calendar.getTime();
                        calendar.add(Calendar.DAY_OF_YEAR, t1);
                        Date date2=calendar.getTime();

                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                        String strDate = formatter.format(date2);

                        String[] fechaDividida3 = strDate.split("/");

                        //Si fecha hoy < fecha inicio
                        int compara = (int) diasDiferencia(fechaDividida2[2], fechaDividida2[1], fechaDividida2[0], fechaDividida[2], fechaDividida[1], fechaDividida[0]);
                        //si hoy es mas grande que la fecha inicial
                        if(compara>0){
                            int fechaCambiante = (int) diasDiferencia(fechaDividida[2], fechaDividida[1], fechaDividida[0], fechaDividida3[2], fechaDividida3[1], fechaDividida3[0])-t1;
                            int fechaCambianteAbs=Math.abs(fechaCambiante);

                            float porcentaje= (fechaCambianteAbs*100)/t1;
                            String porcentajeTexto = String.valueOf(porcentaje);
                            avance3.setText(porcentajeTexto +" %");

                            CurrentProgress = fechaCambianteAbs;
                            progressBar3.setProgress(CurrentProgress);
                            progressBar3.setMax(t1);


                        }
                        //si la fecha inicial es mas grande que hoy
                        else{

                            //fecha cambiante
                            int fechaCambiante = (int) diasDiferencia(fechaDividida2[2], fechaDividida2[1], fechaDividida2[0], fechaDividida3[2], fechaDividida3[1], fechaDividida3[0])-t1;
                            int fechaCambianteAbs=Math.abs(fechaCambiante);

                            float porcentaje= (fechaCambianteAbs*100)/t1;
                            String porcentajeTexto = String.valueOf(porcentaje);
                            avance3.setText(porcentajeTexto +" %");

                            CurrentProgress = fechaCambianteAbs;
                            progressBar3.setProgress(CurrentProgress);
                            progressBar3.setMax(t1);

                        }



                        //dias restantes
                        int fechaCambiante2 = (int) diasDiferencia(fechaDividida[2], fechaDividida[1], fechaDividida[0], fechaDividida3[2], fechaDividida3[1], fechaDividida3[0]);
                        String fechaCambianteTexto = String.valueOf(fechaCambiante2);

                        //imprime Datos
                        LeeFecha3.setText("faltan "+fechaCambianteTexto+" días");

                        //Descomentar para enviar correo
                        if(fechaCambiante2==0){
                            try{
                                String asunto="Cosecha nivel 3 Finalizada";
                                String mnsj="La cosecha de "+ h1+ " en el nivel 3 ha finalizado";
                                sendMail(asunto,mnsj);
                            }catch (Exception e){
                                Toast.makeText(Home.this,"Error al enviar correo",Toast.LENGTH_SHORT).show();
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                return null;
            }
        }.execute();









    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public long diasDiferencia(String añoI, String mesI, String diaI, String añoF, String mesF, String diaF){
        String dateBeforeString = añoI+"-"+mesI+"-"+diaI;
        String dateAfterString = añoF+"-"+mesF+"-"+diaF;

   //     String dateBeforeString = "2017-05-24";
   //     String dateAfterString = "2017-07-29";

        //Convertir a tipo Fecha
        LocalDate dateBefore = LocalDate.parse(dateBeforeString);
        LocalDate dateAfter = LocalDate.parse(dateAfterString);

        //calcula dias diferencia
        long noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);

        //displaying the number of days
        //System.out.println(noOfDaysBetween);

        return noOfDaysBetween;
    }

    public int diasCosecha(String hortaliza){
        switch (hortaliza) {
            case "Cilantro ":
                //tiempoHortaliza = 45;
                tiempoHortaliza = 5;
                break;
            case "Lechuga ":
                 tiempoHortaliza = 60;
                break;
            case "Brocoli ":
                tiempoHortaliza = 90;
                break;
            case "Ajo ":
                tiempoHortaliza = 120;
                break;
        }
        return tiempoHortaliza;
    }

    public void sendMail(String subject,String mnsj){
        String mail= mEmail;
        String message = mMessage;



        //send mail
        JavaMailAPI javaMailAPI = new JavaMailAPI(this,mail,subject,message,mnsj);
        javaMailAPI.execute();
    }

    public String asignaImagen(String img){
        switch (img) {
            case "Cilantro ":
                //tiempoHortaliza = 45;
                img = "cilantro";
                break;
            case "Lechuga ":
                 img = "lechuga";
                break;
            case "Brocoli ":
                img = "brocoli";
                break;
            case "Ajo ":
                img = "cilantro";
                break;
        }
        return img;

    }


}