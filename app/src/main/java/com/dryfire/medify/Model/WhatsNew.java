package com.dryfire.medify.Model;

import java.io.Serializable;

public class WhatsNew implements Serializable {

    private String name;
    private String username;
    private String item;
    private int whats_new_image;



    public WhatsNew(String name, String username, String item, int whats_new_image) {
        this.name = name;
        this.username = username;
        this.item = item;
        this.whats_new_image = whats_new_image;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getWhats_new_image() {
        return whats_new_image;
    }

    public void setWhats_new_image(int whats_new_image) {
        this.whats_new_image = whats_new_image;
    }

    public WhatsNew(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }


}
