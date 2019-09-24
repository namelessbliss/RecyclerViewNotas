package com.app.nb.responsivedesign;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.app.nb.responsivedesign.db.entity.NotaEntity;

import java.util.List;

public class NuevaNotaDialogViewModel extends AndroidViewModel {

    private LiveData<List<NotaEntity>> notas;
    private NotaRepository notaRepository;

    public NuevaNotaDialogViewModel(Application application) {
        super(application);

        notaRepository = new NotaRepository(application);
        notas = notaRepository.getNotas();
    }

    //Fragment que necesita recibir la lista de datos
    public LiveData<List<NotaEntity>> getNotas() {
        return notas;
    }

    //Fragment que inserte una nueva nota, debera comunicarlo a este ViewModel
    public void insertarNota(NotaEntity notaEntity) {
        notaRepository.insertarNota(notaEntity);
    }

    public void actualizarNota(NotaEntity notaEntity) {
        notaRepository.actualizarNota(notaEntity);
    }

}
