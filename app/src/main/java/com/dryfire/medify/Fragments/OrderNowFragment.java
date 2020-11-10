package com.dryfire.medify.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dryfire.medify.Adapter.OrderNowRecyclerViewAdapter;
import com.dryfire.medify.Model.OrderNow;
import com.dryfire.medify.R;

import java.util.ArrayList;
import java.util.List;

public class OrderNowFragment extends Fragment {
    View v;
    RecyclerView recyclerView;
    OrderNowRecyclerViewAdapter adapter;
    List<OrderNow> orderNowList;


    public OrderNowFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.order_now_fragment,container,false);

        recyclerView = v.findViewById(R.id.ordernow_recyclerview);
        adapter = new OrderNowRecyclerViewAdapter(getContext(),orderNowList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return v;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        orderNowList = new ArrayList<>();
        orderNowList.add(new OrderNow("Protien House","Keto Salad",150.0f));
        orderNowList.add(new OrderNow("Ovan Express","Rice bowl",50.0f));
        orderNowList.add(new OrderNow("Kitchen Ette","Aaloo Paratha",40.0f));
        orderNowList.add(new OrderNow("Protien House","Keto Salad",150.0f));
        orderNowList.add(new OrderNow("Ovan Express","Rice bowl",50.0f));
        orderNowList.add(new OrderNow("Kitchen Ette","Aaloo Paratha",40.0f));
        orderNowList.add(new OrderNow("Protien House","Keto Salad",150.0f));
        orderNowList.add(new OrderNow("Ovan Express","Rice bowl",50.0f));
        orderNowList.add(new OrderNow("Kitchen Ette","Aaloo Paratha",40.0f));
        orderNowList.add(new OrderNow("Protien House","Keto Salad",150.0f));
        orderNowList.add(new OrderNow("Ovan Express","Rice bowl",50.0f));
        orderNowList.add(new OrderNow("Kitchen Ette","Aaloo Paratha",40.0f));
        orderNowList.add(new OrderNow("Protien House","Keto Salad",150.0f));
        orderNowList.add(new OrderNow("Ovan Express","Rice bowl",50.0f));
        orderNowList.add(new OrderNow("Kitchen Ette","Aaloo Paratha",40.0f));
        orderNowList.add(new OrderNow("Protien House","Keto Salad",150.0f));
        orderNowList.add(new OrderNow("Ovan Express","Rice bowl",50.0f));
        orderNowList.add(new OrderNow("Kitchen Ette","Aaloo Paratha",40.0f));



    }
}
