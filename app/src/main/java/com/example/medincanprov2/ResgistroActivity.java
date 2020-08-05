package com.example.medincanprov2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medincanprov2.models.Usuario;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import static com.example.medincanprov2.utils.Constants.ADMIN_CI;
import static com.example.medincanprov2.utils.Constants.CI_MAX;

public class ResgistroActivity extends AppCompatActivity {
    private Button registrar;
    private EditText nombre;
    private EditText dni;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resgistro);

        nombre = findViewById(R.id.txtnombre);
        dni = findViewById(R.id.txtndi);
        registrar= findViewById(R.id.btnRegistrar2);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
            }
        });
    }

    private void registrarUsuario() {
        nombre.setError(null);
        dni.setError(null);
        if (TextUtils.isEmpty(nombre.getText())) {
            nombre.setError("Ingrese Nombre");
            return;
        }
        if (TextUtils.isEmpty(dni.getText()) || dni.getText().length() > CI_MAX) {
            dni.setError("Ingrese CI valido, hasta 7 caracteres.");
            return;
        }
        String name = nombre.getText().toString();
        String ci = dni.getText().toString();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        Map<String,Object> datosUsuarios= new HashMap<>();
        datosUsuarios.put("nombre", name);
        datosUsuarios.put("Dni", ci);
        datosUsuarios.put("estado", isAdmin(ci));

        mDatabase.child("User").push().setValue(datosUsuarios)
            .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(getApplicationContext(), "registrado " ,Toast.LENGTH_SHORT).show();
                    ResgistroActivity.this.finish();
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), "Error " ,Toast.LENGTH_LONG).show();
                }
            });
    }

    private String isAdmin(String ci) {
        return ci.equalsIgnoreCase(ADMIN_CI) ? "0" : "1";
    }

}