package com.example.crud_sqlite_mvvm.view;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import com.example.crud_sqlite_mvvm.R;
import com.example.crud_sqlite_mvvm.view.FieldListActivity;
import com.example.crud_sqlite_mvvm.view.MapActivity;
//import com.example.crud_sqlite_mvvm.view.HomeActivity;
//import com.example.crud_sqlite_mvvm.view.ChatActivity;
//import com.example.crud_sqlite_mvvm.view.UserActivity;

public class ToolbarHelper {
    public static void setupToolbarClicks(AppCompatActivity activity) {
        // Xử lý sự kiện click cho button Map
        activity.findViewById(R.id.buttonMap).setOnClickListener(v -> {
            if (!(activity instanceof MapActivity)) {
                activity.startActivity(new Intent(activity, MapActivity.class));
            }
        });

        // Xử lý sự kiện click cho button List
        activity.findViewById(R.id.buttonList).setOnClickListener(v -> {
            if (!(activity instanceof FieldListActivity)) {
                activity.startActivity(new Intent(activity, FieldListActivity.class));
            }
        });

        // Uncomment and add similar checks for other buttons if needed

        // Xử lý sự kiện click cho button Home
//        activity.findViewById(R.id.buttonHome).setOnClickListener(v -> {
//            if (!(activity instanceof HomeActivity)) {
//                activity.startActivity(new Intent(activity, HomeActivity.class));
//            }
//        });
//
//        // Xử lý sự kiện click cho button Chat
//        activity.findViewById(R.id.buttonChat).setOnClickListener(v -> {
//            if (!(activity instanceof ChatActivity)) {
//                activity.startActivity(new Intent(activity, ChatActivity.class));
//            }
//        });

        // Xử lý sự kiện click cho button User
        activity.findViewById(R.id.buttonUser).setOnClickListener(v -> {
            if (!(activity instanceof ProfileActivity)) {
                activity.startActivity(new Intent(activity, ProfileActivity.class));
            }
        });

    }
}
