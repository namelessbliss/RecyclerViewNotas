package com.app.nb.responsivedesign.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.app.nb.responsivedesign.NuevaNotaDialogFragment;
import com.app.nb.responsivedesign.NuevaNotaDialogViewModel;
import com.app.nb.responsivedesign.R;
import com.app.nb.responsivedesign.db.entity.NotaEntity;

import java.util.ArrayList;
import java.util.List;

public class NotaFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";

    private int mColumnCount = 2;

    private List<NotaEntity> notaList;

    private MyNotaRecyclerViewAdapter notaAadapter;

    private NuevaNotaDialogViewModel notaViewModel;

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

        //Indica que el fragment tiene un menu option propio
        setHasOptionsMenu(true);
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

            lanzarViewModel();
        }
        return view;
    }

    private void lanzarViewModel() {
        notaViewModel = ViewModelProviders.of(getActivity()).get(NuevaNotaDialogViewModel.class);
        notaViewModel.getNotas().observe(getActivity(), new Observer<List<NotaEntity>>() {
            @Override
            public void onChanged(List<NotaEntity> notaEntities) {
                notaAadapter.setNuevasNotas(notaEntities);
            }
        });
    }

    private List<NotaEntity> getNotas() {
        return new ArrayList<NotaEntity>() {{
        }};
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.option_menu_nota_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_nota:
                mostrarDialogNuevaNota();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void mostrarDialogNuevaNota() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        NuevaNotaDialogFragment dialogFragment = new NuevaNotaDialogFragment();
        dialogFragment.show(manager, "NuevaNotaDialogFragment");
    }
}
