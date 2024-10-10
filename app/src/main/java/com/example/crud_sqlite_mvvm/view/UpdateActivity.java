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
    private EditText editUsername;
    private EditText editAddress;
    private Button buttonUpdateUser;


    private User user;
    private UserViewModel userViewModel;  // Thêm UserViewModel

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update);

        // Khởi tạo các view
        editUsername = findViewById(R.id.edit_username);
        editAddress = findViewById(R.id.edit_address);
        buttonUpdateUser = findViewById(R.id.btnUpdateUser);

        // Khởi tạo ViewModel
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        // Nhận dữ liệu từ Intent
        user = (User) getIntent().getExtras().get("object_user");
        if (user != null) {
            editUsername.setText(user.getUsername());
            editAddress.setText(user.getAddress());
        }

        // Xử lý sự kiện nhấn nút Cập nhật
        buttonUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUser();
            }
        });
    }

    private void updateUser() {
        String username = editUsername.getText().toString().trim();
        String address = editAddress.getText().toString().trim();

        // Kiểm tra dữ liệu nhập vào
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(address)) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Cập nhật thông tin user
        user.setUsername(username);
        user.setAddress(address);

        // Sử dụng ViewModel để cập nhật user trong cơ sở dữ liệu
        userViewModel.updateUser(user);

        Toast.makeText(this, "User updated successfully", Toast.LENGTH_SHORT).show();

        // Trả về kết quả cho MainActivity và đóng UpdateActivity
        Intent intentResult = new Intent();
        setResult(Activity.RESULT_OK, intentResult);
        finish();
    }
}