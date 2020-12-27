package com.dryfire.medify.Model;

public class Trending {

    private String info_text;
    private int trending_image;

    public Trending(String info_text, int trending_image) {
        this.info_text = info_text;
        this.trending_image = trending_image;
    }


    public int getTrending_image() {
        return trending_image;
    }

    public void setTrending_image(int trending_image) {
        this.trending_image = trending_image;
    }

    public String getInfo_text() {
        return info_text;
    }

    public void setInfo_text(String info_text) {
        this.info_text = info_text;
    }
}
