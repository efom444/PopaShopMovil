package com.efom.popashopmovil;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        db_SQLilte = new DB_SQLilte(this, null,1);
        if(sharedPreferences.getInt("id_usuario", 0) != 0 && db_SQLilte.contarUsuarios() != 0){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }else{
            startActivity(new Intent(this, ActivityAcceso.class));
            finish();
        }
        */
    }
}
