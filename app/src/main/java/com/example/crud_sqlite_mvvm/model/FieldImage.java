package com.example.crud_sqlite_mvvm.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "field_images")
public class FieldImage {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int fieldId;
    private String imagePath;

    public FieldImage(int fieldId, String imagePath) {
        this.fieldId = fieldId;
        this.imagePath = imagePath;
    }
}
