package com.example.crud_sqlite_mvvm.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.crud_sqlite_mvvm.database.CategoryDao;

public class CategoryViewModelFactory implements ViewModelProvider.Factory {
    private final CategoryDao categoryDao;

    public CategoryViewModelFactory(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CategoryViewModel.class)) {
            return (T) new CategoryViewModel(categoryDao);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
