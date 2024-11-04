package com.example.crud_sqlite_mvvm.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "category")
public class Category {
    @PrimaryKey(autoGenerate = true)
    private int categoryId;
    private String name;
    private int imageResId;

    // Constructor
    public Category(String name, int imageResId) {
        this.name = name;
        this.imageResId = imageResId;
    }

    // Getters and Setters
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageResId() {
        return imageResId; // Trả về ID hình ảnh
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}
