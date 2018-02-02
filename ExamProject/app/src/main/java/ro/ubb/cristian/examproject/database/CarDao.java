package ro.ubb.cristian.examproject.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ro.ubb.cristian.examproject.model.Project;


/**
 * Created by crist on 28-Jan-18.
 */
@Dao
public interface CarDao {
    @Query("SELECT * FROM Project")
    List<Project> findAll();

    @Query("SELECT * FROM Project WHERE id = :id")
    Project findOne(Long id);

    @Insert
    void save(Project... b);

    @Update
    void update(Project... b);

    @Delete
    void delete(Project... b);

    @Query("DELETE FROM Project")
    void deleteAll();
}
