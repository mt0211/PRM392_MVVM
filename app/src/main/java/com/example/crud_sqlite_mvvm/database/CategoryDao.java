package com.example.crud_sqlite_mvvm.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.crud_sqlite_mvvm.model.Category;

import java.util.List;

@Dao
public interface CategoryDao {
    @Insert
    void insert(Category category);

    @Update
    void update(Category category);

    @Delete
    void delete(Category category);

    @Query("SELECT * FROM Category")
    LiveData<List<Category>> getAllCategoriesLiveData();

    @Query("SELECT * FROM Category")
    List<Category> getAllCategoriesSync();

    @Query("SELECT * FROM Category WHERE categoryId = :id LIMIT 1")
    Category getCategoryById(int id);
}
