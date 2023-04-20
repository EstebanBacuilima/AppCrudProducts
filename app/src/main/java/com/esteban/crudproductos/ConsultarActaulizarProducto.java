package com.esteban.crudproductos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.esteban.crudproductos.Configuracion.DBHelper;

import java.text.NumberFormat;

public class ConsultarActaulizarProducto extends AppCompatActivity {

    EditText codigoBuscar, txtNombre, txtCodigo, txtPrecio;
    Button btnBuscar, btnEditar, btnEliminar;
    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_actaulizar_producto);

        // CONEXION A LA BASE DE DATOS
        dbHelper = new DBHelper(ConsultarActaulizarProducto.this,"ProductosDB.db",null,1);

        codigoBuscar = findViewById(R.id.buscarCodigo);
        txtCodigo = findViewById(R.id.txtCodigoA);
        txtNombre = findViewById(R.id.txtNombreA);
        txtPrecio = findViewById(R.id.txtPrecioA);
    }


    public void buscarProducto(View view){
        String codigo = codigoBuscar.getText().toString();
        if (codigo.isEmpty()) {
            Toast.makeText(getApplicationContext(),"Campos de buscqueda vacio", Toast.LENGTH_SHORT).show();
        } else {
            try {
                Cursor cursor = dbHelper.findByCodigo(codigo);
                if (cursor.moveToFirst()) {
                    txtCodigo.setText(cursor.getString(cursor.getColumnIndex("codigo")));
                    txtNombre.setText(cursor.getString(cursor.getColumnIndex("nombre")));
                    txtPrecio.setText(cursor.getString(cursor.getColumnIndex("precio")));
                } else {
                    Toast.makeText(getApplicationContext(), "No se encontró ningún producto con el código " + codigo, Toast.LENGTH_LONG).show();
                }
                cursor.close();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(),"Error " + e,Toast.LENGTH_LONG).show();
            }
        }
    }

    public void actualizarProducto(View view){
        String codigo = txtCodigo.getText().toString();
        String nombre = "";
        nombre = txtNombre.getText().toString();
        String precioString = txtPrecio.getText().toString();
        NumberFormat format = NumberFormat.getInstance();
        Number precio = null;
        if (codigo.isEmpty() || nombre.isEmpty() || precioString.isEmpty()) {
            Toast.makeText(getApplicationContext(),"Campos Vacios", Toast.LENGTH_SHORT).show();
        } else {
            try {
                precio = format.parse(precioString);
                try {
                    dbHelper.updateProducto(nombre,precio,codigo);
                    Toast.makeText(getApplicationContext(), "Registro Actualizado", Toast.LENGTH_LONG).show();
                    Intent intencion = new Intent(getApplication(),MenuPrincipal.class);
                    startActivity(intencion);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"Error " + e,Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(),"Error " + e,Toast.LENGTH_LONG).show();
            }
        }
    }

    public void eliminarProducto(View view){
        String codigo = txtCodigo.getText().toString();
        String nombre = txtNombre.getText().toString();
        String precioString = txtPrecio.getText().toString();

        if (codigo.isEmpty() || nombre.isEmpty() || precioString.isEmpty()) {
            Toast.makeText(getApplicationContext(),"Campos Vacios", Toast.LENGTH_SHORT).show();
        } else {
            dbHelper.deleteProducto(codigo);
            txtCodigo.setText("");
            txtNombre.setText("");
            txtPrecio.setText("");
            Toast.makeText(this, "Registro Eliminado", Toast.LENGTH_SHORT).show();
            Intent intencion = new Intent(getApplication(),MenuPrincipal.class);
            startActivity(intencion);
        }
    }
}