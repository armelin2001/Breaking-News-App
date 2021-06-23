package com.br.breakingnewsapp.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;


public class HomeViewModel extends ViewModel {



    //private MutableLiveData<String> mText;
    private MutableLiveData<RecyclerView> recyclerViewMutableLiveData;
    public HomeViewModel() {
        /*mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");*/
        recyclerViewMutableLiveData= new MutableLiveData<>();
    }

    /*public LiveData<String> getText() {
        return mText;
    }*/
    public void setRecyclerViewMutableLiveData(RecyclerView recyclerView) {
        recyclerViewMutableLiveData.setValue(recyclerView);
    }
}