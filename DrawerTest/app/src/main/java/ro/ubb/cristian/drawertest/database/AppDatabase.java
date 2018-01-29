package ro.ubb.cristian.drawertest.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ro.ubb.cristian.drawertest.model.car.Car;

/**
 * Created by crist on 28-Jan-18.
 */

@Database(entities = {Car.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CarDao getCarDao();
}