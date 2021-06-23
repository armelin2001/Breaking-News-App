package com.br.breakingnewsapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.br.breakingnewsapp.FullNews;
import com.br.breakingnewsapp.R;
import com.br.breakingnewsapp.model.FeedRss;
import com.br.breakingnewsapp.model.Noticia;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private HomeAdapter homeAdapter;
    private RecyclerView recyclerView;
    private List<Noticia> list = new ArrayList<>();
    private FeedRss feedRss;
    //https://g1.globo.com/rss/g1/tecnologia/
    //https://g1.globo.com/rss/g1/brasil/
    //https://g1.globo.com/rss/g1/economia/
    private static final String XML_URL = "https://g1.globo.com/rss/g1/economia/";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerViewHome);
        feedRss = new FeedRss();
        list = feedRss.loadData(XML_URL,getActivity(),list);

        homeAdapter = new HomeAdapter(list,getActivity());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),1,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(homeAdapter);
        if(homeAdapter.getItemCount()==0){
            list.clear();
            list= feedRss.loadData(XML_URL,getActivity(),list);
        }
        homeAdapter.setClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                homeAdapter.notifyItemChanged(position);
                Intent intent = new Intent(getActivity(), FullNews.class);
                intent.putExtra("news", list.get(position));
                getActivity().startActivity(intent);
            }
        });
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}