package com.dryfire.medify.Fragments;


import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.FrameLayout;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
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
    private ConstraintSet layout1,layout2;
    private ConstraintLayout constraintLayout;
    private boolean flag = false,isOpen = false;
    public WhatsNewFragment() {
    }

    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);


           // Toast.makeText(getContext(), "Scrolling down" + dx + " " + dy, Toast.LENGTH_SHORT).show();
            if(dy > 0){
                // cardView.shrink();
                // LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0);

                //RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) trending_RecyclerView.getLayoutParams();

                //Toast.makeText(getContext(), "Scrolling down", Toast.LENGTH_SHORT).show();
                TransitionManager.beginDelayedTransition(constraintLayout);
                layout2.applyTo(constraintLayout);
                isOpen = !isOpen;
                trending_RecyclerView.setVisibility(View.GONE);
                if(!isOpen){

                }else{

                }
            }else{


              //  Toast.makeText(getContext(), "Scrolling up", Toast.LENGTH_SHORT).show();
                //  RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,200);
                //  cardView.getLayoutParams();
                //  trending_RecyclerView.setLayoutParams(params);
                TransitionManager.beginDelayedTransition(constraintLayout);
                layout1.applyTo(constraintLayout);
                isOpen = !isOpen;
                if(dy == 0){
                    trending_RecyclerView.setVisibility(View.INVISIBLE);
                    if(dy < 0){
                        trending_RecyclerView.setVisibility(View.GONE);
                    }else{
                        trending_RecyclerView.setVisibility(View.VISIBLE);
                    }


                }


            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.whats_new_layout,container,false);
        recyclerView = v.findViewById(R.id.whatsnew_recyclerview);
        trending_RecyclerView = v.findViewById(R.id.trending_on_social_media);
        cardView = v.findViewById(R.id.trending_layout);

        layout1 = new ConstraintSet();
        layout2 = new ConstraintSet();
        constraintLayout = v.findViewById(R.id.whats_new_constraint_layout);

        layout2.clone(getContext(),R.layout.whats_new_layout_collapsed);
        layout1.clone(constraintLayout);

        TrendingSocialRecyclerViewAdapter trending_adapter = new TrendingSocialRecyclerViewAdapter(getContext(),trendingList);
        WhatsNewRecyclerViewAdapter adapter = new WhatsNewRecyclerViewAdapter(getContext(),whatsNewList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        trending_RecyclerView.setLayoutManager(layoutManager);
        trending_RecyclerView.setAdapter(trending_adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(onScrollListener);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        trendingList = new ArrayList<>();
        whatsNewList = new ArrayList<>();

        whatsNewList.add(new WhatsNew("Data Item 1","Fionna","fionna@example.com",R.drawable.calculate_calories));
        whatsNewList.add(new WhatsNew("Data Item 2","John","john@example.com",R.drawable.breaking_news));
        whatsNewList.add(new WhatsNew("Data Item 3","Cris","cris@example.com",R.drawable.protein_shake));
        whatsNewList.add(new WhatsNew("Data Item 4","Becky","becky@example.com",R.drawable.trending));

        whatsNewList.add(new WhatsNew("Data Item 1","Fionna","fionna@example.com",R.drawable.calculate_calories));
        whatsNewList.add(new WhatsNew("Data Item 2","John","john@example.com",R.drawable.breaking_news));
        whatsNewList.add(new WhatsNew("Data Item 3","Cris","cris@example.com",R.drawable.protein_shake));
        whatsNewList.add(new WhatsNew("Data Item 4","Becky","becky@example.com",R.drawable.trending));

        trendingList.add(new Trending("World Just blew up",R.drawable.trending_now));
        trendingList.add(new Trending("Joe win's the election",R.drawable.travel));
        trendingList.add(new Trending("You know what it's trending",R.drawable.drawer_ackground));
        trendingList.add(new Trending("World Just blew up",R.drawable.trending_now));
        trendingList.add(new Trending("Joe win's the election",R.drawable.travel));
        trendingList.add(new Trending("You know what it's trending",R.drawable.drawer_ackground));












    }
}
