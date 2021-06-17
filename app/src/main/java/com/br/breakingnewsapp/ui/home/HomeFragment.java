package com.br.breakingnewsapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.br.breakingnewsapp.R;
import com.br.breakingnewsapp.model.Noticia;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeAdapter homeAdapter;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerViewHome);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2,GridLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        List<Noticia> list = new ArrayList<>();
        list.add(new Noticia("titulo","link","des"));
        list.add(new Noticia("segundoTitulo","segundoLink","segundaDescrição"));
        homeAdapter = new HomeAdapter(list);
        recyclerView.setAdapter(homeAdapter);
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}