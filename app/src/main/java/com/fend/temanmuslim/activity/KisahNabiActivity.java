package com.fend.temanmuslim.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fend.temanmuslim.R;
import com.fend.temanmuslim.adapter.KisahNabiAdapter;
import com.fend.temanmuslim.model.KisahNabiModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class KisahNabiActivity extends AppCompatActivity implements KisahNabiAdapter.OnItemClickListener {

    private List<KisahNabiModel> list;
    private KisahNabiAdapter adapter;
    private RecyclerView recyclerView;
    private ImageButton bt_clear, btn_cari;
    private View lyt_no_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kisah_nabi);
        ImageButton iv_back= findViewById(R.id.iv_back);
        iv_back.setOnClickListener(view ->
                onBackPressed()
        );
        TextView no_item_message = findViewById(R.id.no_item_message);
        no_item_message.setText(R.string.kisah_kosong);

        list = new ArrayList<>();
        adapter = new KisahNabiAdapter(this, this, list);

        lyt_no_item = findViewById(R.id.lyt_no_item);
        lyt_no_item.setVisibility(View.GONE);
        btn_cari = findViewById(R.id.btn_cari);
        bt_clear = findViewById(R.id.btn_clear);
        recyclerView = findViewById(R.id.rvListKisah);
        btn_cari.setVisibility(View.VISIBLE);
        bt_clear.setVisibility(View.GONE);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        loadJSONFromAsset();
        EditText searchList = findViewById(R.id.searchDoa);
        searchList.addTextChangedListener(new TextWatcherCariDoa(searchList));
        searchList.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        bt_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchList.setText("");
            }
        });
    }

    private class TextWatcherCariDoa implements TextWatcher {

        private final View view;

        private TextWatcherCariDoa(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence s, int i, int before, int i2) {
            if (s.toString().length()>0){
                btn_cari.setVisibility(View.GONE);
                bt_clear.setVisibility(View.VISIBLE);
            }else {
                btn_cari.setVisibility(View.VISIBLE);
                bt_clear.setVisibility(View.GONE);
            }
        }

        public void afterTextChanged(Editable editable) {
            if (view.getId() == R.id.searchDoa) {
                adapter.filter(editable.toString().trim());
            }
            if (list.size() == 0){
                lyt_no_item.setVisibility(View.VISIBLE);
            }
        }
    }


    public void loadJSONFromAsset() {
        try {
            Resources res = getResources();
            InputStream in_s = res.openRawResource(R.raw.kisahnabi);
            byte[] b = new byte[in_s.available()];
            in_s.read(b);
            try {
                JSONArray jdata = new JSONObject(new String(b)).getJSONArray("data");
                for (int i = 0; i < jdata.length(); i++) {
                    JSONObject object = jdata.getJSONObject(i);
                    int id = object.getInt("id");
                    String name = object.getString("name");
                    String thn_kelahiran = object.getString("thn_kelahiran");
                    int usia = object.getInt("usia");
                    String description = object.getString("description");
                    String tmp = object.getString("tmp");
                    int icon=R.drawable.ic_doa;

                    if (name.contains("Adam")){
                        icon =R.drawable.adam;
                    }

                    if (name.contains("Idris")){
                        icon =R.drawable.idris;
                    }

                    if (name.contains("Nuh")){
                        icon =R.drawable.nuh;
                    }

                    if (name.contains("Hud")){
                        icon =R.drawable.hud;
                    }

                    if (name.contains("Sholeh")){
                        icon =R.drawable.sholeh;
                    }

                    if (name.contains("Ibrahim")){
                        icon =R.drawable.ibrahim;
                    }

                    if (name.contains("Ismail")){
                        icon =R.drawable.ismail;
                    }

                    if (name.contains("Luth")){
                        icon =R.drawable.lut;
                    }

                    if (name.contains("Ishaq")){
                        icon =R.drawable.ishaq;
                    }


                    if (name.contains("Yaqub")){
                        icon =R.drawable.yaqub;
                    }

                    if (name.contains("Yusuf")){
                        icon =R.drawable.yusuf;
                    }

                    if (name.contains("Syu'aib")){
                        icon =R.drawable.syuaib;
                    }

                    if (name.contains("Ayyub")){
                        icon =R.drawable.ayyub;
                    }

                    if (name.contains("Dzulkifli")){
                        icon =R.drawable.dzulkifli;
                    }

                    if (name.contains("Musa")){
                        icon =R.drawable.musa;
                    }

                    if (name.contains("Harun")){
                        icon =R.drawable.harun;
                    }

                    if (name.contains("Daud")){
                        icon =R.drawable.daud;
                    }

                    if (name.contains("Sulaiman")){
                        icon =R.drawable.sulaiman;
                    }

                    if (name.contains("Ilyas")){
                        icon =R.drawable.ilyas;
                    }

                    if (name.contains("Ilyasa'")){
                        icon =R.drawable.ilyasa;
                    }

                    if (name.contains("Yunus")){
                        icon =R.drawable.yunus;
                    }

                    if (name.contains("Zakariya")){
                        icon =R.drawable.zakariya;
                    }

                    if (name.contains("Yahya")){
                        icon =R.drawable.yahya;
                    }

                    if (name.contains("Isa")){
                        icon =R.drawable.isa;
                    }

                    if (name.contains("Muhammad")){
                        icon =R.drawable.muhammad;
                    }

                    KisahNabiModel item = new KisahNabiModel(id, name, thn_kelahiran, usia, description, tmp, icon);
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
    public void onItemClicked(int position, KisahNabiModel item) {
        Intent intent = new Intent(this, DetailKisahActivity.class);
        intent.putExtra("id", item.getId());
        intent.putExtra("name", "Kisah "+item.getName());
        intent.putExtra("thn_kelahiran", item.getThn_kelahiran());
        intent.putExtra("usia", item.getUsia());
        intent.putExtra("description", item.getDescription());
        intent.putExtra("tmp", "Kejadian: "+item.getTmp());
        intent.putExtra("icon", item.getIcon());
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}