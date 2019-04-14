package com.efom.popashopmovil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    Login login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     login= new Login();

     //login.prueba();

        Intent intent = new Intent(MainActivity.this, Login.class);
        startActivity(intent);
    }
}
