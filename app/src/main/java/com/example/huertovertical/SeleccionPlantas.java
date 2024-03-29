package com.example.huertovertical;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.text.DateFormat;
import java.util.Calendar;

public class SeleccionPlantas extends AppCompatActivity{
    //inicializa variable arrastrable1
    TextView textView;  // nivel 1
    TextView textView2; // nivel 2
    TextView textView3; // nivel 3
    TextView textView4; // nivel 4
    ArrayList<String> arrayList;
    Dialog dialog;

    //Inicializa Variables fechas
    DatePickerDialog picker;
    EditText eText;
    DatePickerDialog picker2;
    EditText eText2;
    DatePickerDialog picker3;
    EditText eText3;
    DatePickerDialog picker4;
    EditText eText4;

    //Boton Guardar
    Button BtnGuardarCosecha;

    //Objetos Fecha
    public Fecha fecha1;
    public Fecha fecha2;
    public Fecha fecha3;
    public Fecha fecha4;

    //Instancia Firebase
   // private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    //private DatabaseReference mDatabaseReference = mDatabase.getReference();

    public DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_plantas);

        BtnGuardarCosecha = (Button) findViewById(R.id.guardarCosecha_btn);


        //asigna variable fecha
        eText=(EditText) findViewById(R.id.editText1);
        eText.setInputType(InputType.TYPE_NULL);
        eText2=(EditText) findViewById(R.id.editText2);
        eText2.setInputType(InputType.TYPE_NULL);
        eText3=(EditText) findViewById(R.id.editText3);
        eText3.setInputType(InputType.TYPE_NULL);
        eText4=(EditText) findViewById(R.id.editText4);
        eText4.setInputType(InputType.TYPE_NULL);

        //asignar variable
        textView = findViewById(R.id.text_view);    //nivel 1
        textView2 = findViewById(R.id.text_view2);  //nivel 2
        textView3 = findViewById(R.id.text_view3);  //nivel 3
        textView4 = findViewById(R.id.text_view4);  //nivel 3

        //inicializa arraylist
        arrayList = new ArrayList<>();
        //agrega valores en el array
        arrayList.add("Fresa");
        arrayList.add("Frambuesa ");
        arrayList.add("Chile piquín ");
        arrayList.add("Ajo ");
        arrayList.add("Repollo ");
        arrayList.add("Brócoli ");
        arrayList.add("Acelga ");
        arrayList.add("Apio ");
        arrayList.add("Cilantro ");
        arrayList.add("Pimiento ");
        arrayList.add("Perejil ");
        arrayList.add("Habanero ");
        arrayList.add("Tomate ");
        arrayList.add("Cebollín ");
        arrayList.add("Espinaca ");
        arrayList.add("Chile de árbol ");
        arrayList.add("Lechuga ");

        //Inicia evento click en el spinner 1
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //inicializar dialogo
                dialog = new Dialog(SeleccionPlantas.this);
            //establecer dialogo personalizado
                dialog.setContentView(R.layout.dialog_searchable_spinner);
            //Establecer altura y ancho personalizado
                dialog.getWindow().setLayout(650,800);
                //Establecer fondo transparence
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //Mostrar Dialogo
                dialog.show();

            //Inicializar y asignar Variable
                EditText editText = dialog.findViewById(R.id.edit_text);
                ListView listView = dialog.findViewById(R.id.lsit_view);

            //Inicializar adaptador array
            ArrayAdapter<String> adapter = new ArrayAdapter<>(SeleccionPlantas.this , android.R.layout.simple_list_item_1,arrayList);
            //Establecer Adaptador
            listView.setAdapter(adapter);
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    //Filtro dentro del array de opciones
                    adapter.getFilter().filter(charSequence);
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                  //cuando el item es seleccionado de la lista
                  //establecer item seleccionado en text view
                    textView.setText(adapter.getItem(i));
                  //despedir dialogo
                    dialog.dismiss();
                }
            });
            }
        }); // finaliza metodo spinner 1
        // evento click en el spinner 2
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //inicializar dialogo
                dialog = new Dialog(SeleccionPlantas.this);
                //establecer dialogo personalizado
                dialog.setContentView(R.layout.dialog_searchable_spinner);
                //Establecer altura y ancho personalizado
                dialog.getWindow().setLayout(650,800);
                //Establecer fondo transparence
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //Mostrar Dialogo
                dialog.show();

                //Inicializar y asignar Variable
                EditText editText = dialog.findViewById(R.id.edit_text);
                ListView listView = dialog.findViewById(R.id.lsit_view);

                //Inicializar adaptador array
                ArrayAdapter<String> adapter = new ArrayAdapter<>(SeleccionPlantas.this , android.R.layout.simple_list_item_1,arrayList);
                //Establecer Adaptador
                listView.setAdapter(adapter);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        //Filtro dentro del array de opciones
                        adapter.getFilter().filter(charSequence);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        //cuando el item es seleccionado de la lista
                        //establecer item seleccionado en text view
                        textView2.setText(adapter.getItem(i));
                        //despedir dialogo
                        dialog.dismiss();
                    }
                });
            }
        }); //finaliza metodo spinner 2

        // Inicia evento click en el spinner 3
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //inicializar dialogo
                dialog = new Dialog(SeleccionPlantas.this);
                //establecer dialogo personalizado
                dialog.setContentView(R.layout.dialog_searchable_spinner);
                //Establecer altura y ancho personalizado
                dialog.getWindow().setLayout(650,800);
                //Establecer fondo transparence
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //Mostrar Dialogo
                dialog.show();

                //Inicializar y asignar Variable
                EditText editText = dialog.findViewById(R.id.edit_text);
                ListView listView = dialog.findViewById(R.id.lsit_view);

                //Inicializar adaptador array
                ArrayAdapter<String> adapter = new ArrayAdapter<>(SeleccionPlantas.this , android.R.layout.simple_list_item_1,arrayList);
                //Establecer Adaptador
                listView.setAdapter(adapter);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        //Filtro dentro del array de opciones
                        adapter.getFilter().filter(charSequence);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        //cuando el item es seleccionado de la lista
                        //establecer item seleccionado en text view
                        textView3.setText(adapter.getItem(i));
                        //despedir dialogo
                        dialog.dismiss();
                    }
                });
            }
        }); //finaliza metodo spinner 3

        // Inicia evento click en el spinner 4
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //inicializar dialogo
                dialog = new Dialog(SeleccionPlantas.this);
                //establecer dialogo personalizado
                dialog.setContentView(R.layout.dialog_searchable_spinner);
                //Establecer altura y ancho personalizado
                dialog.getWindow().setLayout(650,800);
                //Establecer fondo transparence
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //Mostrar Dialogo
                dialog.show();

                //Inicializar y asignar Variable
                EditText editText = dialog.findViewById(R.id.edit_text);
                ListView listView = dialog.findViewById(R.id.lsit_view);

                //Inicializar adaptador array
                ArrayAdapter<String> adapter = new ArrayAdapter<>(SeleccionPlantas.this , android.R.layout.simple_list_item_1,arrayList);
                //Establecer Adaptador
                listView.setAdapter(adapter);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        //Filtro dentro del array de opciones
                        adapter.getFilter().filter(charSequence);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        //cuando el item es seleccionado de la lista
                        //establecer item seleccionado en text view
                        textView4.setText(adapter.getItem(i));
                        //despedir dialogo
                        dialog.dismiss();
                    }
                });
            }
        }); //finaliza metodo spinner 4

        //Inicia metodo Obtener Fecha1
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Fecha fecha1;
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                // date picker dialog
                picker = new DatePickerDialog(SeleccionPlantas.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);

                picker.show();
            }
        });

        //Inicia metodo Obtener Fecha2
        eText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                fecha2= new Fecha(year, month,day);
                // date picker dialog
                picker2 = new DatePickerDialog(SeleccionPlantas.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eText2.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker2.show();
            }
        });

        //Inicia metodo Obtener Fecha3
        eText3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                fecha3= new Fecha(year, month,day);
                // date picker dialog
                picker3 = new DatePickerDialog(SeleccionPlantas.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eText3.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker3.show();
            }
        });

        //Inicia metodo Obtener Fecha4
        eText4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                fecha4= new Fecha(year, month,day);
                // date picker dialog
                picker4 = new DatePickerDialog(SeleccionPlantas.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eText4.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker4.show();
            }
        });


        BtnGuardarCosecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                mDatabaseReference = mDatabase.getReference().child("name");
