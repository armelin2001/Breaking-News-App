package com.br.breakingnewsapp.model;

import android.content.Context;


public class DataModel {
    private static DataModel instance = new DataModel();
    private DataModel(){}
    public static DataModel getInstance(){return instance;}
    private Context context;
    //private FeedRss feedRss;

    public void setContext(Context context){
        this.context = context;
    }


}
