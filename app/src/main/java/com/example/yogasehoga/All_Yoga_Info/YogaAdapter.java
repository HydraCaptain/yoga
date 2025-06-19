package com.example.yogasehoga.All_Yoga_Info;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class YogaAdapter extends RecyclerView.Adapter<YogaAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(String yogaName);
    }

    private List<String> yogaNames;
    private Context context;
    private OnItemClickListener listener;

    public YogaAdapter(List<String> yogaNames, Context context, OnItemClickListener listener) {
        this.yogaNames = yogaNames;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public YogaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull YogaAdapter.ViewHolder holder, int position) {
        String name = yogaNames.get(position);
        holder.textView.setText(name);
        holder.itemView.setOnClickListener(v -> listener.onItemClick(name));
    }

    @Override
    public int getItemCount() {
        return yogaNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
}
