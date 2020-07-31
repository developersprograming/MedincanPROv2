package com.example.medincanprov2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medincanprov2.R;
import com.example.medincanprov2.models.Medicamentos;

import java.util.ArrayList;

public class MedicmetosUserAdapter extends RecyclerView.Adapter<MedicmetosUserAdapter.ViewHolder> {
    private int resource;
    private ArrayList<Medicamentos> MEdicamLIst;
    public MedicmetosUserAdapter(ArrayList<Medicamentos> MEdicamLIst,int resource)
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
        Medicamentos medicamentos=MEdicamLIst.get(position);
        holder.infomacionMEdicamnetos.setText(medicamentos.getNombreMEdicamento());
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
