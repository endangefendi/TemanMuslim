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
import com.fend.temanmuslim.model.DoaModel;
import com.github.florent37.expansionpanel.ExpansionLayout;

import java.util.ArrayList;
import java.util.List;

public class DoaAdapter extends RecyclerView.Adapter<DoaAdapter.ViewHolder> {
    private static final String TAG = "DoaAdapter";
    private final Context context;
    private List<DoaModel> list;
    private final ArrayList<DoaModel> listFilter;

    final OnItemClickListener listener;

    public DoaAdapter(Context context, OnItemClickListener listener,List<DoaModel> list) {
        this.context = context;
        this.listener = listener;
        this.list = list;

        listFilter = new ArrayList<>();
        listFilter.addAll(list);
    }

    public void addList(List<DoaModel> list) {
        this.list = list;
        this.listFilter.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DoaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_niat_sholat, parent, false);
        DoaAdapter.ViewHolder vh = new DoaAdapter.ViewHolder(view);
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
            for (DoaModel lok : listFilter) {
                if (lok.getTitle().toLowerCase().contains(charText)) {
                    list.add(lok);
                }
            }
        }

        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull DoaAdapter.ViewHolder holder, final int position) {
        final DoaModel item = list.get(position);

        if (item.getId().length()>2){
            holder.tvId.setTextSize(9);
        }
        holder.tvId.setText(item.getId());

        holder.tvTitle.setText(item.getTitle());
        holder.tvArabic.setText(item.getArabic());
        holder.tvLatin.setText(item.getLatin());
        holder.tvTerjemahan.setText(item.getTranslation());

    }

    @Override
    public int getItemCount() {
        if (list != null) return list.size();
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvTitle;
        TextView tvTerjemahan,tvArabic, tvLatin;

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
        void onItemClicked(int position, DoaModel item);
    }


}
