package com.vs.room.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RoomDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(RoomEntity name);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(RoomEntity name);

    @Delete
    void delete(RoomEntity name);

    @Query("DELETE FROM word_table")
    void deleteAll();

    @Query("SELECT * from word_table ORDER BY first_name ASC")
    LiveData<List<RoomEntity>> getAlphabetizedWords();
}
