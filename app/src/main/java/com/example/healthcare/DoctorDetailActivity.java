package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DoctorDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);


        TextView tv = findViewById(R.id.defaultTextDD);
        Intent it = new Intent();
        String title = it.getStringExtra("title");
        tv.setText(title);

    }
}