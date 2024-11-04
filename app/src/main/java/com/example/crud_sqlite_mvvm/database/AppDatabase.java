package com.example.crud_sqlite_mvvm.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Context;

import com.example.crud_sqlite_mvvm.model.Category;
import com.example.crud_sqlite_mvvm.model.Field;
import com.example.crud_sqlite_mvvm.model.FieldImage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Field.class, Category.class, FieldImage.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FieldDao fieldDao();
    public abstract CategoryDao categoryDao();
    public abstract FieldImageDao fieldImageDao();

    private static AppDatabase instance;
    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
