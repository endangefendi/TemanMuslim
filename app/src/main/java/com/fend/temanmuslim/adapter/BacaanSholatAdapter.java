package com.fend.temanmuslim.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fend.temanmuslim.R;
import com.fend.temanmuslim.model.BacaanSholatModel;
import com.github.florent37.expansionpanel.ExpansionLayout;

import java.util.ArrayList;
import java.util.List;

public class BacaanSholatAdapter extends RecyclerView.Adapter<BacaanSholatAdapter.ViewHolder> {
    private static final String TAG = "BacaanSholatAdapter";
    private final Context context;
    private List<BacaanSholatModel> list;
    private final ArrayList<BacaanSholatModel> listFilter;

    final OnItemClickListener listener;

    public BacaanSholatAdapter(Context context, OnItemClickListener listener, List<BacaanSholatModel> list) {
        this.context = context;
        this.listener = listener;
        this.list = list;

        listFilter = new ArrayList<>();
        listFilter.addAll(list);
    }

    public void addList(List<BacaanSholatModel> list) {
        this.list = list;
        this.listFilter.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BacaanSholatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        BacaanSholatAdapter.ViewHolder vh = new BacaanSholatAdapter.ViewHolder(view);
        return vh;
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
            for (BacaanSholatModel lok : listFilter) {
                if (lok.getName().toLowerCase().contains(charText)) {
                    list.add(lok);
                }
            }
        }

        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull BacaanSholatAdapter.ViewHolder holder, final int position) {
        final BacaanSholatModel item = list.get(position);

        if (item.getId().length()>2){
            holder.tvId.setTextSize(9);
        }
        holder.tvId.setText(item.getId());

        holder.tvTitle.setText(item.getName());
        holder.tvArabic.setText(item.getArabic());
        holder.tvLatin.setText(item.getLatin());
        holder.tvTerjemahan.setText(item.getTerjemahan());

    }

    @Override
    public int getItemCount() {
        if (list != null) return list.size();
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvTitle;
        TextView tvTerjemahan,tvArabic, tvLatin;

//        WebView tvTerjemahan,tvArabic, tvLatin;
        ExpansionLayout expansionLayout;
        public ViewHolder(@NonNull View v) {
            super(v);
            tvId = v.findViewById(R.id.tvId);
            tvTitle = v.findViewById(R.id.tvTitle);
            tvArabic = v.findViewById(R.id.tvArabic);
            tvLatin = v.findViewById(R.id.tvLatin);
            tvTerjemahan = v.findViewById(R.id.tvTerjemahan);
            expansionLayout = v.findViewById(R.id.expansionLayout);
        }
    }

    public interface OnItemClickListener {
        void onItemClicked(int position, BacaanSholatModel item);
    }


}
