package com.fend.temanmuslim.activity;

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
import com.fend.temanmuslim.adapter.DoaAdapter;
import com.fend.temanmuslim.model.DoaModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DoaHarianActivity extends AppCompatActivity implements DoaAdapter.OnItemClickListener{

    private List<DoaModel> list;
    private DoaAdapter adapter;
    private RecyclerView recyclerView;
    private ImageButton bt_clear, btn_cari;
    private View lyt_no_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doa_harian);
        ImageButton iv_back= findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        list = new ArrayList<>();
        adapter = new DoaAdapter(this, this, list);

        TextView no_item_message = findViewById(R.id.no_item_message);
        no_item_message.setText(R.string.doa_kosong);
        lyt_no_item = findViewById(R.id.lyt_no_item);
        lyt_no_item.setVisibility(View.GONE);
        btn_cari = findViewById(R.id.btn_cari);
        bt_clear = findViewById(R.id.btn_clear);
        recyclerView = findViewById(R.id.rvListDoa);
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
            InputStream in_s = res.openRawResource(R.raw.datadoaharian);
            byte[] b = new byte[in_s.available()];
            in_s.read(b);
            try {
                JSONArray jdata = new JSONObject(new String(b)).getJSONArray("data");
                for (int i = 0; i < jdata.length(); i++) {
                    JSONObject object = jdata.getJSONObject(i);
                    String id = object.getString("id");
                    String title = object.getString("title");
                    String arabic = object.getString("arabic");
                    String latin = object.getString("latin");
                    String translation = object.getString("translation");

                    DoaModel item = new DoaModel(id, title, arabic, latin, translation);
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
    public void onItemClicked(int position, DoaModel item) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}