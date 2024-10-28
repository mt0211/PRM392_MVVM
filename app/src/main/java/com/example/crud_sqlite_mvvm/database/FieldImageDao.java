package com.example.crud_sqlite_mvvm.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.crud_sqlite_mvvm.model.FieldImage;
import java.util.List;

@Dao
public interface FieldImageDao {
    @Insert
    long insert(FieldImage fieldImage);

    @Query("SELECT * FROM field_images WHERE fieldId = :fieldId")
    LiveData<List<FieldImage>> getImagesByFieldId(int fieldId);
}

