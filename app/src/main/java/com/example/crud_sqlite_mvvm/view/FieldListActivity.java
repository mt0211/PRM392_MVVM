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
    private List<Category> categories = new ArrayList<>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.field_list_activity);
        ToolbarHelper.setupToolbarClicks(this);

        AppDatabase db = AppDatabase.getInstance(this);
        Log.d("FieldListActivity", "Database initialized");

        // Initialize ViewModels
        categoryViewModel = new ViewModelProvider(this, new CategoryViewModelFactory(db.categoryDao())).get(CategoryViewModel.class);
        fieldViewModel = new ViewModelProvider(this, new FieldViewModelFactory(db.fieldDao())).get(FieldViewModel.class);

        // Set up RecyclerView for Categories
        recyclerViewCategories = findViewById(R.id.recyclerViewCategories);
        recyclerViewCategories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        categories = new ArrayList<>(); // Initialize categories list
        categoryAdapter = new CategoryAdapter(this, categories);
        categoryAdapter.setOnCategoryClickListener(this::onCategoryClick);
        recyclerViewCategories.setAdapter(categoryAdapter);

        // Observe categories LiveData
        categoryViewModel.getCategories().observe(this, observedCategories -> {
            if (observedCategories != null) {
                categories.clear();
                categories.addAll(observedCategories);
                categoryAdapter.notifyDataSetChanged();
                Log.d("FieldListActivity", "Categories observed: " + categories.size());

                if (categories.isEmpty()) {
                    addSampleCategories();
                }
            } else {
                Log.d("FieldListActivity", "Categories data is null");
            }
        });

        // Setup RecyclerView for Fields
        recyclerViewFields = findViewById(R.id.recyclerViewFields);
        recyclerViewFields.setLayoutManager(new LinearLayoutManager(this));
        fieldAdapter = new FieldAdapter(this, new ArrayList<>(), db);
        recyclerViewFields.setAdapter(fieldAdapter);

        // Observe fields LiveData
        fieldViewModel.getFields().observe(this, fields -> {
            if (fields != null && !fields.isEmpty()) {
                fieldAdapter.setFields(fields);
                Log.d("FieldListActivity", "Fields observed: " + fields.size());
            } else {
                Log.d("FieldListActivity", "No fields available or fields data is null.");
                if (!categories.isEmpty()) {
                    addSampleFields(categories.get(0).getCategoryId(), categories.get(1).getCategoryId(), categories.get(2).getCategoryId(), categories.get(3).getCategoryId());
                }
            }
        });
    }

    private void onCategoryClick(Category category) {
        Log.d("FieldListActivity", "Danh mục được bấm: " + category.getName());
        fieldViewModel.getFieldsByCategory(category.getCategoryId()).observe(this, fields -> {
            if (fields != null && !fields.isEmpty()) {
                fieldAdapter.setFields(fields);
                Log.d("FieldListActivity", "Số trường đã được lọc: " + fields.size());
            } else {
                Log.d("FieldListActivity", "Không có trường nào cho danh mục này.");
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
            long newCategoryId4 = categoryDao.insert(new Category("PickleBall", R.drawable.pickle));
            Log.d("FieldListActivity", "Categories have been inserted.");
            addSampleFields(newCategoryId1, newCategoryId2,newCategoryId3, newCategoryId4); // Insert sample fields with the new category IDs
        }).start();
    }

    // Thêm trường mẫu vào cơ sở dữ liệu
    private void addSampleFields(long categoryId1, long categoryId2, long newCategoryId3, long newCategoryId4) {
        new Thread(() -> {
            FieldDao fieldDao = AppDatabase.getInstance(this).fieldDao();
            // Sử dụng categoryId để tạo các Field mới
            Field field1 = new Field(5, "09:00", "11:00", 90, "HCM City", "You can have 5v5 or 7v7 match in this field", (int) categoryId1,R.drawable.pic1, "FootBall Field Club");
            Field field2 = new Field(10, "08:00", "14:00", 60, "Can Tho", "Hope you enjoy it", (int) categoryId2,R.drawable.pic2, "RoYal Field");
            Field field3 = new Field(3, "09:00", "15:00", 180, "Kien Giang", "Hope you enjoy it", (int) newCategoryId3,R.drawable.pic3,"ABC Stadium");
            Field field4 = new Field(15, "08:00", "14:00", 60, "Ha Noi", "Hope you enjoy it", (int) newCategoryId4,R.drawable.pic1, "Field KNIGHT");
            fieldDao.insert(field1);
            fieldDao.insert(field2);
            fieldDao.insert(field3);
            fieldDao.insert(field4);
            Log.d("FieldListActivity", "Fields have been inserted.");
        }).start();
    }
}
