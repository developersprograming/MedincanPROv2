package com.example.medincanprov2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.medincanprov2.adapters.MedicmetosUserAdapter;
import com.example.medincanprov2.adapters.UserPacienteAdapters;
import com.example.medincanprov2.models.Medicamentos;
import com.example.medincanprov2.models.Paciente;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VIewMEdicametosUSER extends AppCompatActivity {
    private MedicmetosUserAdapter mApater;
    private RecyclerView mRecyclerView;
    TextView nombrep;
    Button agregarM;
    private ArrayList<Medicamentos> mMedicametos = new ArrayList<>();
    Paciente paciente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_v_iew_m_edicametos_u_s_e_r);

        nombrep= findViewById(R.id.txtnombrePAcinetePErsona);
        Intent mIntent = getIntent();
        paciente = (Paciente) mIntent.getParcelableExtra("Pacinetuser");
        Log.i(String.valueOf(this), paciente.getNomnbre());
        nombrep.setText(paciente.getNomnbre());
        mRecyclerView=findViewById(R.id.reciclevieMEdicmetosUSER);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        try {
          /*  DatabaseReference database = FirebaseDatabase.getInstance().getReference();
            DatabaseReference ref = database.child("Medicametos");
            Query UserQuery = ref.orderByChild("idUser").equalTo(user.getId());
            UserQuery.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        mMedicametos.clear();
                        for (DataSnapshot dtaUser : dataSnapshot.getChildren()) {

                            mMedicametos.add(new Medicamentos("aa","aa" ,"aa","aaa","aaa","aa"));
                        }
                        mApater = new MedicmetosUserAdapter(mMedicametos, R.layout.user_medicametos_view);
                        mRecyclerView.setAdapter(mApater);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });*/

          agregarM= findViewById(R.id.btnAddMedic);
          agregarM.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  String id=paciente.getId();
                  Intent intent = new Intent(getApplicationContext(),RegistroMEdicametos.class);
                  intent.putExtra("id",id);
                  startActivity(intent);
              }
          });
        }
        catch (Exception ex){

        }


    }
}