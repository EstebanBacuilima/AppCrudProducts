package com.esteban.crudproductos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MenuPrincipal extends AppCompatActivity {

    Button crear, listar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        crear = findViewById(R.id.vistaCrear);
        listar = findViewById(R.id.vistaListar);
    }

    // VISTAS
    public void viewCreate(View view){
        Intent intencion = new Intent(getApplication(),CrearProducto.class);
        startActivity(intencion);
    }

    public void viewList(View view){
        Intent intencion = new Intent(getApplication(),listaProductos.class);
        startActivity(intencion);
    }

    public void vistaConsultar(View view){
        Intent intencion = new Intent(getApplication(),ConsultarActaulizarProducto.class);
        startActivity(intencion);
    }
}