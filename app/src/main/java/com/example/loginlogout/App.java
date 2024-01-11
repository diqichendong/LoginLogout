package com.example.loginlogout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class App extends AppCompatActivity {

    private final String PREF_NAME = "datos";
    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        setSupportActionBar(findViewById(R.id.toolbar));

        settings = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        String usuario = settings.getString(getString(R.string.keyUsuario), null);

        TextView lblMensaje = findViewById(R.id.lblMensaje);
        lblMensaje.setText("Hola " + usuario + "\n¡Bienvenido!");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        boolean res = super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.menu_logout) {
            settings.edit().clear().apply();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            res = true;
        }

        return res;
    }
}