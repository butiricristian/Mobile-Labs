package ro.ubb.cristian.examproject.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ro.ubb.cristian.examproject.model.Project;


/**
 * Created by crist on 28-Jan-18.
 */

@Database(entities = {Project.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CarDao getCarDao();
}