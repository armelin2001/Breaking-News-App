package com.br.breakingnewsapp.ui.slideshow;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.breakingnewsapp.R;
import com.br.breakingnewsapp.model.Noticia;
import com.squareup.picasso.Picasso;

public class FullNewsSlide extends Activity {
    private Noticia noticia;
    private ImageView imageView;
    private TextView textView;
    private TextView textViewDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_news_slide);
        imageView = findViewById(R.id.ImageViewFullNewsSlide);
        textView = findViewById(R.id.textViewTitleFullNewsSlide);
        textViewDescription = findViewById(R.id.textViewDescriptionSlide);
        Bundle extras = getIntent().getExtras();
        noticia = (Noticia) extras.getSerializable("newsSlide");
        Picasso.with(FullNewsSlide.this).load(noticia.getImg()).resize(1300,600).into(imageView);
        textView.setText(noticia.getTitle());
        textViewDescription.setText(noticia.getDescription());
    }
}