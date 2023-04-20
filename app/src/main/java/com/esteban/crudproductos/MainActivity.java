package com.esteban.crudproductos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button login;
    EditText username,password;

    Intent miIntent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // REFERENCIAR BOTONES Y CAMPOS
        login =findViewById(R.id.button);
        username = findViewById(R.id.txtUsername);
        password = findViewById(R.id.txtPaswword);

    }

    // lOGICA
    public void login(View view){
        if (username.getText().toString().trim().equals("admin") && password.getText().toString().trim().equals("admin")) {
            Intent intencion = new Intent(getApplication(),MenuPrincipal.class);
            startActivity(intencion);
            Toast.makeText(getApplicationContext(),"Bienvenido",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(),"Credenciales incorrectas",Toast.LENGTH_SHORT).show();
        }
    }
}