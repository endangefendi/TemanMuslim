package com.fend.temanmuslim.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.fend.temanmuslim.R;

import static com.fend.temanmuslim.utils.Constans.close_html;
import static com.fend.temanmuslim.utils.Constans.open_html;

public class DetailKisahActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kisah);
        ImageButton iv_back= findViewById(R.id.iv_back);
        iv_back.setOnClickListener(view ->
                onBackPressed()
        );

        String name = getIntent().getStringExtra("name");
        String des = getIntent().getStringExtra("description");
        int icon = getIntent().getIntExtra("icon", 0);
//        intent.putExtra("id", item.getId());
//        intent.putExtra("name", item.getName());
//        intent.putExtra("thn_kelahiran", item.getThn_kelahiran());
//        intent.putExtra("usia", item.getUsia());
//        intent.putExtra("description", item.getDescription());
//        intent.putExtra("tmp", item.getTmp());
//        intent.putExtra("icon", item.getIcon());
//        startActivity(intent);

        TextView title = findViewById(R.id.title);
        title.setText(name);

        ImageView ImageIcon = findViewById(R.id.icon);
        ImageIcon.setImageResource(icon);

        WebView description = findViewById(R.id.description);
        description.loadDataWithBaseURL(null, open_html + des + close_html, "text/html", "UTF-8", "about:blank");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}