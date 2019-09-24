package com.app.nb.responsivedesign.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.app.nb.responsivedesign.R;
import com.app.nb.responsivedesign.db.entity.NotaEntity;

import java.util.ArrayList;
import java.util.List;

public class NotaFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";

    private int mColumnCount = 2;

    private List<NotaEntity> notaList;

    private MyNotaRecyclerViewAdapter notaAadapter;

    public NotaFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static NotaFragment newInstance(int columnCount) {
        NotaFragment fragment = new NotaFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nota_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                //recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(mColumnCount, StaggeredGridLayoutManager.VERTICAL));
            }

            notaList = getNotas();

            notaAadapter = new MyNotaRecyclerViewAdapter(notaList, getActivity());

            recyclerView.setAdapter(notaAadapter);
        }
        return view;
    }

    private List<NotaEntity> getNotas() {
        return new ArrayList<NotaEntity>() {{
            add(new NotaEntity("Lista de compras", "Comprar pan y un kilo de huevo", false, android.R.color.holo_blue_light));
            add(new NotaEntity("Recoger hija de la escuela", "No olvidar ir a recoGer a la niña a la escuela a las de 2 pm ya que mamá no volvera del trabajo hoy", true, android.R.color.holo_red_light));
            add(new NotaEntity("Recordatorio de deuda", "Pagar para el sabado", false, android.R.color.holo_green_dark));
            add(new NotaEntity("Cumpleaño", "No olvidar comprar un regalo para el cumpleaños de la vecina", true, android.R.color.holo_purple));
        }};
    }
}
