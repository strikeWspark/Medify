package com.dryfire.medify.Model;

public class WhatsNew {

    private String name;
    private String username;
    private String item;

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

    public WhatsNew(String item, String name, String username) {
        this.name = name;
        this.username = username;
        this.item = item;
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
