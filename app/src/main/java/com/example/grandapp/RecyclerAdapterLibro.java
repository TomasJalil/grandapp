package com.example.grandapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapterLibro extends RecyclerView.Adapter<RecyclerAdapterLibro.RecyclerHolder> implements View.OnClickListener {
    private List<itemListLibro> items;
private View.OnClickListener listener;
    public RecyclerAdapterLibro(List<itemListLibro> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_libro, parent, false);
        view.setOnClickListener(this);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        itemListLibro item=items.get(position);
        holder.portadaLibro.setImageBitmap(item.getImgResource());
        holder.tituloLibro.setText(item.getTitulo());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
}
    @Override
    public void onClick(View view) {
if (listener!=null){
    listener.onClick(view);
}
    }


    public static class RecyclerHolder extends  RecyclerView.ViewHolder{
        private ImageView portadaLibro;
        private TextView tituloLibro;
        public RecyclerHolder(@NonNull View itemView){
            super(itemView);
            portadaLibro=itemView.findViewById(R.id.portadaLibro);
            tituloLibro=itemView.findViewById(R.id.tituloLibro);

        }
    }
}
