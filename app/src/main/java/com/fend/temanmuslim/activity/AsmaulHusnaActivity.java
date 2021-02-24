package com.fend.temanmuslim.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fend.temanmuslim.R;
import com.fend.temanmuslim.adapter.AsmaulHusnaAdapter;
import com.fend.temanmuslim.adapter.AsmaulHusnaAdapter;
import com.fend.temanmuslim.model.AsmaulHusnaModel;
import com.fend.temanmuslim.utils.CustomGridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AsmaulHusnaActivity extends AppCompatActivity implements AsmaulHusnaAdapter.OnItemClickListener{

    private List<AsmaulHusnaModel> list;
    private AsmaulHusnaAdapter adapter;
    private CustomGridView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asmaul_husna);
        ImageButton iv_back= findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        list = new ArrayList<>();
        adapter = new AsmaulHusnaAdapter(this, this, list);

        recyclerView = findViewById(R.id.rvAsmaulHusna);

        loadJSONFromAsset();
    }

    public void loadJSONFromAsset() {
        try {
            Resources res = getResources();
            InputStream in_s = res.openRawResource(R.raw.asmaulhusna);
            byte[] b = new byte[in_s.available()];
            in_s.read(b);
            try {
                JSONArray jdata = new JSONObject(new String(b)).getJSONArray("data");
                for (int i = 0; i < jdata.length(); i++) {
                    JSONObject object = jdata.getJSONObject(i);
                    String index = object.getString("index");
                    String latin = object.getString("latin");
                    String arabic = object.getString("arabic");
                    String translation_id = object.getString("translation_id");

                    AsmaulHusnaModel item = new AsmaulHusnaModel(index, latin, arabic, translation_id);
                    list.add(item);
                }
                adapter.addList(list);
                adapter.filter("");
                recyclerView.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onItemClicked(int position, AsmaulHusnaModel item) {
        AlertDialog.Builder alert;
        AlertDialog ad;

        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        @SuppressLint("InflateParams")
        View alertLayout = inflater.inflate(R.layout.dialog_asmaul_husna, null);

        alert = new AlertDialog.Builder(this);
        alert.setView(alertLayout);

        ad = alert.show();
        TextView tvId = alertLayout.findViewById(R.id.tvId);
        TextView tvArabic = alertLayout.findViewById(R.id.tvArabic);
        TextView tvLatin = alertLayout.findViewById(R.id.tvLatin);
        TextView tvTerjemahan = alertLayout.findViewById(R.id.tvTerjemahan);
        tvId.setText(item.getIndex());
        if (item.getIndex().length()>1){
            tvId.setTextSize(9);
        }
        tvArabic.setText(item.getArabic());
        tvLatin.setText(item.getLatin());
        tvTerjemahan.setText(item.getTranslation_id());

        ad.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}