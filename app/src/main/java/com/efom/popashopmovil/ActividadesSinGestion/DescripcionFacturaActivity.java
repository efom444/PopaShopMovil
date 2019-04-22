package com.efom.popashopmovil.ActividadesSinGestion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.efom.popashopmovil.AdaptadoresListas.AdaptadorMuestraFactura;
import com.efom.popashopmovil.Objetos.DescripcionFacturaDetails;
import com.efom.popashopmovil.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DescripcionFacturaActivity extends AppCompatActivity {

    private DescripcionFacturaDetails descripcionFacturaDetails;
    private List<DescripcionFacturaDetails> muestraFactura;
    private Gson gson;
    private AdaptadorMuestraFactura adaptadorMuestraFactura;

    private GridView lista;
    private TextView Usuario, fecha, total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion_factura);


        gson = new Gson();

        lista = (GridView) findViewById(R.id.Products);
        Usuario = (TextView) findViewById(R.id.Usuario2);
        fecha = (TextView) findViewById(R.id.fecha2);
        total = (TextView) findViewById(R.id.TotalPrecio2);


        final String idFactura = getIntent().getStringExtra("idFactura");
        final String fecha1 = getIntent().getStringExtra("fecha");
        final String total1 = getIntent().getStringExtra("total");
        final String usuario = getIntent().getStringExtra("usuario");


        Usuario.setText(usuario);
        total.setText(total1);
        fecha.setText(fecha1);


        //String url = "http://192.168.43.123:8080/miTiendaOnline/?c=AuthMovil";
        String url = "http://192.168.43.127/miTiendaOnline/?c=FacturaMovil&a=traerPorFacturas";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                muestraFactura = gson.fromJson(response, new TypeToken<List<DescripcionFacturaDetails>>() {
                }.getType());

                adaptadorMuestraFactura = new AdaptadorMuestraFactura(DescripcionFacturaActivity.this, muestraFactura);
                lista.setAdapter(adaptadorMuestraFactura);


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
                params.put("idFactura", idFactura.toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }


}
