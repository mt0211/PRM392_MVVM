package com.example.crud_sqlite_mvvm.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "field", foreignKeys = @ForeignKey(entity = Category.class,
        parentColumns = "categoryId",
        childColumns = "category_id",
        onDelete = ForeignKey.CASCADE))
public class Field {
    @PrimaryKey(autoGenerate = true)
    private int fieldId;
    private int freeSlots;
    private String startTime;
    private String endTime;
    private int duration;
    private String location;
    private String description;
    private String fieldName;
    @ColumnInfo(name = "category_id")
    private int categoryId;

    // Thêm thuộc tính để lưu trữ ID tài nguyên hình ảnh
    private int imageResId;

    public Field() {
    }

    // Constructor
    public Field(int freeSlots, String startTime, String endTime, int duration, String location, String description, int categoryId, int imageResId, String fieldName) {
        this.freeSlots = freeSlots;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.location = location;
        this.description = description;
        this.categoryId = categoryId;
        this.imageResId = imageResId;
        this.fieldName = fieldName;
    }

    // Getter và Setter cho các thuộc tính
    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public int getFreeSlots() {
        return freeSlots;
    }

    public void setFreeSlots(int freeSlots) {
        this.freeSlots = freeSlots;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    // Getter và Setter cho thuộc tính imageResId
    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}
