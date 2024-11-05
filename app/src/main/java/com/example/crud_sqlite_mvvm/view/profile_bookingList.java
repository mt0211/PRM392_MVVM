package com.example.crud_sqlite_mvvm.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ViewFlipper;
import com.example.crud_sqlite_mvvm.R;

public class profile_bookingList extends AppCompatActivity {

    private ViewFlipper viewFlipper;
    private static final String TAG = "ProfileBookingList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_bookinglist);
        ToolbarHelper.setupToolbarClicks(this);

        viewFlipper = findViewById(R.id.viewFlipper);
        Button button1 = findViewById(R.id.buttonLayout1);
        Button button2 = findViewById(R.id.buttonLayout2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Button 1 clicked"); // Log để kiểm tra
                viewFlipper.setDisplayedChild(0); // Hiển thị layout 1
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Button 2 clicked"); // Log để kiểm tra
                viewFlipper.setDisplayedChild(1); // Hiển thị layout 2
            }
        });
    }
}
