package com.example.crud_sqlite_mvvm.view;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud_sqlite_mvvm.R;
import com.example.crud_sqlite_mvvm.database.AppDatabase;
import com.example.crud_sqlite_mvvm.database.CategoryDao;
import com.example.crud_sqlite_mvvm.database.FieldDao;
import com.example.crud_sqlite_mvvm.viewmodel.CategoryViewModel;
import com.example.crud_sqlite_mvvm.viewmodel.CategoryViewModelFactory;
import com.example.crud_sqlite_mvvm.viewmodel.FieldViewModel;
import com.example.crud_sqlite_mvvm.viewmodel.FieldViewModelFactory;

public class FieldListActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCategories;
    private RecyclerView recyclerViewFields;
    private CategoryAdapter categoryAdapter;
    private FieldAdapter fieldAdapter;
    private CategoryViewModel categoryViewModel;
    private FieldViewModel fieldViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.field_list_activity);

        // Initialize the database
        AppDatabase db = AppDatabase.getInstance(this);
        Log.d("FieldListActivity", "Database initialized");

        // Initialize ViewModels
        categoryViewModel = new ViewModelProvider(this, new CategoryViewModelFactory(db.categoryDao())).get(CategoryViewModel.class);
        fieldViewModel = new ViewModelProvider(this, new FieldViewModelFactory(db.fieldDao())).get(FieldViewModel.class);

        // Set up RecyclerView for Categories
        recyclerViewCategories = findViewById(R.id.recyclerViewCategories);
        recyclerViewCategories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Observe LiveData for categories
        categoryViewModel.getCategories().observe(this, categories -> {
            if (categories != null) {
                Log.d("FieldListActivity", "Categories observed: " + categories.size());
                categoryAdapter = new CategoryAdapter(FieldListActivity.this, categories);
                recyclerViewCategories.setAdapter(categoryAdapter);
            } else {
                Log.d("FieldListActivity", "Categories data is null");
            }
        });

//        // Set up RecyclerView for Fields
//        recyclerViewFields = findViewById(R.id.recyclerViewFields);
//        recyclerViewFields.setLayoutManager(new LinearLayoutManager(this));
//        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
//        recyclerViewFields.addItemDecoration(new SpacingItemDecoration(spacingInPixels));
//
//        // Observe LiveData for fields
//        fieldViewModel.getFields().observe(this, fields -> {
//            if (fields != null && !fields.isEmpty()) {
//                Log.d("FieldListActivity", "Fields observed: " + fields.size());
//                fieldAdapter = new FieldAdapter(FieldListActivity.this, fields, db);
//                recyclerViewFields.setAdapter(fieldAdapter);
//            } else {
//                Log.d("FieldListActivity", "No fields available or fields data is null.");
//            }
//        });
    }
}