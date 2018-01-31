package ro.ubb.cristian.examskeletonimproved.database;

import android.arch.persistence.room.Room;
import android.content.Context;

/**
 * Created by crist on 01-Dec-17.
 */

public class DatabaseProvider {
    private static AppDatabase database = null;

    public static AppDatabase getDatabaseInstance(Context context){
        if(database != null){
            return database;
        }
        database = Room.databaseBuilder(context, AppDatabase.class, "androidDB")
                .allowMainThreadQueries()
                .build();
        return database;
    }
}
