package com.example.crud;

public class Module {
    private String title;
    private String label;
    private String desc;
    private String imgSrc;

    public Module(String title, String label, String desc, String imgSrc) {
        this.title = title;
        this.label = label;
        this.desc = desc;
        this.imgSrc = imgSrc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }
}
