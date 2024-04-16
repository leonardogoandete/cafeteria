package br.com.cafeteria.dao;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.com.cafeteria.entity.Cafe;

@Database(entities = {Cafe.class}, version = 1, exportSchema = true)
public abstract class AppDatabase extends RoomDatabase{
    private static AppDatabase appDatabase;

    public abstract CafeDAO createCafeDAO();

    public static AppDatabase getInstance(Context ctx){
        if (appDatabase == null){
            appDatabase = Room.databaseBuilder(ctx.getApplicationContext(), AppDatabase.class, "driver_database")
                    .allowMainThreadQueries()
                    .build();
        }
        return appDatabase;
    }
}
