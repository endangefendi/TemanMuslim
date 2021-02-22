package com.fend.temanmuslim.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fend.temanmuslim.R;
import com.fend.temanmuslim.adapter.BacaanSholatAdapter;
import com.fend.temanmuslim.model.BacaanSholatModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BacaanSholatActivity extends AppCompatActivity implements BacaanSholatAdapter.OnItemClickListener{

    private List<BacaanSholatModel> list;
    private BacaanSholatAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bacaan_sholat);
        ImageButton iv_back= findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        list = new ArrayList<>();
        adapter = new BacaanSholatAdapter(this, this, list);

        recyclerView = findViewById(R.id.rvBacaanShalat);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        loadJSONFromAsset();
    }

    public void loadJSONFromAsset() {
        try {
            Resources res = getResources();
            InputStream in_s = res.openRawResource(R.raw.bacaanshalat);
            byte[] b = new byte[in_s.available()];
            in_s.read(b);
            try {
                JSONArray jdata = new JSONObject(new String(b)).getJSONArray("data");
                for (int i = 0; i < jdata.length(); i++) {
                    JSONObject object = jdata.getJSONObject(i);
                    String id = object.getString("id");
                    String name = object.getString("name");
                    String arabic = object.getString("arabic");
                    String latin = object.getString("latin");
                    String terjemahan = object.getString("terjemahan");

                    BacaanSholatModel item = new BacaanSholatModel(id, name, arabic, latin, terjemahan);
                    list.add(item);
                }
                adapter.addList(list);
                adapter.filter("");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onItemClicked(int position, BacaanSholatModel item) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}