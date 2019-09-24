package com.app.nb.responsivedesign.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.app.nb.responsivedesign.db.entity.NotaEntity;

import java.util.List;

@Dao
public interface NotaDAO {

    @Insert
    void insertarNota(NotaEntity nota);

    @Update
    void actualizarNota(NotaEntity nota);

    @Query("DELETE FROM NOTAS")
    void borrarTodo();

    @Query("DELETE FROM NOTAS WHERE id = :idNota")
    void borrarNotaPorId(int idNota);

    @Query("SELECT * FROM NOTAS ORDER BY titulo ASC")
    LiveData<List<NotaEntity>> getNotas();

    @Query("SELECT * FROM NOTAS WHERE FAVORITA LIKE 1")
    LiveData<List<NotaEntity>> getNotasFavoritas();

}
