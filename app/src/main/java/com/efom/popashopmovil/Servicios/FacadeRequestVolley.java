package com.efom.popashopmovil.Servicios;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;

public class FacadeRequestVolley {

    static RequestQueue queue;
    static StringRequest stringRequest;
    static Context context;
    static ComunicaVolley comunicaVolley;

    public FacadeRequestVolley(ComunicaVolley comunicaVolley, Context context) {
        /******* LA VISTA PERTENCE AL LUGAR DONDE DE EJECUTA Y LA ACTIVIDAD DEL LUGAR ******/
        //contexto de lugar de ejecucion. Ej: fragmento = Fragment.this, actividad = Main.this
        FacadeRequestVolley.comunicaVolley = comunicaVolley;
        //contexto de actividad. Ej: fragmento = getActivity, actividad = Main.this
        FacadeRequestVolley.context = context;
    }

    public void peticion(String datos, String fechas, String user_tipo_limite, String url){
        queue = Volley.newRequestQueue(context);


        //variables enviadas por post al webservice
        final String DATOSP = datos;
        final String FECHASP = fechas;
        final String USER = user_tipo_limite;
        final String URL = url;

        stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                /******* ENVIA EL STRING RESPONSE POR UNA INTERFAZ ******/
                comunicaVolley.completeSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                comunicaVolley.completeError(error);
            }
        }){
            /*=============      ENVIAR POR POST AL WEBSERVISE      ===============*/
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("datos", DATOSP);
                params.put("fechas", FECHASP);
                params.put("user_tipo_limite", USER);
                return params;
            }
        };
        /******* SE USA EL PATRON SINGLETON EVITAR QUE SE DUPLIQUE ESTE METODO ******/
        stringRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) {

            }
        });
        MySingletonVolley.getInstance(context).addToRequestQueue(stringRequest);
    }
}