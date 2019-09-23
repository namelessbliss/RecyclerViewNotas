package com.app.nb.responsivedesign;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class NotaFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 2;
    private NotasInteractionListener mListener;

    private List<Nota> notaList;

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

            notaAadapter = new MyNotaRecyclerViewAdapter(notaList, mListener);

            recyclerView.setAdapter(notaAadapter);
        }
        return view;
    }

    private List<Nota> getNotas() {
        return new ArrayList<Nota>() {{
            add(new Nota("Lista de compras", "Comprar pan y un kilo de huevo", false, android.R.color.holo_blue_light));
            add(new Nota("Recoger hija de la escuela", "No olvidar ir a recoGer a la niña a la escuela a las de 2 pm ya que mamá no volvera del trabajo hoy", true, android.R.color.holo_red_light));
            add(new Nota("Recordatorio de deuda", "Pagar para el sabado", false, android.R.color.holo_green_dark));
            add(new Nota("Cumpleaño", "No olvidar comprar un regalo para el cumpleaños de la vecina", true, android.R.color.holo_purple));
        }};
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof NotasInteractionListener) {
            mListener = (NotasInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
