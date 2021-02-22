package com.fend.temanmuslim.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.fend.temanmuslim.R;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        LinearLayout linearDoaHarian = findViewById(R.id.linearDoaHarian);
        linearDoaHarian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, DoaHarianActivity.class));
            }
        });

        LinearLayout linearAyatKursi = findViewById(R.id.linearAyatKursi);
        linearAyatKursi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, AyatKursiActivity.class));
            }
        });

        LinearLayout linearBacaanShalat = findViewById(R.id.linearBacaanShalat);
        linearBacaanShalat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, BacaanSholatActivity.class));
            }
        });

        LinearLayout linearNiatShalat = findViewById(R.id.linearNiatShalat);
        linearNiatShalat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, NiatSholatWajibActivity.class));
            }
        });
    }
}