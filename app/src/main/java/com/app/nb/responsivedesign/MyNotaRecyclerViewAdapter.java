package com.app.nb.responsivedesign;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyNotaRecyclerViewAdapter extends RecyclerView.Adapter<MyNotaRecyclerViewAdapter.ViewHolder> {

    private final List<Nota> mValues;
    private Context context;

    public MyNotaRecyclerViewAdapter(List<Nota> items, Context context) {
        mValues = items;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_nota, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.tvTitulo.setText(mValues.get(position).getTitulo());
        holder.tvContenido.setText(mValues.get(position).getContenido());

        if (holder.mItem.isFavorita())
            holder.ivFavorito.setImageResource(R.drawable.ic_star_black_24dp);
        else
            holder.ivFavorito.setImageResource(R.drawable.ic_star_border_black_24dp);

        holder.ivFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView tvTitulo;
        public final TextView tvContenido;
        public final ImageView ivFavorito;
        public Nota mItem;

        public ViewHolder(View view) {
            super(view);
            tvTitulo = view.findViewById(R.id.textViewTitulo);
            tvContenido = view.findViewById(R.id.textViewContenido);
            ivFavorito = view.findViewById(R.id.imageViewFavorito);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvTitulo.getText() + "'";
        }
    }
}
