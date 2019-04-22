package com.efom.popashopmovil.ActividadesSinGestion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.efom.popashopmovil.R;
import com.efom.popashopmovil.RegistroActivity;

public class AccesoPrincipalActivity extends Activity {
    private Button btnRegistrarse, btnIniciar;
    private static final int Registrer = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso_aplicacion);


        btnIniciar = (Button) findViewById(R.id.btnIniciar);
        btnRegistrarse = (Button) findViewById(R.id.btnRegistrarse);


        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AccesoPrincipalActivity.this, IniciarSesionActivity.class);
                startActivity(intent);
            }
        });


        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tiposdeDocumento();
            }
        });


    }


    private void tiposdeDocumento() {

        //String url = "http://192.168.43.123:8080/MiTiendaOnline/private/administrador/?c=TipoDocumentoMovil&a=actionCreate";
        String url = "http://192.168.43.127/MiTiendaOnline/?c=TipoDocumentoMovil&a=actionCreate";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Intent intent = new Intent(AccesoPrincipalActivity.this, RegistroActivity.class);
                intent.putExtra("tiposDocumentosBD", response);
                startActivityForResult(intent, Registrer);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "Verifique su conexion a la red".toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),
                        "Sin conexion no puedes registrarte".toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {


        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
// Esto es lo que hace mi botón al pulsar ir a atrás

            moveTaskToBack(true);

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Registrer:


                    break;


            }
        }
    }
}
