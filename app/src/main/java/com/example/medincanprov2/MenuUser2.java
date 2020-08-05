package com.example.medincanprov2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medincanprov2.adapters.MedianAdapter;
import com.example.medincanprov2.models.Medicamento;
import com.example.medincanprov2.models.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MenuUser2 extends AppCompatActivity {
    Usuario user;
    TextView nombre;
    TextView nombrep;
    Button agregarM;
    private ArrayList<Medicamento> mMedicametos = new ArrayList<>();
    private MedianAdapter mApater;
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_user2);
        nombre= findViewById(R.id.txtnombre);

        user = DataSet.getInstance().getCurrentUser();

        Log.i(String.valueOf(this), user.getId());
        nombre.setText(user.getNomnbre());
     ///  bd figuras
        mRecyclerView=findViewById(R.id.resiclevieMEdicametosRECILER);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                try{
                    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                    DatabaseReference ref = database.child("Medicamentos");
                    Query UserQuery = ref.orderByChild("idUserP").equalTo(user.getId());
                    UserQuery.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                mMedicametos.clear();
                                for (DataSnapshot dbMedicamnetos : dataSnapshot.getChildren()) {
                                    String id=dbMedicamnetos.getKey();
                                    String nombreMEndicament=dbMedicamnetos.child("Nombre_MEdicamento").getValue(String.class);
                                    String cantidad=dbMedicamnetos.child("Cantidad").getValue(String.class);
                                    String hora=dbMedicamnetos.child("Hora").getValue(String.class);
                                    String minuto=dbMedicamnetos.child("Minutos").getValue(String.class);
                                    String idUserP=dbMedicamnetos.child("idUserP").getValue(String.class);
                                    mMedicametos.add(new Medicamento(nombreMEndicament,cantidad,hora,minuto,id,idUserP));
                                }
                                mApater = new MedianAdapter(mMedicametos, R.layout.medicametos_view);
                                mRecyclerView.setAdapter(mApater);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }catch (Exception ex)
                {
                    Toast.makeText(getApplicationContext(), "errro" ,Toast.LENGTH_SHORT).show();
                }
        }
}