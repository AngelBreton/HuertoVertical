package com.example.huertovertical;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SeleccionPlantas extends AppCompatActivity {
    //inicializa variable arrastrable1
    TextView textView;  // nivel 1
    TextView textView2; // nivel 2
    TextView textView3; // nivel 3
    TextView textView4; // nivel 4
    ArrayList<String> arrayList;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_plantas);

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
    }

    //metodo boton anterior
    public void Anterior(View View){
        Intent anterior = new Intent(this, MainActivity.class);
        startActivity(anterior);
    }
}