package com.br.breakingnewsapp.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.br.breakingnewsapp.R;
import com.br.breakingnewsapp.model.Noticia;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.ArrayList;
import java.util.List;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
    private List<Noticia> list;
    private Context context;
    private OnItemClickListener clickListener;
    interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
    public HomeAdapter(List<Noticia> list,Context context){
        this.list = list;
        this.context = context;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.item_home_recyclerview,
                        parent,false
                );
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Noticia current = list.get(position);

        holder.title.setText(current.getTitle());

        //Picasso.with(context).load(current.getImg()).resize(800,400).into(holder.imageView);
        //Picasso.with(context).load(current.getImg()).resize(450,holder.imageView.getHeight()).into(holder.imageView);
        Picasso.with(context).load(current.getImg()).resize(450,250).into(holder.imageView);
        /*holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(context, holder.desciption.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView imageView;
        public MyViewHolder(View itemView){
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.textViewTitle);
            imageView = itemView.findViewById(R.id.imageViewNews);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onItemClick(view,getAdapterPosition());
                }
            });
        }

    }
}
