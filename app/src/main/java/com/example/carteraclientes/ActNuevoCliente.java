package com.example.carteraclientes;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.widget.Toast;


import androidx.appcompat.widget.Toolbar;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.carteraclientes.databinding.ActNuevoClienteBinding;

public class ActNuevoCliente extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActNuevoClienteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        binding = ActNuevoClienteBinding.inflate(getLayoutInflater());
        // setContentView(binding.getRoot());
        setContentView(R.layout.act_nuevo_cliente);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_act_nuevo_cliente);
        // appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        // NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    // @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_act_nuevo_cliente);
//        return NavigationUI.navigateUp(navController, appBarConfiguration)
//                || super.onSupportNavigateUp();
//    }

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
                Toast.makeText(this, "Botón Ok seleccionado", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_cancelar:
                Toast.makeText(this, "Botón Cancelar seleccionado", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}