package com.esteban.crudproductos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.esteban.crudproductos.Configuracion.DBHelper;

import java.text.NumberFormat;


public class CrearProducto extends AppCompatActivity {

    EditText txtCodigo,txtNombre,txtPrecio;
    Button guardar;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_producto);

        // CONEXION A LA BASE DE DATOS
        dbHelper = new DBHelper(CrearProducto.this,"ProductosDB.db",null,1);

        txtCodigo = findViewById(R.id.txtCodigo);
        txtNombre = findViewById(R.id.txtNombre);
        txtPrecio = findViewById(R.id.txtPrecio);

        guardar = findViewById(R.id.btnCrear);

    }

    public void crearProducto(View view){
        String codigo = txtCodigo.getText().toString();
        String nombre = txtNombre.getText().toString();
        String precioString = txtPrecio.getText().toString();
        NumberFormat format = NumberFormat.getInstance();
        Number precio = null;

        if (codigo.equals("") || nombre.equals("") || precioString.equals("")) {
            Toast.makeText(getApplicationContext(),"Campos Vacios", Toast.LENGTH_SHORT).show();
        } else {
            try {
                precio = format.parse(precioString);
                try
                {
                   dbHelper.addProduct(codigo,nombre,precio);
                   txtCodigo.setText("");
                   txtNombre.setText("");
                   txtPrecio.setText("");
                   Toast.makeText(getApplicationContext(),"Producto Registrado", Toast.LENGTH_SHORT).show();
                   Intent intencion = new Intent(getApplication(),MenuPrincipal.class);
                   startActivity(intencion);
                }
                catch (Exception ex)
                {
                    Toast.makeText(getApplicationContext(),ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(),e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}