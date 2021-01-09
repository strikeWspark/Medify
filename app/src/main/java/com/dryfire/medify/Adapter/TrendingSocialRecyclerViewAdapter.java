package com.dryfire.medify.Adapter;

import android.content.Context;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
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

        return new MyViewHolder(v,context);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.trending_info.setText(trendingList.get(position).getInfo_text());
        holder.trending_imageView.setBackgroundResource(trendingList.get(position).getTrending_image());
    }

    @Override
    public int getItemCount() {
        return trendingList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ConstraintSet layout1,layout2;
        ConstraintLayout constraintLayout;
        public TextView trending_info;
        public ImageView trending_imageView;
        public MyViewHolder(@NonNull View itemView, final Context ctx) {
            super(itemView);
            context = ctx;

            constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.constraint_trending_layout);
            trending_info = (TextView) itemView.findViewById(R.id.trending_info);
            trending_imageView = (ImageView) itemView.findViewById(R.id.trending_imageView);
            layout1 = new ConstraintSet();
            layout2 = new ConstraintSet();
            layout2.clone(context,R.layout.whats_new_trending_expanded);
            layout1.clone(constraintLayout);
            constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    expandingCard(ctx);

                }
            });
        }

        private void expandingCard(Context ctx) {
            TransitionManager.beginDelayedTransition(constraintLayout);
            layout2.applyTo(constraintLayout);
            Toast.makeText(ctx, "Clicked", Toast.LENGTH_SHORT).show();
        }
    }


}
