package com.rjt.android.demo2.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rjt.android.demo2.R;
import com.rjt.android.demo2.pojo.Result;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.GirlViewHolder> {
    List<Result> mResults;
    Context mContext;

    public MainAdapter(List<Result> results, Context context) {
        mResults = results;
        mContext = context;
    }

    @NonNull
    @Override
    public MainAdapter.GirlViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new GirlViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.GirlViewHolder holder, int position) {
        holder.girlTv.setText(mResults.get(position).getSource());
        Glide.with(mContext).load(mResults.get(position).getUrl()).into(holder.girlIv);
    }

    @Override
    public int getItemCount() {
        return mResults.size();
    }

    class GirlViewHolder extends RecyclerView.ViewHolder {
        ImageView girlIv;
        TextView girlTv;
        public GirlViewHolder(View itemView) {
            super(itemView);
            girlIv = itemView.findViewById(R.id.img);
            girlTv = itemView.findViewById(R.id.source);
           // Animation zoomAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom);
        }
    }
}
