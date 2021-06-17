package com.br.breakingnewsapp.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.br.breakingnewsapp.R;
import com.br.breakingnewsapp.model.Noticia;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
    private List<Noticia> list= new ArrayList<>();
    //private LayoutInflater inflater;
    //private Context context;
    public HomeAdapter(List<Noticia> list){
        this.list = list;
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
        holder.setData(current,position);
        /*holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(context, holder.desciption.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView desciption;
        TextView title;
        TextView img;
        TextView link;
        public MyViewHolder(View itemView){
            super(itemView);
            desciption = (TextView) itemView.findViewById(R.id.textViewDescription);
            title= (TextView) itemView.findViewById(R.id.textViewTitle);
            img = (TextView) itemView.findViewById(R.id.textViewImg);
            link = (TextView) itemView.findViewById(R.id.textViewLink);
        }
        public void setData(Noticia current, int position){
            this.desciption.setText(current.getDescription());
            this.title.setText(current.getTitle());
            this.img.setText(current.getImg());
            this.link.setText(current.getLink());
        }
    }
}
