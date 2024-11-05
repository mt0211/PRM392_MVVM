package com.example.crud_sqlite_mvvm.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.crud_sqlite_mvvm.R;
import com.example.crud_sqlite_mvvm.database.UserDatabase;
import com.example.crud_sqlite_mvvm.model.User;
import com.example.crud_sqlite_mvvm.viewmodel.UserViewModel;

public class UpdateActivity extends AppCompatActivity {
    private EditText editEmail;
    private EditText editPhone;
    private EditText editAddress;
    private EditText editBirthDate;
    private Button btnUpdateUser;
    private User user; // Variable to hold user data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update); // Make sure you create this layout

        editEmail = findViewById(R.id.edit_email);
        editPhone = findViewById(R.id.edit_phone);
        editAddress = findViewById(R.id.edit_address);
        editBirthDate = findViewById(R.id.edit_birth_date);
        btnUpdateUser = findViewById(R.id.btnUpdateUser);

        // Retrieve user data from Intent
        user = (User) getIntent().getSerializableExtra("object_user");

        // Populate fields with existing user data
        if (user != null) {

            editEmail.setText(user.getEmail()); // Assuming the User model has this method
            editPhone.setText(user.getPhone()); // Assuming the User model has this method
            editAddress.setText(user.getAddress());
            editBirthDate.setText(user.getDateOfBirth()); // Assuming the User model has this method
        }

        // Set click listener for update button
        btnUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update user object with new data
                if (TextUtils.isEmpty(editEmail.getText().toString())) {
                    Toast.makeText(UpdateActivity.this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
                    return; // Exit the method if email is empty
                }

                if (TextUtils.isEmpty(editPhone.getText().toString())) {
                    Toast.makeText(UpdateActivity.this, "Phone cannot be empty", Toast.LENGTH_SHORT).show();
                    return; // Exit the method if phone is empty
                }

                if (TextUtils.isEmpty(editAddress.getText().toString())) {
                    Toast.makeText(UpdateActivity.this, "Address cannot be empty", Toast.LENGTH_SHORT).show();
                    return; // Exit the method if address is empty
                }

                if (TextUtils.isEmpty(editBirthDate.getText().toString())) {
                    Toast.makeText(UpdateActivity.this, "Birthdate cannot be empty", Toast.LENGTH_SHORT).show();
                    return; // Exit the method if birthdate is empty
                }

                user.setEmail(editEmail.getText().toString()); // Set email
                user.setPhone(editPhone.getText().toString()); // Set phone
                user.setAddress(editAddress.getText().toString());
                user.setDateOfBirth(editBirthDate.getText().toString()); // Set birth date

                // Prepare intent to return updated user
                Intent resultIntent = new Intent();
                resultIntent.putExtra("updated_user", user);
                setResult(RESULT_OK, resultIntent);
                finish(); // Close this activity
            }
        });
    }
}