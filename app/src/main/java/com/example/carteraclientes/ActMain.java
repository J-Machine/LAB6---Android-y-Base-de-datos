package com.example.carteraclientes;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import androidx.navigation.ui.AppBarConfiguration;

import com.example.carteraclientes.databinding.ActMainBinding;

import java.util.ArrayList;

import BaseDatos.DatosOpenHelper;

public class ActMain extends AppCompatActivity {

//    private AppBarConfiguration appBarConfiguration;
    private ActMainBinding binding;

    private ListView lsDatos;
    private ArrayAdapter<String> adaptador;
    private ArrayList<String> clientes;

    private SQLiteDatabase conexion;
    private DatosOpenHelper datosOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* 13 - No se ha cambiado nada */
        binding = ActMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_act_main);
//        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(ActMain.this,ActNuevoCliente.class);
//                startActivity(it);
                startActivityForResult(it, 0);
            }
        });

        actualizar();
    }

    private void actualizar() {
        lsDatos = findViewById(R.id.lsDatos);
        clientes = new ArrayList<String>() ;

        try {
            datosOpenHelper = new DatosOpenHelper(this);
            conexion = datosOpenHelper.getWritableDatabase();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM CLIENT");
            String sNombre;
            String sTelefono;

            Cursor resultado = conexion.rawQuery(sql.toString(), null);

            if(resultado.getCount() > 0) {
                resultado.moveToFirst();
                do {
                    sNombre = resultado.getString(resultado.getColumnIndex("NOMBRE"));
                    sTelefono = resultado.getString(resultado.getColumnIndex("TELEFONO"));
                    clientes.add(sNombre + ": " + sTelefono);
                }
                while (resultado.moveToNext());
            }
            adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, clientes);
            lsDatos.setAdapter(adaptador);
        }
        catch (Exception ex) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Aviso");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton("Ok", null);
            dlg.show();
        }
    }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        actualizar();
    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_act_main);
//        return NavigationUI.navigateUp(navController, appBarConfiguration)
//                || super.onSupportNavigateUp();
//    }

}