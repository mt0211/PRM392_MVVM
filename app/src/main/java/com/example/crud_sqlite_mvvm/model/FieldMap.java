package com.example.crud_sqlite_mvvm.model;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;

public class FieldMap {
    private LatLng latLng;
    private String name;
    private BitmapDescriptor markerIcon;
    private String type;

    // Cập nhật constructor để nhận loại sân bóng
    public FieldMap(LatLng latLng, String name, String type, BitmapDescriptor markerIcon) {
        this.latLng = latLng;
        this.name = name;
        this.type = type; // Khởi tạo loại sân bóng
        this.markerIcon = markerIcon;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public String getName() {
        return name;
    }

    public BitmapDescriptor getMarkerIcon() {
        return markerIcon;
    }

    // Thêm phương thức để lấy loại sân bóng
    public String getType() {
        return type;
    }
}
