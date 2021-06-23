package com.br.breakingnewsapp.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.br.breakingnewsapp.FullNews;
import com.br.breakingnewsapp.R;
import com.br.breakingnewsapp.model.Noticia;
import com.br.breakingnewsapp.ui.home.HomeAdapter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SlideshowFragment extends Fragment {
    private SlideAdapter slideAdapter;
    private RecyclerView recyclerView;
    private List<Noticia> listNoticias;
    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    //oi12@gmail  123456789
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        listNoticias = new ArrayList<>();

        ValueEventListener itensEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot d: snapshot.getChildren()){
                    Noticia noticia = d.getValue(Noticia.class);
                    listNoticias.add(new Noticia(noticia.getTitle(),noticia.getImg(),noticia.getDescription()));
                    Log.d("noticiaObje",noticia.getTitle());

                }
                listNoticias.remove(0);

                recyclerView = (RecyclerView) root.findViewById(R.id.recyclerViewSlide);


                slideAdapter = new SlideAdapter(listNoticias,getActivity());
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),1,GridLayoutManager.VERTICAL,false);
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(slideAdapter);
                slideAdapter.setClickListener(new SlideAdapter.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {
                        slideAdapter.notifyItemChanged(position);
                        Intent intent = new Intent(getActivity(),FullNewsSlide.class);

                        intent.putExtra("newsSlide",listNoticias.get(position));

                        getActivity().startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        DatabaseReference rootReference = firebaseDatabase.getReference();
        rootReference.child(firebaseUser.getUid()).child("news").addValueEventListener(itensEventListener);

        Log.d("tamanhoListaMain",String.valueOf(listNoticias.size()));


        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


}