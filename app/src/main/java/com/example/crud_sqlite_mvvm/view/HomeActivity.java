package com.example.crud_sqlite_mvvm.view;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.crud_sqlite_mvvm.R;
import com.example.crud_sqlite_mvvm.database.AppDatabase;
import com.example.crud_sqlite_mvvm.database.CategoryDao;
import com.example.crud_sqlite_mvvm.database.FieldDao;
import com.example.crud_sqlite_mvvm.model.Category;
import com.example.crud_sqlite_mvvm.model.Field;
import com.example.crud_sqlite_mvvm.model.User;
import com.example.crud_sqlite_mvvm.viewmodel.CategoryViewModel;
import com.example.crud_sqlite_mvvm.viewmodel.CategoryViewModelFactory;
import com.example.crud_sqlite_mvvm.viewmodel.FieldViewModel;
import com.example.crud_sqlite_mvvm.viewmodel.FieldViewModelFactory;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        ToolbarHelper.setupToolbarClicks(this);
        user = (User) getIntent().getSerializableExtra("object_user");
        // Tìm ImageButton
        ImageButton buttonUser = findViewById(R.id.buttonUser);

        // Thiết lập sự kiện nhấp chuột
        buttonUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khởi động UpdateUserProfileActivity
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                intent.putExtra("object_user", user);
                startActivity(intent);
            }
        });
    }
}



