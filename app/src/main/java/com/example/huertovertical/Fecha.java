package com.example.huertovertical;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Fecha extends AppCompatActivity {



    public String año;
    public String mes;
    public String dia;

    public Fecha() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Fecha(String year, String mes, String dia) {
        this.año = year;
        this.mes = mes;
        this.dia = dia;
    }

    public Fecha(int year1, int month1, int day1) {
    }


    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }
}