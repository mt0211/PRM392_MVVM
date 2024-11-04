package com.example.crud_sqlite_mvvm.view;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud_sqlite_mvvm.R;
import com.example.crud_sqlite_mvvm.database.AppDatabase;
import com.example.crud_sqlite_mvvm.database.FieldDao;
import com.example.crud_sqlite_mvvm.database.CategoryDao;
import com.example.crud_sqlite_mvvm.model.Category;
import com.example.crud_sqlite_mvvm.model.Field;
import com.example.crud_sqlite_mvvm.viewmodel.CategoryViewModel;
import com.example.crud_sqlite_mvvm.viewmodel.CategoryViewModelFactory;
import com.example.crud_sqlite_mvvm.viewmodel.FieldViewModel;
import com.example.crud_sqlite_mvvm.viewmodel.FieldViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class FieldListActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCategories;
    private RecyclerView recyclerViewFields;
    private CategoryAdapter categoryAdapter;
    private FieldAdapter fieldAdapter;
    private CategoryViewModel categoryViewModel;
    private FieldViewModel fieldViewModel;
    private List<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.field_list_activity);

        // Khởi tạo database
        AppDatabase db = AppDatabase.getInstance(this);
        Log.d("FieldListActivity", "Database initialized");

        // Khởi tạo ViewModels
        categoryViewModel = new ViewModelProvider(this, new CategoryViewModelFactory(db.categoryDao())).get(CategoryViewModel.class);
        fieldViewModel = new ViewModelProvider(this, new FieldViewModelFactory(db.fieldDao())).get(FieldViewModel.class);

        // Set up RecyclerView for Categories
        recyclerViewCategories = findViewById(R.id.recyclerViewCategories);
        recyclerViewCategories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        categoryAdapter = new CategoryAdapter(this, new ArrayList<>());
        recyclerViewCategories.setAdapter(categoryAdapter);

        // Quan sát LiveData cho categories
        categoryViewModel.getCategories().observe(this, categories -> {
            if (categories != null) {
                Log.d("FieldListActivity", "Categories observed: " + categories.size());
                for (Category category : categories) {
                    Log.d("FieldListActivity", "Category ID: " + category.getCategoryId() + ", Name: " + category.getName());
                }
                categoryAdapter.setCategories(categories);
            } else {
                Log.d("FieldListActivity", "Categories data is null");
            }

            if (categories.isEmpty()) {
                addSampleCategories();
            }
        });

        // Set up RecyclerView for Fields
        recyclerViewFields = findViewById(R.id.recyclerViewFields);
        recyclerViewFields.setLayoutManager(new LinearLayoutManager(this));
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        recyclerViewFields.addItemDecoration(new SpacingItemDecoration(spacingInPixels));
        fieldAdapter = new FieldAdapter(this, new ArrayList<>(), db);
        recyclerViewFields.setAdapter(fieldAdapter);

        // Quan sát LiveData cho fields
        fieldViewModel.getFields().observe(this, fields -> {
            if (fields != null && !fields.isEmpty()) {
                Log.d("FieldListActivity", "Fields observed: " + fields.size());
                for (Field field : fields) {
                    Log.d("FieldListActivity", "Field ID: " + field.getFieldId() + ", Location: " + field.getLocation() + ", Time: " + field.getStartTime() + " - " + field.getEndTime());
                }
                fieldAdapter.setFields(fields);
            } else {
                Log.d("FieldListActivity", "No fields available or fields data is null.");
                // Call addSampleFields only if categories are available
                if (categories != null && !categories.isEmpty()) {
                    long categoryId1 = categories.get(0).getCategoryId();
                    long categoryId2 = categories.size() > 1 ? categories.get(1).getCategoryId() : categoryId1; // Use the same ID if there's only one category
                    addSampleFields(categoryId1, categoryId2);
                }
            }
        });
    }

    // Thêm danh mục mẫu vào cơ sở dữ liệu
    private void addSampleCategories() {
        new Thread(() -> {
            CategoryDao categoryDao = AppDatabase.getInstance(this).categoryDao();
            long newCategoryId1 = categoryDao.insert(new Category("Football", R.drawable.ball));
            long newCategoryId2 = categoryDao.insert(new Category("Tennis", R.drawable.tennis));
            long newCategoryId3 = categoryDao.insert(new Category("Basketball", R.drawable.basketball1));
            Log.d("FieldListActivity", "Categories have been inserted.");
            addSampleFields(newCategoryId1, newCategoryId2); // Insert sample fields with the new category IDs
        }).start();
    }

    // Thêm trường mẫu vào cơ sở dữ liệu
    private void addSampleFields(long categoryId1, long categoryId2) {
        new Thread(() -> {
            FieldDao fieldDao = AppDatabase.getInstance(this).fieldDao();
            // Sử dụng categoryId để tạo các Field mới
            Field field1 = new Field(10, "09:00", "11:00", 90, "Location 1", "Description 1", (int) categoryId1);
            Field field2 = new Field(10, "08:00", "14:00", 60, "Location 2", "Description 2", (int) categoryId2);
            fieldDao.insert(field1);
            fieldDao.insert(field2);
            Log.d("FieldListActivity", "Fields have been inserted.");
        }).start();
    }
}
