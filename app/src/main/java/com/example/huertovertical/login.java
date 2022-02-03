package com.example.huertovertical;

import static com.example.huertovertical.R.layout.activity_login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class login extends Activity {
    Button BtnLogin2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_login);

        BtnLogin2 = (Button) findViewById(R.id.login_btn2);

        BtnLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login.this, Home.class);
                startActivity(i);
            }
        });
    }


}
//public class Your_Class_Name extends Activity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.Your_XML_FileName);
//        ...
//    }
//}