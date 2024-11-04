package com.example.crud_sqlite_mvvm.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crud_sqlite_mvvm.R;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_maps);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.map, new MapsFragment())
                    .commit();
        }
    }
}