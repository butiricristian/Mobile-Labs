package ro.ubb.cristian.examskeletonimproved.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ro.ubb.cristian.examskeletonimproved.model.Item;


/**
 * Created by crist on 28-Jan-18.
 */

@Database(entities = {Item.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CarDao getCarDao();
}