package com.fend.temanmuslim.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import androidx.cardview.widget.CardView;

import com.fend.temanmuslim.R;
import com.fend.temanmuslim.model.AsmaulHusnaModel;

import java.util.ArrayList;
import java.util.List;

public class AsmaulHusnaAdapter extends BaseAdapter {
    private static final String TAG = "DoaAdapter";
    private final Context context;
    private List<AsmaulHusnaModel> list;
    private final ArrayList<AsmaulHusnaModel> listFilter;

    private final OnItemClickListener listener;

    public AsmaulHusnaAdapter(Context context, OnItemClickListener listener, List<AsmaulHusnaModel> list) {
        this.context = context;
        this.listener = listener;
        this.list = list;

        listFilter = new ArrayList<>();
        listFilter.addAll(list);
    }

    public void addList(List<AsmaulHusnaModel> list) {
        this.list = list;
        this.listFilter.addAll(list);
        notifyDataSetChanged();
    }


    @SuppressLint("DefaultLocale")
    public void filter(String charText)
    {
        charText = charText.toLowerCase();
        list.clear();
        if (charText.length() == 0) {
            /* tampilkan seluruh data */
            list.addAll(listFilter);
        } else {
            for (AsmaulHusnaModel lok : listFilter) {
                if (lok.getLatin().toLowerCase().contains(charText)) {
                    list.add(lok);
                }
            }
        }

        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (list == null)return 0;
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        if (list == null)return null;
        return list.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = LayoutInflater.from(context).inflate(R.layout.item_asmaulhusna, parent, false);
        final AsmaulHusnaModel item = list.get(position);

        TextView tvArabic = view.findViewById(R.id.tvArabic);
        TextView tvId = view.findViewById(R.id.tvId);

        tvId.setText(item.getIndex());
        if (item.getIndex().length()>1){
            tvId.setTextSize(7);
        }

        if (item.getIndex().length()>2){
            tvId.setTextSize(6);
        }

        tvArabic.setText(item.getArabic());

        CardView layout = view.findViewById(R.id.parent);
        layout.setOnClickListener(view1 -> {
                    if (listener != null) listener.onItemClicked(position, item);
                }
        );

        view.setOnClickListener(view1 -> {
                    if (listener != null) listener.onItemClicked(position, item);
                }
        );

        tvArabic.setOnClickListener(view1 -> {
                    if (listener != null) listener.onItemClicked(position, item);
                }
        );

        return view;
    }

    public interface OnItemClickListener {
        void onItemClicked(int position, AsmaulHusnaModel item);
    }


}
