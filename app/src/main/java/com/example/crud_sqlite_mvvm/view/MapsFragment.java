package com.example.crud_sqlite_mvvm.view;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.crud_sqlite_mvvm.R;
import com.example.crud_sqlite_mvvm.model.FieldMap;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsFragment extends Fragment {

    private GoogleMap googleMap;
    private boolean isFieldsInitialized = false;
    private static final LatLng DEFAULT_LOCATION = new LatLng(10.012462, 105.732496);
    private static final float DEFAULT_ZOOM_LEVEL = 15f;
    private View rootView;
    private List<FieldMap> Fields;
    private Location fptUniversityLocation;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap map) {
            googleMap = map;
            fptUniversityLocation = new Location("FPT University");
            fptUniversityLocation.setLatitude(DEFAULT_LOCATION.latitude);
            fptUniversityLocation.setLongitude(DEFAULT_LOCATION.longitude);
            googleMap.addMarker(new MarkerOptions().position(DEFAULT_LOCATION).title("Trường Đại Học FPT Cần Thơ"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DEFAULT_LOCATION, DEFAULT_ZOOM_LEVEL));

            if (!isFieldsInitialized) {
                initializeFields();
                addFields();
                isFieldsInitialized = true;
            }

            googleMap.setOnMarkerClickListener(marker -> {
                LatLng markerPosition = marker.getPosition();
                Location fieldLocation = new Location("Field Location");
                fieldLocation.setLatitude(markerPosition.latitude);
                fieldLocation.setLongitude(markerPosition.longitude);
                float distanceInMeters = fptUniversityLocation.distanceTo(fieldLocation);
                float distanceInKilometers = distanceInMeters / 1000;
                Toast.makeText(getContext(), "Khoảng cách đến FPT University: " + distanceInKilometers + " km", Toast.LENGTH_SHORT).show();
                return false;
            });
        }
    };

    private BitmapDescriptor resizeDrawable(int drawableId, int width, int height) {
        BitmapDrawable bitmapDrawable = (BitmapDrawable ) getResources().getDrawable(drawableId);
        Bitmap bitmap = bitmapDrawable.getBitmap();
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);
        return BitmapDescriptorFactory.fromBitmap(resizedBitmap);
    }

    private void initializeFields() {
        Fields = new ArrayList<>();
        Fields.add(new FieldMap(new LatLng(10.02462, 105.732496), "Field 1", "Football", resizeDrawable(R.drawable.soccer_field, 100, 100)));
        Fields.add(new FieldMap(new LatLng(10.016462, 105.735496), "Field 2", "Football", resizeDrawable(R.drawable.soccer_field, 100, 100)));
        Fields.add(new FieldMap(new LatLng(10.018462, 105.735496), "Field 3", "Basketball", resizeDrawable(R.drawable.basketball, 100, 100)));
        Fields.add(new FieldMap(new LatLng(10.017000, 105.738496), "Field 4", "Tennis", resizeDrawable(R.drawable.tennis, 100, 100)));
        Fields.add(new FieldMap(new LatLng(10.019462, 105.738496), "Field 5", "PickleBall", resizeDrawable(R.drawable.racket, 100, 100)));
    }

    private void addFields() {
        for (FieldMap field : Fields) {
            googleMap.addMarker(new MarkerOptions()
                    .position(field.getLatLng())
                    .title(field.getName())
                    .icon(field.getMarkerIcon()));
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.activity_map, container, false);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Tìm fragment bản đồ
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }

        setupFieldButtons(rootView);
    }

    private void setupFieldButtons(View view) {
        LinearLayout buttonFootball = view.findViewById(R.id.Football);
        LinearLayout buttonBasketball = view.findViewById(R.id.Basketball);
        LinearLayout buttonTennis = view.findViewById(R.id.Tennis);
        LinearLayout buttonPickleBall = view.findViewById(R.id.PickleBall);

        buttonFootball.setOnClickListener(v -> filterMarkers("Football"));
        buttonBasketball.setOnClickListener(v -> filterMarkers("Basketball"));
        buttonTennis.setOnClickListener(v -> filterMarkers("Tennis"));
        buttonPickleBall.setOnClickListener(v -> filterMarkers("PickleBall"));
    }

    private void filterMarkers(String selectedType) {
        Log.d("MapsFragment", "filterMarkers called with type: " + selectedType);
        googleMap.clear();

        googleMap.addMarker(new MarkerOptions().position(DEFAULT_LOCATION).title("Trường Đại Học FPT Cần Thơ"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DEFAULT_LOCATION, DEFAULT_ZOOM_LEVEL));

        for (FieldMap field : Fields) {
            if (field.getType().equals(selectedType)) {
                googleMap.addMarker(new MarkerOptions()
                        .position(field.getLatLng())
                        .title(field.getName())
                        .icon(field.getMarkerIcon()));
            }
        }
    }
}