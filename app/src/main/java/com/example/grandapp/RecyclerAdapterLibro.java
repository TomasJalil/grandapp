package com.example.grandapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


    public  class RecyclerViewAdaptador extends RecyclerView.Adapter<RecyclerViewAdaptador.ViewHolder> {
        public static class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView imgLibro;
            private TextView tituloLibro;
            private TextView contenidoLibro;
            private TextView autor;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                imgLibro=itemView.findViewById(R.id.imgLibro);
                autor=itemView.findViewById(R.id.autor);
                tituloLibro=itemView.findViewById(R.id.title);
                contenidoLibro=itemView.findViewById(R.id.content);
            }

        }
        public List<Libro> libroslista;

        public RecyclerViewAdaptador(List<Libro> libroslista) {
            this.libroslista = libroslista;
        }

        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerlibro,parent, false);
            ViewHolder viewHolder= new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.imgLibro.setImageBitmap(libroslista.get(position).getUrlMiniatura());
            holder.contenidoLibro.setText(libroslista.get(position).getDescripcion());
            holder.tituloLibro.setText(libroslista.get(position).getTitulo()));
            holder.autor.setText(libroslista.get(position).getAutor());


        }

        @Override
        public int getItemCount() {
            return libroslista.size();
        }

        public static class RecyclerHolder extends RecyclerView.ViewHolder{



            public RecyclerHolder(@NonNull View itemView){
                super(itemView);
                imgLibro=itemView.findViewById(R.id.imgLibro);
                tituloLibro=itemView.findViewById(R.id.title);
                contenidoLibro=itemView.findViewById(R.id.content);
                autor=itemView.findViewById(R.id.autor);
            }
        }


    }

}
