package com.efom.popashopmovil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Login();
    }

    public void Login() {
        //String url = "http://192.168.43.123:8080/miTiendaOnline/?c=AuthMovil";
        String url = "http://192.168.1.7/servicioMovil/login.php?1login=crr&password=crr";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                String[] retorno = response.trim().split("-");
                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                if (retorno[0].trim().equals("error")) {
                    Toast.makeText(getApplicationContext(), retorno[1], Toast.LENGTH_SHORT).show();
                } else {
                    if (retorno[0].trim().equals("usuario")) {
                        // Toast.makeText(getApplicationContext(), "Bienvenid@ ".toString() + edtUserName.getText().toString(), Toast.LENGTH_SHORT).show();

                        // usuarioLogin = retorno[1];
                        // obtenerProductos();
                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "Usuario no permitido", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        "Error al conectarse".toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "Verifique su conexion a la red".toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("login", "crr");
                params.put("password", "crr");
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
