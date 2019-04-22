package com.efom.popashopmovil.ActividadesSinGestion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.efom.popashopmovil.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class DescripcionProductoActivity extends Activity implements Response.Listener<JSONObject> {

    // public static final String URL_FOTOS = "http://192.168.43.123:8080/MiTiendaOnline/appCliente/assets/productos/";
    public static final String URL_FOTOS = "http://192.168.43.127/MiTiendaOnline/appCliente/assets/productos/";

    Button comprar1, btnMas, btnMenos;
    TextView count, tv_nombre, tvPrecio;
    private ImageView ivFoto;
    int contador = 1;
    GridView lista;
    private Gson gson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion_producto);
        comprar1 = (Button) findViewById(R.id.comprar1);
        lista = (GridView) findViewById(R.id.am_gv_gridviewdeseos);
        ivFoto = (ImageView) findViewById(R.id.fot);
        tvPrecio = (TextView) findViewById(R.id.tv_preci);
        btnMas = (Button) findViewById(R.id.btnMas);
        count = (TextView) findViewById(R.id.count);
        btnMenos = (Button) findViewById(R.id.btnMenos);
        tv_nombre = (TextView) findViewById(R.id.tv_nombr);
        //Recibe info del producto
        final String idProducto = getIntent().getStringExtra("idProducto");
        final String nombre = getIntent().getStringExtra("Nombre");
        final String precio = getIntent().getStringExtra("Precio");
        final String foto = getIntent().getStringExtra("Foto");
        final String usuario = getIntent().getStringExtra("usuario");

        //asigno info a los campos text
        tv_nombre.setText(nombre);
        tvPrecio.setText(precio);
        Picasso.with(DescripcionProductoActivity.this).load(URL_FOTOS + foto).into(ivFoto);
        contador();
        //envio info de producto al carrito
        comprar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), nombre + " " + "añadido al carrito " + "" + " ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                TextView nombrePCarrito = tv_nombre;
                int precio1 = Integer.parseInt(tvPrecio.getText().toString());
                int cantidad1 = Integer.parseInt(count.getText().toString());
                //hacer el calculo precio por cantidad
                final int PrecioTotal;
                PrecioTotal = precio1 * cantidad1;
                intent.putExtra("usuario", usuario);
                intent.putExtra("idProducto", Integer.parseInt(idProducto));
                intent.putExtra("idProductoComp", idProducto);
                intent.putExtra("foto", foto);
                intent.putExtra("nombre", nombrePCarrito.getText().toString());
                intent.putExtra("precio", precio1);
                intent.putExtra("precioTotal", String.valueOf(PrecioTotal));
                intent.putExtra("cantidad", cantidad1);
                intent.putExtra("respuesta11", "2");
                //startActivity(intent);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }


    private void update_contMas() {
        if (contador >= 15) {
        } else {
            contador++;
            String contador = String.valueOf(this.contador);
            count.setText(contador);
        }
    }

    private void update_contMenos() {
        if (contador <= 1) {
        } else {
            contador--;
            String contador = String.valueOf(this.contador);
            count.setText(contador);
        }
    }

    private void contador() {
        btnMas = (Button) findViewById(R.id.btnMas);
        count = (TextView) findViewById(R.id.count);
        btnMenos = (Button) findViewById(R.id.btnMenos);

        btnMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update_contMas();
            }
        });

        btnMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                update_contMenos();
                ;
            }
        });
    }

    @Override
    public void onResponse(JSONObject response) {
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}