package com.app.nb.responsivedesign;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.app.nb.responsivedesign.db.NotaRoomDatabase;
import com.app.nb.responsivedesign.db.dao.NotaDAO;
import com.app.nb.responsivedesign.db.entity.NotaEntity;

import java.util.List;

/**
 * Repositorio
 */
public class NotaRepository {

    private NotaDAO notaDAO;
    private LiveData<List<NotaEntity>> notas;
    private LiveData<List<NotaEntity>> notasFavoritas;

    public NotaRepository(Application application) {

        NotaRoomDatabase db = NotaRoomDatabase.getDatabase(application);
        notaDAO = db.notaDAO();
        notas = notaDAO.getNotas();
        notasFavoritas = notaDAO.getNotasFavoritas();
    }

    public LiveData<List<NotaEntity>> getNotas() {
        return notas;
    }

    public LiveData<List<NotaEntity>> getNotasFavoritas() {
        return notasFavoritas;
    }

    public void insertarNota(NotaEntity nota) {
        new InsertAsyncTask(notaDAO).execute(nota);
    }

    /**
     * Clase asincrona para la insersion de notas
     */
    private class InsertAsyncTask extends AsyncTask<NotaEntity, Void, Void> {

        private NotaDAO notaDAOAsyncTask;

        /**
         * Obtiene el dao como paametro e igual al dao del contexto actual
         * @param dao
         */
        InsertAsyncTask(NotaDAO dao) {
            notaDAOAsyncTask = dao;
        }

        @Override
        protected Void doInBackground(NotaEntity... notaEntities) {
            //obtiene el primer elemento Nota de la lista para insertarlo
            notaDAOAsyncTask.insertarNota(notaEntities[0]);
            return null;
        }
    }
}
