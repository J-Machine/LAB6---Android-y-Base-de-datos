package com.example.carteraclientes;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import BaseDatos.DatosOpenHelper;
import BaseDatos.DatosOpenHelperContract.ClientEntry;

public class ActNuevoCliente extends AppCompatActivity {
    private EditText edtNombre;
    private EditText edtDireccion;
    private EditText edtEmail;
    private EditText edtTelefono;

    private String nombre;
    private String direccion;
    private String email;
    private String telefono;

    private SQLiteDatabase conexion;
    private DatosOpenHelper datosOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_nuevo_cliente);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtNombre = (EditText) findViewById(R.id.edtNombre);
        edtDireccion = (EditText) findViewById(R.id.edtDireccion);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtTelefono = (EditText) findViewById(R.id.edtTelefono);

    }


    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_act_nuevo_cliente, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_ok:
//                Toast.makeText(this, "Botón Ok seleccionado", Toast.LENGTH_SHORT).show();
                if(bCamposCorrectos()){
                    try {
                        // acceder a la base de datos, crea una instancia de la subclase de SQLiteOpenHelper
                        DatosOpenHelper db = new DatosOpenHelper(this);
                        // Gets the data repository in write mode
                        SQLiteDatabase conexion = db.getWritableDatabase();

                        // Create a new map of values, where column names are the keys
                        nombre = edtNombre.getText().toString().trim();
                        direccion = edtDireccion.getText().toString().trim();
                        email = edtEmail.getText().toString().trim();
                        telefono = edtTelefono.getText().toString().trim();

                        ContentValues values = new ContentValues();
                        values.put(ClientEntry.COL_NOMBRE, nombre);
                        values.put(ClientEntry.COL_DIRECCION, direccion);
                        values.put(ClientEntry.COL_EMAIL, email);
                        values.put(ClientEntry.COL_TELEFONO, telefono);

                        // Insert the new row, returning the primary key value of the new row
                        long newRowId = conexion.insert(ClientEntry.TABLE_NAME, null, values);
                        conexion.close();
                        finish();
                    } catch (Exception ex) {
                        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                        dlg.setTitle("Aviso");
                        dlg.setMessage(ex.getMessage());
                        dlg.setNeutralButton("OK", null);
                        dlg.show();
                    }
                }
                else {
                    AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                    dlg.setTitle("Aviso");
                    dlg.setMessage("Existen campos vacios");
                    dlg.setNeutralButton("OK", null);
                    dlg.show();
                }
                break;
            case R.id.action_cancelar:
//                Toast.makeText(this, "Botón Cancelar seleccionado", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean bCamposCorrectos() {
        boolean res = true;
        if(edtNombre.getText().toString().trim().isEmpty()) {
            edtNombre.requestFocus();
            res = false;
        }
        if(edtDireccion.getText().toString().trim().isEmpty()) {
            edtDireccion.requestFocus();
            res = false;
        }
        if(edtEmail.getText().toString().trim().isEmpty()) {
            edtEmail.requestFocus();
            res = false;
        }
        if(edtTelefono.getText().toString().trim().isEmpty()) {
            edtTelefono.requestFocus();
            res = false;
        }
        return  res;
    }

}