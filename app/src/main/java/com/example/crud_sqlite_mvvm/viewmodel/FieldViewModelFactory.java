package com.example.crud_sqlite_mvvm.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.crud_sqlite_mvvm.database.FieldDao;

public class FieldViewModelFactory implements ViewModelProvider.Factory {
    private final FieldDao fieldDao;

    public FieldViewModelFactory(FieldDao fieldDao) {
        this.fieldDao = fieldDao;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(FieldViewModel.class)) {
            return (T) new FieldViewModel(fieldDao);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
