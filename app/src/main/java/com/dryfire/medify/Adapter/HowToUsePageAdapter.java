package com.dryfire.medify.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.dryfire.medify.R;
import com.dryfire.medify.UI.HowToUse.ScreenItem;

import java.util.List;

public class HowToUsePageAdapter extends PagerAdapter {

    Context context;
    List<ScreenItem> screenItemList;

    public HowToUsePageAdapter(Context context, List<ScreenItem> screenItemList) {
        this.context = context;
        this.screenItemList = screenItemList;
    }

    @Override
    public int getCount() {
        return screenItemList.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen = inflater.inflate(R.layout.how_to_use_layout_screen,null);

        ImageView imageView = layoutScreen.findViewById(R.id.howtouse_ImageView);
        TextView titleText = layoutScreen.findViewById(R.id.howtouse_TitleTextView);
        TextView descriptionText = layoutScreen.findViewById(R.id.howtouse_DescriptionTextView);

        titleText.setText(screenItemList.get(position).getTitle());
        imageView.setImageResource(screenItemList.get(position).getImage());
        descriptionText.setText(screenItemList.get(position).getDescription());

        container.addView(layoutScreen);

        return layoutScreen;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return  view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
         container.removeView((View) object);
    }
}
