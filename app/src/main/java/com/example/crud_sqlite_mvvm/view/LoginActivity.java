package com.example.crud_sqlite_mvvm.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.crud_sqlite_mvvm.R;
import com.example.crud_sqlite_mvvm.database.AppDatabase;
import com.example.crud_sqlite_mvvm.model.User;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = AppDatabase.getInstance(this); // Initialize the database

        etUsername = findViewById(R.id.etUsername); // Updated to username
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        addSampleData();
        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                Toast.makeText(LoginActivity.this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
            } else {
                loginUser(username, password);
            }
        });
    }

    private void loginUser(String username, String password) {
        new Thread(() -> {
            User user = db.userDAO().login(username, password);
            runOnUiThread(() -> {
                if (user != null) {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtra("object_user", user);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            });
        }).start();
    }

    private void addSampleData() {
        new Thread(() -> {
            // Kiểm tra xem có người dùng nào không
            List<User> userList = db.userDAO().getListUser();
            if (userList.isEmpty()) {
                // Nếu không có người dùng, thêm người dùng mẫu
                User user1 = new User("hungloipro123", "Address 1", "user1@example.com", "123", "0969091310", "2003-09-10");
                User user2 = new User("user2", "Address 2", "user2@example.com", "password2", "0987654321", "1992-02-02");
                User user3 = new User("user3", "Address 3", "user3@example.com", "password3", "0912345678", "1993-03-03");

                db.userDAO().insertUser(user1);
                db.userDAO().insertUser(user2);
                db.userDAO().insertUser(user3);
            }
        }).start();
    }

    public void signUp(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
