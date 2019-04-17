package com.efom.popashopmovil.Servicios;
import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public final class MySingletonVolley {
    // Atributos
    private static MySingletonVolley singleton;
    private RequestQueue requestQueue;
    private static Context context;

    private MySingletonVolley(Context context) {
        MySingletonVolley.context = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized MySingletonVolley getInstance(Context context) {
        if (singleton == null) {
            singleton = new MySingletonVolley(context);
        }
        return singleton;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public  void addToRequestQueue(Request req) {
        getRequestQueue().add(req);
    }

}
