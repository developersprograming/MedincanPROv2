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

import com.example.medincanprov2.models.Paciente;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RegistroMEdicametos extends AppCompatActivity {
    TextView horayminuto,cantidad,mensaje;
    EditText nombreMedicamento;
    Button reloj,add;
    Context mContex=this;
    String hora,minutos;
    String am_pm;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_m_edicametos);
        try {
            nombreMedicamento=findViewById(R.id.txtnombreMEcicamento);
            cantidad= findViewById(R.id.txtCantidad);
            mensaje=findViewById(R.id.txtMEnsaje);
            add= findViewById(R.id.btnAddmedicamento);
            horayminuto = findViewById(R.id.txthorayminuto);
            final String id = getIntent().getStringExtra("id");
            Calendar calendar= Calendar.getInstance();
             final  int hour=calendar.get(Calendar.HOUR_OF_DAY);
             final  int mini=calendar.get(Calendar.MINUTE);
            reloj=findViewById(R.id.btnRelog);
            reloj.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TimePickerDialog timePickerDialog = new TimePickerDialog(mContex, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            if (minute>=0 && minute< 10)
                            {
                                //am_pm = (hourOfDay < 12) ? "AM" : "PM";
                                horayminuto.setText(hourOfDay+" : 0"+minute);
                                hora=""+hourOfDay;
                                minutos="0"+minute;
                            }
                            else {
                                //am_pm = (hourOfDay < 12) ? "AM" : "PM";
                                horayminuto.setText(hourOfDay + " : " + minute);
                                hora=""+hourOfDay;
                                minutos=""+minute;
                            }

                        }
                    },hour,mini,android.text.format.DateFormat.is24HourFormat(mContex));
                    timePickerDialog.show();
                }
            });
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        //int i= Integer.parseInt(cantidad.getText().toString());
                        //nombre.getText().toString().trim().equalsIgnoreCase("")

                        if (nombreMedicamento.getText().toString().trim().equals(""))
                        {
                            nombreMedicamento.setError("tiene que pner el nombre del medicamento");
                        }
                        else if (cantidad.getText().toString().trim().equals("")
                                || cantidad.getText().toString().trim().equals("0")) {
                            cantidad.setError("no tiene que estar vacio y tiene que se mayor 0");
                        }
                        else if(horayminuto.getText().toString().trim().equals("")){
                            horayminuto.setError("no se ingreso una hora");
                        }
                        else {

                            mDatabase = FirebaseDatabase.getInstance().getReference();
                            Map<String, Object> datosUsuarios = new HashMap<>();
                            datosUsuarios.put("Nombre_MEdicamento", nombreMedicamento.getText().toString());
                            datosUsuarios.put("Cantidad", cantidad.getText().toString());
                            datosUsuarios.put("Hora", hora);
                            datosUsuarios.put("Minutos", minutos);
                            datosUsuarios.put("idUserP", id);
                            mDatabase.child("Medicamentos").push().setValue(datosUsuarios);
                            horayminuto.setText("");
                            horayminuto.setError("Puedes ingresar una nueva hora");
                            mensaje.setText("puedes agregra otra hora para el medicamento");
                            Toast.makeText(getApplicationContext(), "Medicamneto Agregado" ,Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (Exception ex)
                    {}

                }
            });

        }
        catch (Exception ex)
        {

          }


    }
}