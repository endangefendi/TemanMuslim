package com.fend.temanmuslim.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fend.temanmuslim.R;
import com.fend.temanmuslim.model.AyatKursiModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class AyatKursiActivity extends AppCompatActivity {

    TextView txtAyat;
    TextView txtLatin;
    TextView txtTerjemahan;

    AyatKursiModel item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayat_kursi);
        txtAyat = findViewById(R.id.txtAyat);
        txtLatin = findViewById(R.id.txtLatin);
        txtTerjemahan = findViewById(R.id.txtTerjemahan);
        getAyatKursi();
        ImageButton iv_back= findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void getAyatKursi() {
        try {
            Resources res = getResources();
            InputStream in_s = res.openRawResource(R.raw.ayatkursi);
            byte[] b = new byte[in_s.available()];
            in_s.read(b);
            try {
                JSONArray jdata = new JSONObject(new String(b)).getJSONArray("data");
                for (int i = 0; i < jdata.length(); i++) {
                    JSONObject object = jdata.getJSONObject(i);
                    String tafsir = object.getString("tafsir");
                    String translation = object.getString("translation");
                    String arabic = object.getString("arabic");
                    String latin = object.getString("latin");
                    item = new AyatKursiModel(tafsir, translation, arabic, latin);
                }
                txtAyat.setText(item.getArabic());
                txtLatin.setText(item.getLatin());
                txtTerjemahan.setText(item.getTranslation());

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}