package com.fend.temanmuslim.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.fend.temanmuslim.R;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        LinearLayout linearDoaHarian = findViewById(R.id.linearDoaHarian);
        linearDoaHarian.setOnClickListener(view -> {
            startActivity(new Intent(DashboardActivity.this, DoaHarianActivity.class));
        });

        LinearLayout frame_about = findViewById(R.id.frame_about);
        frame_about.setOnClickListener(view ->  {
                popUpInfo();
        });

        LinearLayout linearAyatKursi = findViewById(R.id.linearAyatKursi);
        linearAyatKursi.setOnClickListener(view ->  {
                startActivity(new Intent(DashboardActivity.this, AyatKursiActivity.class));
        });

        LinearLayout linearBacaanShalat = findViewById(R.id.linearBacaanShalat);
        linearBacaanShalat.setOnClickListener(view ->  {
                startActivity(new Intent(DashboardActivity.this, BacaanSholatActivity.class));
        });

        LinearLayout linearNiatShalat = findViewById(R.id.linearNiatShalat);
        linearNiatShalat.setOnClickListener(view ->  {
                startActivity(new Intent(DashboardActivity.this, NiatSholatWajibActivity.class));
        });

        LinearLayout linearKisahNabi = findViewById(R.id.linearKisahNabi);
        linearKisahNabi.setOnClickListener(view ->  {
                startActivity(new Intent(DashboardActivity.this, KisahNabiActivity.class));
        });

        LinearLayout linearAsmaulHusna = findViewById(R.id.linearAsmaulHusna);
        linearAsmaulHusna.setOnClickListener(view ->  {
                startActivity(new Intent(DashboardActivity.this, AsmaulHusnaActivity.class));
        });
    }

    private AlertDialog.Builder alert;
    private AlertDialog ad;

    private void popUpInfo() {
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        @SuppressLint("InflateParams")
        View alertLayout = inflater.inflate(R.layout.dialog_about, null);

        alert = new AlertDialog.Builder(this);
        alert.setView(alertLayout);
//        alert.setCancelable(false);

        ad = alert.show();
        ImageView imageView = alertLayout.findViewById(R.id.btn_close);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ad.dismiss();

            }
        });
        ad.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}