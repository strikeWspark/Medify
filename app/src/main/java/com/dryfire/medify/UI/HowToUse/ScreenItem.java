package com.dryfire.medify.UI.HowToUse;

public class ScreenItem {

    private String title,Description;
    int image;

    public ScreenItem(String title, String description, int image) {
        this.title = title;
        Description = description;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
