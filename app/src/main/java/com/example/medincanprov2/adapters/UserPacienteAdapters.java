package com.example.medincanprov2.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medincanprov2.ActilizarMEdicamneto;
import com.example.medincanprov2.MenuUSER;
import com.example.medincanprov2.R;
import com.example.medincanprov2.VIewMEdicametosUSER;
import com.example.medincanprov2.models.Paciente;

import java.util.ArrayList;



public class UserPacienteAdapters extends RecyclerView.Adapter<UserPacienteAdapters.ViewHolder>  {
    private  int resource;
    private ArrayList<Paciente> PacineteList;
    public  UserPacienteAdapters(ArrayList<Paciente> PacineteList, int resource)
    {
        this.PacineteList=PacineteList;
        this.resource=resource;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(resource,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
     final Paciente paciente = PacineteList.get(position);
     holder.InformacionUaserPaciete.setText(paciente.getNomnbre()+" "+paciente.getDni());
     holder. BotonVerPerfofil.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Context context = v.getContext();
             Intent intent = new Intent(context, VIewMEdicametosUSER.class);
             intent.putExtra("Pacinetuser", paciente);
             context.startActivity(intent);

         }
     });
     holder.bActualizar.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Context context = v.getContext();
             Intent intent = new Intent(context, ActilizarMEdicamneto.class);
             intent.putExtra("Pacinetuser", paciente);
             context.startActivity(intent);
         }
     });

    }

    @Override
    public int getItemCount() {
        return PacineteList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        private TextView InformacionUaserPaciete;
        private Button BotonVerPerfofil,bActualizar;
        public View view;
        public ViewHolder(final View view)
        {
            super(view);
            this.view=view;

            InformacionUaserPaciete=(TextView) view.findViewById(R.id.TxtInformacionUSer);
            BotonVerPerfofil=(Button)view.findViewById(R.id.BtnVerPerfil);
            bActualizar=(Button)view.findViewById(R.id.btnUpdate);


        }

    }



}
