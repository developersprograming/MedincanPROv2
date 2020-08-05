package com.example.medincanprov2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.medincanprov2.adapters.UserPacienteAdapters;
import com.example.medincanprov2.models.Paciente;
import com.example.medincanprov2.models.Usuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.medincanprov2.R.*;

public class MenuUSER extends AppCompatActivity {
    TextView nombre;
    ListView vistaPAciente;
    FloatingActionButton flotin;
     Paciente paciente;

    Usuario user;

    private UserPacienteAdapters mApater;
    private RecyclerView mRecyclerView;
    private ArrayList<Paciente> mPacinteLIst = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_menu_u_s_e_r);
        nombre= findViewById(id.txtnombre);

        user = DataSet.getInstance().getCurrentUser();

        Log.i(String.valueOf(this), user.getNomnbre());
        nombre.setText(user.getNomnbre());
        flotin=findViewById(id.btnFlotin);
        flotin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResgistroActivity.class);
                startActivity(intent);
            }
        });
        ///para recuperar datos de fire bases
        mRecyclerView=findViewById(id.recyclervViewUusarisos);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = database.child("User");
        Query UserQuery = ref.orderByChild("idUser").equalTo(user.getId());
        UserQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    mPacinteLIst.clear();
                    for (DataSnapshot dtaUser : dataSnapshot.getChildren()) {
                       String id = dtaUser.getKey();
                        String dni = dtaUser.child("Dni").getValue(String.class);
                        String nombrepersona = dtaUser.child("nombre").getValue(String.class);
                        String estado = dtaUser.child("estado").getValue(String.class);
                        String iduser = dtaUser.child("idUser").getValue(String.class);
                        mPacinteLIst.add(new Paciente(nombrepersona, estado, id, dni, iduser));
                    }
                    mApater = new UserPacienteAdapters(mPacinteLIst, layout.usurios_view);
                    mRecyclerView.setAdapter(mApater);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



}






