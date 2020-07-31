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
import com.example.medincanprov2.models.Paciente;

import java.util.ArrayList;

public class MedianAdapter extends RecyclerView.Adapter<MedianAdapter.ViewHolder> {
    private int resource;
    private ArrayList<Medicamentos> MEdicamLIst;

    public MedianAdapter(ArrayList<Medicamentos> MEdicamLIst,int resource)
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
holder.infomacionMEdicamnetos.setText(medicamentos.getHora()+":"+medicamentos.getMinutos()+" "+
        medicamentos.getNombreMEdicamento()+" con la cantidad "+medicamentos.getCantidad());
    }

    @Override
    public int getItemCount() {
        return MEdicamLIst.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView infomacionMEdicamnetos;

        public View view;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            infomacionMEdicamnetos = (TextView) view.findViewById(R.id.txtInformacionMEdicamento);
        }

    }

}
