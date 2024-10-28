package com.example.crud_sqlite_mvvm.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.crud_sqlite_mvvm.database.CategoryDao;
import com.example.crud_sqlite_mvvm.model.Category;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CategoryViewModel extends ViewModel {
    private final CategoryDao categoryDao;
    private final LiveData<List<Category>> categories;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public CategoryViewModel(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
        this.categories = categoryDao.getAllCategoriesLiveData();
    }

    public LiveData<List<Category>> getCategories() {
        return categories;
    }

    public void insert(Category category) {
        executorService.execute(() -> {
            try {
                categoryDao.insert(category);
            } catch (Exception e) {
                // Handle the error (e.g., log it)
            }
        });
    }

    public void update(Category category) {
        executorService.execute(() -> {
            try {
                categoryDao.update(category);
            } catch (Exception e) {
                // Handle the error
            }
        });
    }

    public void delete(Category category) {
        executorService.execute(() -> {
            try {
                categoryDao.delete(category);
            } catch (Exception e) {
                // Handle the error
            }
        });
    }
}
