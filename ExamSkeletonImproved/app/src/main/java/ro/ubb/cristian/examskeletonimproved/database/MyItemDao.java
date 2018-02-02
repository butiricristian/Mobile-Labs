package ro.ubb.cristian.examskeletonimproved.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ro.ubb.cristian.examskeletonimproved.model.Item;
import ro.ubb.cristian.examskeletonimproved.model.MyItem;

/**
 * Created by crist on 01-Feb-18.
 */

@Dao
public interface MyItemDao {
    @Query("SELECT * FROM MyItem")
    List<MyItem> findMyItems();

    @Query("SELECT * FROM MyItem WHERE id = :id")
    Item findOne(Long id);

    @Insert
    void save(MyItem... item);

    @Delete
    void delete(MyItem... b);

    @Update
    void update(MyItem... b);
}
