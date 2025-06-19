package com.example.yogasehoga;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yogasehoga.All_Yoga_Info.YogaAdapter;

import java.util.List;

public class YogaPoseAdapter extends RecyclerView.Adapter<YogaPoseAdapter.YogaViewHolder> {

    public interface OnYogaPoseClickListener {
        void onYogaPoseClick(String yogaName);
    }
    private Context context;
    private List<YogaPose> yogaPoseList;
    private OnYogaPoseClickListener listener;

    public YogaPoseAdapter(Context context, List<YogaPose> yogaPoseList, OnYogaPoseClickListener listener) {
        this.context = context;
        this.yogaPoseList = yogaPoseList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public YogaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_yoga_pose, parent, false);
        return new YogaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull YogaViewHolder holder, int position) {
        YogaPose pose = yogaPoseList.get(position);
        holder.title.setText(pose.getYogaName());
        Glide.with(context).load(pose.getImageUrl()).into(holder.imageView);
        holder.itemView.setOnClickListener(v -> {
            listener.onYogaPoseClick(pose.getYogaName());  // Send yoga name back
        });
    }

    @Override
    public int getItemCount() {
        return yogaPoseList.size();
    }

    public static class YogaViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;

        public YogaViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.poseImage);
            title = itemView.findViewById(R.id.poseName);
        }
    }
}