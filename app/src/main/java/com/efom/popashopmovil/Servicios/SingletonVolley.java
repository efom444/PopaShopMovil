package com.efom.popashopmovil.Servicios;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public final class SingletonVolley {
    // Atributos
    private static SingletonVolley singleton;
    private RequestQueue requestQueue;
    private static Context context;

    private SingletonVolley(Context context) {
        SingletonVolley.context = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized SingletonVolley getInstance(Context context) {
        if (singleton == null) {
            singleton = new SingletonVolley(context);
        }
        return singleton;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public void addToRequestQueue(Request req) {
        getRequestQueue().add(req);
    }

}
