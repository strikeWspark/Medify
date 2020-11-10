package com.dryfire.medify.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.dryfire.medify.Activities.FullDetailActivity;
import com.dryfire.medify.R;
import com.dryfire.medify.Model.WhatsNew;

import java.util.List;

public class WhatsNewRecyclerViewAdapter extends RecyclerView.Adapter<WhatsNewRecyclerViewAdapter.MyViewHolder> {
    Context context;
    List<WhatsNew> whatsNewsList;

    public WhatsNewRecyclerViewAdapter(Context context, List<WhatsNew> whatsNewList) {
            this.context = context;
            this.whatsNewsList = whatsNewList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.whats_new_card,parent,false);
        return new MyViewHolder(v,context);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                holder.container.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_up_card_animation));
                holder.tv_item.setText(whatsNewsList.get(position).getItem());
                holder.name.setText(whatsNewsList.get(position).getName());
                holder.username.setText(whatsNewsList.get(position).getUsername());
    }

    @Override
    public int getItemCount() {
        return whatsNewsList.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_item,name,username;
        CardView container;
        public MyViewHolder(@NonNull View itemView,final Context ctx) {
            super(itemView);

            context = ctx;

            container = (CardView) itemView.findViewById(R.id.whats_newcontainer);
            tv_item = (TextView) itemView.findViewById(R.id.item_data);
            name = itemView.findViewById(R.id.name);
            username = itemView.findViewById(R.id.username);

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        ctx.startActivity(new Intent(context, FullDetailActivity.class));
                }
            });

        }
    }
}
