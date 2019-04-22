package com.efom.popashopmovil.ActividadesSinGestion;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.efom.popashopmovil.Objetos.Usuario;
import com.efom.popashopmovil.R;
import com.google.gson.Gson;

public class AjustesActivity extends Activity {
    private Usuario usuario;
    private LinearLayout cerrar_sesion;
    private Gson gson;
    private LinearLayout EditarUsuario;
    private EditText edtPassword, edtUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);
        edtUserName = (EditText) findViewById(R.id.et_Usuario);
        edtPassword = (EditText) findViewById(R.id.et_Contrasenia);
        cerrar_sesion = (LinearLayout) findViewById(R.id.cerrarSesion);

        EditarUsuario = (LinearLayout) findViewById(R.id.EditarUsuario);

        Gson gson = new Gson();

        if (getIntent() != null) {
            final String usuario1 = getIntent().getStringExtra("usuario");
            usuario = gson.fromJson(usuario1, Usuario.class);

        }


        final String login = usuario.getLogin();
        final String password = usuario.getPassword();


        EditarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AjustesActivity.this, ValidarUsuarioAEditarActivity.class);
                intent.putExtra("usuario", login);
                intent.putExtra("password", password);
                startActivity(intent);
            }
        });


        cerrar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(AjustesActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert).setTitle(" Cerrar Sesión")
                        .setMessage("Esta seguro? ").setNegativeButton(android.R.string.cancel, null)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(AjustesActivity.this, IniciarSesionActivity.class);
                                startActivity(intent);
                                finish();
                                setResult(RESULT_OK);
                            }
                        }).show();
            }
        });


    }
}
