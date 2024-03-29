package com.app.nb.responsivedesign.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.app.nb.responsivedesign.db.dao.NotaDAO;
import com.app.nb.responsivedesign.db.entity.NotaEntity;

@Database(entities = {NotaEntity.class}, version = 1)
public abstract class NotaRoomDatabase extends RoomDatabase {

    public abstract NotaDAO notaDAO();

    private static volatile NotaRoomDatabase INSTANCIA;

    /**
     * Patron Singleton para la db Room
     * @param context
     * @return
     */
    public static NotaRoomDatabase getDatabase(final Context context) {
        if (INSTANCIA == null) {

            synchronized (NotaRoomDatabase.class) {
                if (INSTANCIA == null) {
                    INSTANCIA = Room.databaseBuilder(context.getApplicationContext(), NotaRoomDatabase.class, "db_notas")
                    .build();
                }
            }

        }

        return INSTANCIA;
    }
}
