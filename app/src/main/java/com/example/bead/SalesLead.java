package com.example.bead;

public class SalesLead {
    private int id;
    private String href;
    private String description;
    private String name;

    public SalesLead() {
    }

    public SalesLead(int id, String href, String description, String name) {
        this.id = id;
        this.href = href;
        this.description = description;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
