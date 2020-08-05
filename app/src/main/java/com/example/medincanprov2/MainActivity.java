package com.example.medincanprov2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medincanprov2.models.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import static com.example.medincanprov2.utils.Constants.CI_MAX;

public class MainActivity extends AppCompatActivity {
    private Button inicio;
    private Button registro;
    private EditText carnet;
    private Usuario user;
    boolean exite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carnet = findViewById(R.id.txtid);
        registro = findViewById(R.id.btnRegister);
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResgistroActivity.class);
                startActivity(intent);
            }
        });
        inicio = findViewById(R.id.btnIniciar);
        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (TextUtils.isEmpty(carnet.getText()) || carnet.getText().length() > CI_MAX) {
                    carnet.setError("Ingrese CI valido, hasta 7 caracteres.");
                    return;
                }

                DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                DatabaseReference ref = database.child("User");
                Query UserQuery = ref.orderByChild("Dni").equalTo(carnet.getText().toString());
                UserQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        carnet.setError(null);
                        if (dataSnapshot.exists()) {
                            Map<String,Object> datosUsuario = (Map<String,Object>)dataSnapshot.getValue();
                            if (datosUsuario.isEmpty()) {
                                carnet.setError("el usuario no exite");
                                return;
                            }
                            String id = (String)(datosUsuario.keySet()).toArray()[0];
                            Map<String,String> userValues = (Map<String,String>)datosUsuario.get(id);

                            user = new Usuario();
                            user.setNomnbre(userValues.get("nombre"));
                            user.setDni(userValues.get("Dni"));
                            user.setEstado(userValues.get("estado"));
                            user.setId(id);
                            DataSet.getInstance().setCurrentUser(user);
                            if (user.getEstado().equals("0")) {
                                Intent intent = new Intent(getApplicationContext(), MenuUSER.class);
                                startActivity(intent);
                            } else {
                                Intent intent = new Intent(getApplicationContext(), MenuUser2.class);
                                startActivity(intent);
                            }
                        } else {
                            carnet.setError("el usuario no exite");
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }


                });
            }
        });
    }
}