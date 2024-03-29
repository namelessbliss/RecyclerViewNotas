package com.app.nb.responsivedesign.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.app.nb.responsivedesign.NuevaNotaDialogViewModel;
import com.app.nb.responsivedesign.R;
import com.app.nb.responsivedesign.db.entity.NotaEntity;

import java.util.List;

public class MyNotaRecyclerViewAdapter extends RecyclerView.Adapter<MyNotaRecyclerViewAdapter.ViewHolder> {

    private List<NotaEntity> mValues;
    private Context context;
    private NuevaNotaDialogViewModel viewModel;

    public MyNotaRecyclerViewAdapter(List<NotaEntity> items, Context context) {
        mValues = items;
        this.context = context;
        viewModel = ViewModelProviders.of((AppCompatActivity) context).get(NuevaNotaDialogViewModel.class);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_nota, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
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
                if (holder.mItem.isFavorita()) {
                    holder.mItem.setFavorita(false);
                    holder.ivFavorito.setImageResource(R.drawable.ic_star_border_black_24dp);
                } else {
                    holder.mItem.setFavorita(true);
                    holder.ivFavorito.setImageResource(R.drawable.ic_star_black_24dp);
                }

                viewModel.actualizarNota(holder.mItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void setNuevasNotas(List<NotaEntity> nuevasNotas) {
        this.mValues = nuevasNotas;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView tvTitulo;
        public final TextView tvContenido;
        public final ImageView ivFavorito;
        public NotaEntity mItem;

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
