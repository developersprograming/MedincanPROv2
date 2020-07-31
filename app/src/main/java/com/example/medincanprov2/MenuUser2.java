package com.example.medincanprov2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MenuUser2 extends AppCompatActivity {
    User user;
    TextView nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_user2);nombre= findViewById(R.id.txtnombre);
        Intent mIntent = getIntent();
        user = (User) mIntent.getParcelableExtra("Usurper");
        Log.i(String.valueOf(this), user.getNomnbre());
        nombre.setText(user.getNomnbre());
    }
}