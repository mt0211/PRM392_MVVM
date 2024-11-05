package com.example.crud_sqlite_mvvm.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.crud_sqlite_mvvm.database.FieldDao;
import com.example.crud_sqlite_mvvm.model.Field;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FieldViewModel extends ViewModel {
    private final FieldDao fieldDao;
    private final LiveData<List<Field>> fields;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public FieldViewModel(FieldDao fieldDao) {
        this.fieldDao = fieldDao;
        this.fields = fieldDao.getAllFields(); // Initialize fields LiveData
    }

    public LiveData<List<Field>> getFields() {
        // Return the existing LiveData instead of creating a new one
        Log.d("FieldViewModel", "Fields loaded: " + (fields.getValue() != null ? fields.getValue().size() : "null"));
        return fields; // Return the initialized LiveData
    }
    public LiveData<List<Field>> getFieldsByCategory(long categoryId) {
        return fieldDao.getFieldsByCategory(categoryId);
    }

    public void insert(Field field) {
        executorService.execute(() -> {
            try {
                fieldDao.insert(field);
            } catch (Exception e) {
                // Handle the error
            }
        });
    }

    public void update(Field field) {
        executorService.execute(() -> {
            try {
                fieldDao.update(field);
            } catch (Exception e) {
                // Handle the error
            }
        });
    }

    public void delete(Field field) {
        executorService.execute(() -> {
            try {
                fieldDao.delete(field);
            } catch (Exception e) {
                // Handle the error
            }
        });
    }
}
