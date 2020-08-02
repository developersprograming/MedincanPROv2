 package com.example.medincanprov2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medincanprov2.models.Paciente;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

 public class ActilizarMEdicamneto extends AppCompatActivity {
     EditText nombreUSer,dni;
     Button update;
     Paciente paciente;
     private DatabaseReference mDatabase;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actilizar_m_edicamneto);
        nombreUSer= findViewById(R.id.txtnombreuser);
        dni=findViewById(R.id.txtndiUSer);
        update= findViewById(R.id.btnActualizarUausrio);
        Intent mIntent = getIntent();
        paciente = (Paciente) mIntent.getParcelableExtra("Pacinetuser");
        nombreUSer.setText(paciente.getNomnbre());
        dni.setText(paciente.getDni());
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nombreUSer.getText().toString().trim().equalsIgnoreCase(""))
                {
                    nombreUSer.setError("Este campo no tiene que estar vacio");
                }
               else if (dni.getText().toString().trim().equalsIgnoreCase(""))
                {
                    dni.setError("Este campo no tiene que estar vacio");
                }
                else if(dni.length()>7 )
                {
                    dni.setError("su numero de carne  tiene mas de 7 caracteres");
                }
                else {
                    if (dni.length()==7){
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    Map<String,Object> datosUsuarios= new HashMap<>();
                    datosUsuarios.put("nombre",nombreUSer.getText().toString());
                    datosUsuarios.put("Dni",dni.getText().toString());
                    mDatabase.child("User").child(paciente.getId()).updateChildren(datosUsuarios);
                    Toast.makeText(getApplicationContext(), "actualizado corectamente " ,Toast.LENGTH_SHORT).show();
                    finish();}
                    else {
                        dni.setError("su carnet no tiene  la cantidad de caracteres   ");
                    }
                }


            }
        });

    }
}