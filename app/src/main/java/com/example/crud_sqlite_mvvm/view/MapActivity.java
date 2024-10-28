package com.example.crud_sqlite_mvvm.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.crud_sqlite_mvvm.R;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_maps); // Layout cho MapActivity

        // Kiểm tra nếu savedInstanceState là null, có nghĩa là lần đầu khởi động
        if (savedInstanceState == null) {
            // Thay thế fragment container bằng MapsFragment
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.map, new MapsFragment())
                    .commit();
        }
    }
}
