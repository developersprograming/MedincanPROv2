package com.example.medincanprov2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    Button inicio,registro;
    EditText carnet;
    User user;
    boolean exite=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carnet= findViewById(R.id.txtid);
        registro= findViewById(R.id.btnRegister);
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = new User();
                Intent intent = new Intent(getApplicationContext(), resgistrar.class);
                intent.putExtra("Usurper", user);
                startActivity(intent);

            }
        });
        inicio = findViewById(R.id.btnIniciar);
        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (carnet.getText().toString().trim().equalsIgnoreCase("") )
                {
                    carnet.setError("Campo vacio");
                }
                if ( carnet.length()>7) {
                    carnet.setError("su carnt no tiene que se mayor o menor de 7 dijitos ");
                }
                if (carnet.length()==7)
                 {
                    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                    DatabaseReference ref = database.child("User");
                    Query UserQuery = ref.orderByChild("Dni").equalTo(carnet.getText().toString());
                    UserQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if (dataSnapshot.exists()) {
                                String nombrepersona = null,dni = null,estado = null,id = null;
                                for (DataSnapshot dtaUser : dataSnapshot.getChildren()) {
                                    id=dtaUser.getKey();
                                    dni=dtaUser.child("Dni").getValue(String.class);
                                    nombrepersona=dtaUser.child("nombre").getValue(String.class);
                                    estado=dtaUser.child("estado").getValue(String.class);
                                    user = new User();
                                    user.setNomnbre(nombrepersona);
                                    user.setDni(dni);
                                    user.setEstado(estado);
                                    user.setId(id);
                                    exite=true;
                                }
                                if (exite==true){
                                    if (user.getEstado().equals("0"))
                                    {Intent intent = new Intent(getApplicationContext(), MenuUSER.class);
                                        intent.putExtra("Usurper", user);
                                        startActivity(intent);}
                                    else {
                                        Intent intent = new Intent(getApplicationContext(), MenuUser2.class);
                                        intent.putExtra("Usurper", user);
                                        startActivity(intent);
                                    }

                                }
                                else {
                                    carnet.setError("el usuario no exite");
                                }





                            }
                            else {
                                carnet.setError("el usuario no exite");
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }


                    });
                }
                else {
                    carnet.setError("su numero de carnet no tiene que ser meyor o menor de 7 digitos ");
                }
            }
        });
    }
    public void   pacientsesecion()
    {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = database.child("User");
        Query UserQuery = ref.orderByChild("Dni").equalTo(carnet.getText().toString());
        UserQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    String nombrepersona = null,dni = null,estado = null,id = null;
                    for (DataSnapshot dtaUser : dataSnapshot.getChildren()) {
                        id= dtaUser.getKey();
                        nombrepersona=dtaUser.child("nombre").getValue(String.class);
                        dni=dtaUser.child("Dni").getValue(String.class);
                        estado=dtaUser.child("estado").getValue(String.class);
                    }
                    User user = new User(nombrepersona,dni,estado,id);

                    Toast.makeText(getApplicationContext(), "ENTRO " +nombrepersona ,Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "NO esta registrado  " ,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}