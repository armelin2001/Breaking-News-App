package com.br.breakingnewsapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Noticia implements Serializable {
    private String title;
    private String link;
    private String description;
    private String img;

    public Noticia(String title, String img,String description) {
        this.title = title;
        this.img = img;
        this.description = description;
    }
    public Noticia(){}

    public Noticia(String title, String link, String description, String img) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Noticia{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
