package ro.ubb.cristian.examskeletonimproved.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ro.ubb.cristian.examskeletonimproved.model.Item;


/**
 * Created by crist on 28-Jan-18.
 */
@Dao
public interface CarDao {
    @Query("SELECT * FROM Item")
    List<Item> findAll();

    @Query("SELECT * FROM Item WHERE id = :id")
    Item findOne(Long id);

    @Insert
    void save(Item... b);

    @Update
    void update(Item... b);

    @Delete
    void delete(Item... b);

    @Query("DELETE FROM Item")
    void deleteAll();
}
