package com.example.carteraclientes;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.provider.BaseColumns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.carteraclientes.databinding.ActMainBinding;

import java.util.ArrayList;

import BaseDatos.DatosOpenHelper;
import BaseDatos.DatosOpenHelperContract.ClientEntry;

public class ActMain extends AppCompatActivity {
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
            DatosOpenHelper dbClientes = new DatosOpenHelper(this);
            conexion = dbClientes.getReadableDatabase();

            // Define a projection that specifies which columns from the database
            // you will actually use after this query.
            String[] projection = {
                    ClientEntry.COL_NOMBRE,
                    ClientEntry.COL_TELEFONO
            };


            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM CLIENT");
            String sNombre;
            String sTelefono;

//            Cursor resultado = conexion.rawQuery(sql.toString(), null);
            Cursor resultado = conexion.query(ClientEntry.TABLE_NAME, projection, null, null, null, null, null);


            if(resultado.getCount() > 0) {
                resultado.moveToFirst(); // La BD comienza en -1, por eso debe moverse a 0
                do {
                    sNombre = resultado.getString(resultado.getColumnIndex(projection[0]));
                    sTelefono = resultado.getString(resultado.getColumnIndex(projection[1]));
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

}