package com.example.crud_sqlite_mvvm.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;
import androidx.appcompat.app.AppCompatActivity;
import com.example.crud_sqlite_mvvm.R;
import com.example.crud_sqlite_mvvm.model.User;

public class ProfileActivity extends AppCompatActivity {
    private ViewFlipper viewFlipper;
    private User user; // Declare user variable
    private static final String TAG = "ProfileBookingList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_bookinglist);
        ToolbarHelper.setupToolbarClicks(this);

        // Initialize ViewFlipper
        viewFlipper = findViewById(R.id.viewFlipper);

        // Initialize buttons and set click listeners
        Button button1 = findViewById(R.id.buttonLayout1);
        Button button2 = findViewById(R.id.buttonLayout2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Button 1 clicked");
                viewFlipper.setDisplayedChild(0);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Button 2 clicked");
                viewFlipper.setDisplayedChild(1);
            }
        });

        // Retrieve user data from Intent
        user = (User) getIntent().getSerializableExtra("object_user");

        // Display user data
        if (user != null) {
            TextView profileName = findViewById(R.id.profilename);
            TextView profileMail = findViewById(R.id.profilemail);
            TextView profilePhone = findViewById(R.id.profilephone);
            TextView profileAddress = findViewById(R.id.profileaddress);
            TextView profileBirth = findViewById(R.id.profiledate);

            profileName.setText(user.getUsername());
            profileMail.setText(user.getEmail());
            profilePhone.setText(user.getPhone());
            profileAddress.setText(user.getAddress());
            profileBirth.setText(user.getDateOfBirth());
        } else {
            Log.e(TAG, "User data is null");
        }
        // Button to navigate to update profile
        ImageView btnEditProfile = findViewById(R.id.profileEdit); // Add this button in your layout
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start UpdateProfileActivity
                Intent intent = new Intent(ProfileActivity.this, UpdateActivity.class);
                intent.putExtra("object_user", user);
                startActivityForResult(intent, 1); // Start activity for result
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Retrieve updated user data
            user = (User) data.getSerializableExtra("updated_user");
            // Optionally, update the UI with the new user data
            // For example, refresh the profile display
            if (user != null) {
                // Update profile information after the user has updated their data
                TextView profileName = findViewById(R.id.profilename);
                TextView profileMail = findViewById(R.id.profilemail);
                TextView profilePhone = findViewById(R.id.profilephone);
                TextView profileAddress = findViewById(R.id.profileaddress);
                TextView profileBirth = findViewById(R.id.profiledate);

                profileName.setText(user.getUsername());
                profileMail.setText(user.getEmail());
                profilePhone.setText(user.getPhone());
                profileAddress.setText(user.getAddress());
                profileBirth.setText(user.getDateOfBirth());
            }
        }
    }
}
