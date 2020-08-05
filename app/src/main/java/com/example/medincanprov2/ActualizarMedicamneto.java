package com.example.medincanprov2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.medincanprov2.models.Medicamento;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ActualizarMedicamneto extends AppCompatActivity {
  EditText nombremedicamento,cantidadcnatidad;
  TextView horayminutiVIen;
    Context mContex=this;
  Button btnrelo2,updateremedicamneto;
    String hora,minutos;
    Medicamento medicamentos;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_eidcamneto);
        Intent mIntent = getIntent();
        medicamentos = (Medicamento) mIntent.getParcelableExtra("medicamtos");
        nombremedicamento = findViewById(R.id.txtnombreMEcicamentoO);
        cantidadcnatidad= findViewById(R.id.txtCantidadd);
        nombremedicamento.setText(medicamentos.getNombreMEdicamento());
        cantidadcnatidad.setText(medicamentos.getCantidad());
        horayminutiVIen= findViewById(R.id.txthorayminutoo);
        horayminutiVIen.setText(medicamentos.getHora()+":"+medicamentos.getMinutos());
        hora=medicamentos.getHora();
        minutos=medicamentos.getMinutos();
        Calendar calendar= Calendar.getInstance();
        final  int hour=calendar.get(Calendar.HOUR_OF_DAY);
        final  int mini=calendar.get(Calendar.MINUTE);
        btnrelo2= findViewById(R.id.btnRelogF);
        btnrelo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(mContex, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (minute>=0 && minute< 10)
                        {
                            //am_pm = (hourOfDay < 12) ? "AM" : "PM";
                            horayminutiVIen.setText(hourOfDay+" : 0"+minute);
                            hora=""+hourOfDay;
                            minutos="0"+minute;
                        }
                        else {
                            //am_pm = (hourOfDay < 12) ? "AM" : "PM";
                            horayminutiVIen.setText(hourOfDay + " : " + minute);
                            hora=""+hourOfDay;
                            minutos=""+minute;
                        }

                    }
                },hour,mini,android.text.format.DateFormat.is24HourFormat(mContex));
                timePickerDialog.show();
            }
        });

        updateremedicamneto =findViewById(R.id.btnActualizarMedicamento);
        updateremedicamneto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              try {
                  if (nombremedicamento.getText().toString().trim().equalsIgnoreCase(""))
                  {
                      nombremedicamento.setError("Este campo no tiene que estar vacio");
                  }
                  else if (cantidadcnatidad.getText().toString().trim().equalsIgnoreCase(""))
                  {
                      cantidadcnatidad.setError("Este campo no tiene que estar vacio");
                  }
                  else if(cantidadcnatidad.getText().toString().trim().equalsIgnoreCase("0"))
                  {
                      cantidadcnatidad.setError("no se  puede poner una cantidad de 0");
                  }
                  else {
                      mDatabase = FirebaseDatabase.getInstance().getReference();
                      Map<String, Object> datosMEdicmetos = new HashMap<>();
                      datosMEdicmetos.put("Nombre_MEdicamento", nombremedicamento.getText().toString());
                      datosMEdicmetos.put("Cantidad", cantidadcnatidad.getText().toString());
                      datosMEdicmetos.put("Hora", hora);
                      datosMEdicmetos.put("Minutos", minutos);
                      mDatabase.child("Medicamentos").child(medicamentos.getId()).updateChildren(datosMEdicmetos);
                      Toast.makeText(getApplicationContext(), "actualizado corectamente " ,Toast.LENGTH_SHORT).show();
                      finish();
                  }

              }
              catch (Exception ex)
              {

              }
            }
        });


    }
}