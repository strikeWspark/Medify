package com.dryfire.medify.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.FrameLayout;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dryfire.medify.Adapter.TrendingSocialRecyclerViewAdapter;
import com.dryfire.medify.Adapter.WhatsNewRecyclerViewAdapter;
import com.dryfire.medify.Model.Trending;
import com.dryfire.medify.R;
import com.dryfire.medify.Model.WhatsNew;

import java.util.ArrayList;
import java.util.List;

public class WhatsNewFragment extends Fragment {
    View v;
    private RecyclerView recyclerView;
    private RecyclerView trending_RecyclerView;
    private List<WhatsNew> whatsNewList;
    private List<Trending> trendingList;
    private FrameLayout cardView;
    public WhatsNewFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.whats_new_layout,container,false);
        recyclerView = v.findViewById(R.id.whatsnew_recyclerview);
        trending_RecyclerView = v.findViewById(R.id.trending_on_social_media);
        cardView = v.findViewById(R.id.trending_layout);
        TrendingSocialRecyclerViewAdapter trending_adapter = new TrendingSocialRecyclerViewAdapter(getContext(),trendingList);
        WhatsNewRecyclerViewAdapter adapter = new WhatsNewRecyclerViewAdapter(getContext(),whatsNewList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        trending_RecyclerView.setLayoutManager(layoutManager);
        trending_RecyclerView.setAdapter(trending_adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(dx > 0){
                    // cardView.shrink();
                   // LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0);

                   //RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) trending_RecyclerView.getLayoutParams();
                }else{
                  //  RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,200);
                  //  cardView.getLayoutParams();
                  //  trending_RecyclerView.setLayoutParams(params);


                }
            }
        });
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        trendingList = new ArrayList<>();
        whatsNewList = new ArrayList<>();

        whatsNewList.add(new WhatsNew("Data Item 1","Fionna","fionna@example.com"));
        whatsNewList.add(new WhatsNew("Data Item 2","John","john@example.com"));
        whatsNewList.add(new WhatsNew("Data Item 3","Cris","cris@example.com"));
        whatsNewList.add(new WhatsNew("Data Item 4","Becky","becky@example.com"));

        trendingList.add(new Trending("World Just blew up"));
        trendingList.add(new Trending("Joe win's the election"));
        trendingList.add(new Trending("You know what it's trending"));
        trendingList.add(new Trending("Tell me something I don't know"));
        trendingList.add(new Trending("Why you're still alive"));



    }
}
