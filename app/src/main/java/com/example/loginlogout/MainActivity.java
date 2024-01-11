package com.example.loginlogout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String PREF_NAME = "datos";
    private SharedPreferences settings;
    private String keyUsuario, keyPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settings = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        
        keyUsuario = getString(R.string.keyUsuario);
        keyPassword = getString(R.string.keyPassword);

        // Credenciales ya guardadas
        String usuario = settings.getString(keyUsuario, null);
        String password = settings.getString(keyPassword, null);
        if (usuario != null && password != null) {
            Intent i = new Intent(this, App.class);
            startActivity(i);
        }

        // Sin credenciales guardadas
        EditText txtUsuario = findViewById(R.id.txtUsuario);
        EditText txtContrasenya = findViewById(R.id.txtContrasenya);
        Button btnEntrar = findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(v -> {
            String u = txtUsuario.getText().toString().trim();
            String p = txtContrasenya.getText().toString().trim();

            if (u.isEmpty() || p.isEmpty()) {
                Toast.makeText(this, "El usuario y la contraseña no pueden estar vacíos.", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences.Editor e = settings.edit();
                e.putString(keyUsuario, u);
                e.putString(keyPassword, p);
                e.apply();

                Intent i = new Intent(this, App.class);
                startActivity(i);
            }
        });

    }
}