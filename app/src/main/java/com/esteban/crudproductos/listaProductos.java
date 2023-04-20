package com.esteban.crudproductos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.esteban.crudproductos.Configuracion.DBHelper;

import java.util.ArrayList;

public class listaProductos extends AppCompatActivity {

    DBHelper dbHelper;
    ListView listViewProductos;
    ArrayList listForView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);

        // CONEXION A LA BASE DE DATOS
        dbHelper = new DBHelper(listaProductos.this,"ProductosDB.db",null,1);

        // MOSTRAR DATOS EN UN LIST VIEW
        listForView = dbHelper.listProducts();
        listViewProductos = findViewById(R.id.listViewProductos);
        ArrayAdapter adp = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listForView);
        listViewProductos.setAdapter(adp);

    }
}