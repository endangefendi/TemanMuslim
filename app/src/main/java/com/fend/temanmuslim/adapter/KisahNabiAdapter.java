package com.fend.temanmuslim.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fend.temanmuslim.R;
import com.fend.temanmuslim.model.KisahNabiModel;

import java.util.ArrayList;
import java.util.List;


public class KisahNabiAdapter extends RecyclerView.Adapter<KisahNabiAdapter.ViewHolder> {
    private static final String TAG = "KisahNabiAdapter";
    private final Context context;
    private List<KisahNabiModel> list;
    private final ArrayList<KisahNabiModel> listFilter;

    final OnItemClickListener listener;

    public KisahNabiAdapter(Context context, OnItemClickListener listener, List<KisahNabiModel> list) {
        this.context = context;
        this.listener = listener;
        this.list = list;

        listFilter = new ArrayList<>();
        listFilter.addAll(list);
    }

    public void addList(List<KisahNabiModel> list) {
        this.list = list;
        this.listFilter.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public KisahNabiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_kisah_nabi, parent, false);
        KisahNabiAdapter.ViewHolder vh = new ViewHolder(view);
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
            for (KisahNabiModel lok : listFilter) {
                if (lok.getName().toLowerCase().contains(charText)) {
                    list.add(lok);
                }
            }
        }

        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull KisahNabiAdapter.ViewHolder holder, final int position) {
        final KisahNabiModel item = list.get(position);

        String no = item.getId()+".";
        String judul ="Kisah "+item.getName();
        String tempat = "Kejadian: "+item.getTmp();
        holder.tvId.setText(no);
        holder.tvTitle.setText(judul);
        holder.tvTitle.setSelected(true);
        holder.tvTmp.setText(tempat);
        holder.icon.setImageResource(item.getIcon());

        holder.parent1.setOnClickListener(view1 -> {
                    if (listener != null) listener.onItemClicked(position, item);
                }
        );

        holder.parent.setOnClickListener(view1 -> {
                    if (listener != null) listener.onItemClicked(position, item);
                }
        );

        holder.tvTitle.setOnClickListener(view1 -> {
                    if (listener != null) listener.onItemClicked(position, item);
                }
        );
    }

    @Override
    public int getItemCount() {
        if (list != null) return list.size();
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvTmp, tvTitle;
        ImageView icon;
        CardView parent;
        LinearLayout parent1;
        public ViewHolder(@NonNull View v) {
            super(v);
            icon = v.findViewById(R.id.icon);
            tvId = v.findViewById(R.id.tvId);
            tvTitle = v.findViewById(R.id.tvTitle);
            tvTmp = v.findViewById(R.id.tmp);
            parent = v.findViewById(R.id.parent);
            parent1 = v.findViewById(R.id.parent1);

        }
    }

    public interface OnItemClickListener {
        void onItemClicked(int position, KisahNabiModel item);
    }


}
