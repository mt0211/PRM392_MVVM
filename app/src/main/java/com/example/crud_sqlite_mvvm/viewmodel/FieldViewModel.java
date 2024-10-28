package com.example.crud_sqlite_mvvm.viewmodel;

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
        this.fields = fieldDao.getAllFields();
    }

    public LiveData<List<Field>> getFields() {
        return fields;
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
