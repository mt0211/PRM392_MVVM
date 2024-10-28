package com.example.crud_sqlite_mvvm.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

import com.example.crud_sqlite_mvvm.R;
import com.example.crud_sqlite_mvvm.model.Field;
import com.example.crud_sqlite_mvvm.model.Category;
import com.example.crud_sqlite_mvvm.model.FieldImage;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Field.class, Category.class, FieldImage.class}, version = 1)
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
            // Populate initial data after the database is built
            executorService.execute(AppDatabase::populateInitialData);
        }
        return instance;
    }

    private static void populateInitialData() {
        executorService.execute(() -> {
            try {
                CategoryDao categoryDao = instance.categoryDao();
                FieldDao fieldDao = instance.fieldDao();

                List<Category> categories = categoryDao.getAllCategoriesSync();
                if (categories.isEmpty()) {
                    // Add sample categories
                    Category category1 = new Category("Football", R.drawable.football);
                    Category category2 = new Category("Tennis", R.drawable.tenis);
                    Category category3 = new Category("Volleyball", R.drawable.volleyball);
                    categoryDao.insert(category1);
                    categoryDao.insert(category2);
                    categoryDao.insert(category3);

                    // Now check fields using synchronous method
//                    List<Field> fields = fieldDao.getAllFieldsSync();
//                    if (fields.isEmpty()) {
//                        Field field1 = new Field(10, "09:00", "11:00", 2, "Location 1", "Description 1", category1.getCategoryId());
//                        Field field2 = new Field(5, "12:00", "14:00", 2, "Location 2", "Description 2", category2.getCategoryId());
//                        fieldDao.insert(field1);
//                        fieldDao.insert(field2);
//                    }
                }
            } catch (Exception e) {
                Log.e("AppDatabase", "Error populating initial data: " + e.getMessage(), e);
            }
        });
    }
}