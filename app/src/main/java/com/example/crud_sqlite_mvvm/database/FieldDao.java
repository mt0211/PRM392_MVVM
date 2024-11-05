package com.example.crud_sqlite_mvvm.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.crud_sqlite_mvvm.model.Field;

import java.util.List;

@Dao
public interface FieldDao {
    @Insert
    long insert(Field field); // Phương thức để thêm trường

    @Update
    void update(Field field);

    @Delete
    void delete(Field field);

    @Query("SELECT * FROM field WHERE category_id = :categoryId")
    LiveData<List<Field>> getFieldsByCategory(int categoryId);

    @Query("SELECT * FROM field")
    LiveData<List<Field>> getAllFields();

    @Query("SELECT * FROM field")
    List<Field> getAllFieldsSync();

    @Query("SELECT * FROM field WHERE category_id = :categoryId")
    LiveData<List<Field>> getFieldsByCategory(long categoryId);

}

