package com.dryfire.medify.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dryfire.medify.Model.Trending;
import com.dryfire.medify.R;

import java.util.List;

public class TrendingSocialRecyclerViewAdapter extends RecyclerView.Adapter<TrendingSocialRecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private List<Trending> trendingList;

    public TrendingSocialRecyclerViewAdapter(Context context, List<Trending> trendingList) {
        this.context = context;
        this.trendingList = trendingList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.whats_new_trending,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.trending_info.setText(trendingList.get(position).getInfo_text());
    }

    @Override
    public int getItemCount() {
        return trendingList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView trending_info;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            trending_info = (TextView) itemView.findViewById(R.id.trending_info);
        }
    }
}