//                mDatabaseReference.setValue("Donald Duck");

                mDatabase = FirebaseDatabase.getInstance().getReference();


                guardarCosecha("001","02","03");
                guardarCosecha(fecha1.getAño(),fecha1.getMes(),fecha1.getDia());

//                guardarCosecha2(fecha2.getAño(),fecha2.getMes(),fecha2.getDia());
//                guardarCosecha3(fecha3.getAño(),fecha3.getMes(),fecha3.getDia());
//                guardarCosecha4(fecha4.getAño(),fecha4.getMes(),fecha4.getDia());

//                Intent i = new Intent(SeleccionPlantas.this, Home.class);
//                startActivity(i);
            }
        });



    }

    //metodo boton anterior
    public void Anterior(View View){
        Intent anterior = new Intent(this, MainActivity.class);
        startActivity(anterior);
    }

    public void guardarCosecha(String año, String mes, String dia) {
        Fecha fecha = new Fecha(año, mes, dia);
        mDatabase.child("fecha1").setValue(fecha);

    }

    public void guardarCosecha2(String año, String mes, String dia) {
        Fecha fecha = new Fecha(año, mes, dia);
        mDatabase.child("fecha2").setValue(fecha);
    }

    public void guardarCosecha3(String año, String mes, String dia) {
        Fecha fecha = new Fecha(año, mes, dia);
        mDatabase.child("fecha3").setValue(fecha);
    }

    public void guardarCosecha4(String año, String mes, String dia) {
        Fecha fecha = new Fecha(año, mes, dia);
        mDatabase.child("fecha4").setValue(fecha);

    }
}