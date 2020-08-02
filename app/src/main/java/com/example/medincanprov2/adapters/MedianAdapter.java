package com.example.medincanprov2.adapters;

import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medincanprov2.R;
import com.example.medincanprov2.models.Medicamentos;
import com.example.medincanprov2.models.Paciente;

import java.util.ArrayList;
import java.util.Random;

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         Medicamentos medicamentos=MEdicamLIst.get(position);
         Random random = new Random();
         int r= random.nextInt(255);
        int g = random.nextInt(255);
        int b= random.nextInt(255);
         int color = Color.rgb(r,g,b);
        holder.infomacionMEdicamnetos.setBackgroundColor(color);
         random = new Random();
        int rr= random.nextInt(255);
        int gg = random.nextInt(255);
        int bb= random.nextInt(255);
        int colorr = Color.rgb(rr,gg,bb);
        holder.infomacionMEdicamnetos.setTextColor(colorr);
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

            infomacionMEdicamnetos = (TextView) view.findViewById(R.id.textmedicamentos);
        }

    }


}
