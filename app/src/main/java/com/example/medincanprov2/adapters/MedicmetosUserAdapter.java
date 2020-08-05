package com.example.medincanprov2.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medincanprov2.ActualizarMedicamneto;
import com.example.medincanprov2.R;
import com.example.medincanprov2.models.Medicamento;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MedicmetosUserAdapter extends RecyclerView.Adapter<MedicmetosUserAdapter.ViewHolder> {
    private DatabaseReference mDatabase;
    private int resource;
    private ArrayList<Medicamento> MEdicamLIst;
    public MedicmetosUserAdapter(ArrayList<Medicamento> MEdicamLIst, int resource)
    {
        this.MEdicamLIst=MEdicamLIst;
        this.resource=resource;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(resource,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Medicamento medicamentos=MEdicamLIst.get(position);
        holder.infomacionMEdicamnetos.setText(medicamentos.getNombreMEdicamento() + " con la cantidad "+ medicamentos.getCantidad() +"  a la hora "+medicamentos.getHora()+":"+medicamentos.getMinutos());
        holder.ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context,ActualizarMedicamneto.class);
                intent.putExtra("medicamtos", medicamentos);
                context.startActivity(intent);
            }
        });
        holder.eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase = FirebaseDatabase.getInstance().getReference("Medicamentos");
                mDatabase.child(medicamentos.getId()).removeValue();
            }
        });
    }

    @Override
    public int getItemCount() {

            return MEdicamLIst.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView infomacionMEdicamnetos;
        private Button ver,eliminar;


        public View view;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            infomacionMEdicamnetos = (TextView) view.findViewById(R.id.TxtInformacionUSer);
            eliminar = (Button) view.findViewById(R.id.BtnEliminar);
            ver=(Button) view.findViewById(R.id.BtnVerPerfil);
        }

    }
}
