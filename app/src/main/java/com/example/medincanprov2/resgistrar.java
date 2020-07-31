package com.example.medincanprov2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class resgistrar extends AppCompatActivity {
    Button registrar;
    EditText nombre,dni;
    User user;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resgistrar);
        nombre = findViewById(R.id.txtnombre);
        dni= findViewById(R.id.txtndi);
        registrar= findViewById(R.id.btnRegistrar2);
        Intent mIntent = getIntent();
        user = (User) mIntent.getParcelableExtra("Usurper");
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Log.i(String.valueOf(this), "el nombre de usuario esta aqui "+user.getEstado());

                    if (user.getEstado().equals("0")) {
                        enviarpacinet();
                        Log.i(String.valueOf(this), "entro a registra pacuineta");

                    }
                    else {
                        Log.i(String.valueOf(this), "entro a  registrar usuario");
                        enviaruser();

                    }
                }
                catch (Exception ex)
                {

                }



            }
        });
    }
    public void  enviaruser()
    {
        String name,Dni;

        if (nombre.getText().toString().trim().equalsIgnoreCase(""))
        {
            nombre.setError("Campo vacio");
        }
        else {
            if (dni.getText().toString().trim().equalsIgnoreCase(""))
            {
                dni.setError("Campo vacio");
            }
            else if(dni.length()>7 )
            {
                dni.setError("su numero de carne  tiene mas de 7 caracteres");
            }
            else {
                if (dni.length()==7){
                    name= nombre.getText().toString();
                    Dni= dni.getText().toString();
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    Map<String,Object> datosUsuarios= new HashMap<>();
                    datosUsuarios.put("nombre",name);
                    datosUsuarios.put("Dni",Dni);
                    datosUsuarios.put("estado","0");
                    mDatabase.child("User").push().setValue(datosUsuarios);
                    Toast.makeText(getApplicationContext(), "registrado " ,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
                else {
                    dni.setError("su carnet no tiene notiene la cantidad de caracteres   ");
                }

            }
        }


    }
    public void  enviarpacinet()
    {
        String name,Dni;

        if (nombre.getText().toString().trim().equalsIgnoreCase(""))
        {
            nombre.setError("Campo vacio");
        }
        else {
            if (dni.getText().toString().trim().equalsIgnoreCase(""))
            {
                dni.setError("Campo vacio");
            }
            else if(dni.length()>7 )
            {
                dni.setError("su numero de carne  tiene mas de 7 caracteres");
            }
            else {
                if (dni.length()==7){
                    name= nombre.getText().toString();
                    Dni= dni.getText().toString();
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    Map<String,Object> datosUsuarios= new HashMap<>();
                    datosUsuarios.put("nombre",name);
                    datosUsuarios.put("Dni",Dni);
                    datosUsuarios.put("estado","1");
                    datosUsuarios.put("idUser",user.getId());
                    mDatabase.child("User").push().setValue(datosUsuarios);
                    Toast.makeText(getApplicationContext(), "Paciente registrado" ,Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    dni.setError("su carnet no tiene notiene la cantidad de caracteres   ");
                }

            }
        }


    }
}