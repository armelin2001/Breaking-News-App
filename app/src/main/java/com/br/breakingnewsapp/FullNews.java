package com.br.breakingnewsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.br.breakingnewsapp.R;
import com.br.breakingnewsapp.model.Noticia;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FullNews extends AppCompatActivity {
    private Noticia noticia;
    private ImageView imageView;
    private TextView textView;
    private TextView textViewDescription;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    //private ArrayList<Noticia> listNoticias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_news);
        imageView = findViewById(R.id.ImageViewFullNews);
        textView = findViewById(R.id.textViewTitleFullNews);
        textViewDescription = findViewById(R.id.textViewDescription);
        Bundle extras = getIntent().getExtras();
        //listNoticias = new ArrayList<>();
        noticia = (Noticia) extras.getSerializable("news");
        Picasso.with(this).load(noticia.getImg()).resize(1300,600).into(imageView);
        textView.setText(noticia.getTitle());
        textViewDescription.setText(noticia.getDescription());
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
    }
//oi12@gmail  123456789
    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser = firebaseAuth.getCurrentUser();
        //databaseListening();
    }
    public void addNews(View view){
        DatabaseReference rootReference = firebaseDatabase.getReference();
        rootReference.child(firebaseUser.getUid()).child("news").push().setValue(noticia);

    }

}