package com.dryfire.medify.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dryfire.medify.Activities.OrderNowActivity;
import com.dryfire.medify.Model.OrderNow;
import com.dryfire.medify.R;

import java.util.List;

public class OrderNowRecyclerViewAdapter extends RecyclerView.Adapter<OrderNowRecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private List<OrderNow> orderNowList;

    public OrderNowRecyclerViewAdapter(Context context, List<OrderNow> orderNowList) {
        this.context = context;
        this.orderNowList = orderNowList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.ordernow_card,parent,false);

        return new MyViewHolder(v,context);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        holder.container.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));
            holder.restaurantName.setText(orderNowList.get(position).getRestaurantName());
            holder.itemName.setText(orderNowList.get(position).getItem_name());
            holder.itemPrice.setText("\u20b9 " +orderNowList.get(position).getPrice());
            holder.food_item_image.setImageResource(orderNowList.get(position).getFoodImage());
    }

    @Override
    public int getItemCount() {
        return orderNowList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView restaurantName,itemName,itemPrice;
        RelativeLayout container;
        ImageView food_item_image;
        public MyViewHolder(@NonNull View itemView,final Context ctx) {
            super(itemView);

          context = ctx;

            container = itemView.findViewById(R.id.container);
            restaurantName = itemView.findViewById(R.id.restaurant_name);
            itemName = itemView.findViewById(R.id.restuarant_item_name);
            itemPrice = itemView.findViewById(R.id.item_price);
            food_item_image = itemView.findViewById(R.id.restaurant_food_image);

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OrderNow orderNow = orderNowList.get(getAdapterPosition());

                    Intent intent = new Intent(ctx, OrderNowActivity.class);
                    intent.putExtra("order",orderNow);
                    ctx.startActivity(intent);
                }
            });

        }
    }
}
