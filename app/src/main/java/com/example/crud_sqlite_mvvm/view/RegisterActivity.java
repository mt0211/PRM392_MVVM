package com.example.crud_sqlite_mvvm.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crud_sqlite_mvvm.R;
import com.example.crud_sqlite_mvvm.database.AppDatabase;
import com.example.crud_sqlite_mvvm.database.UserDAO;
import com.example.crud_sqlite_mvvm.database.UserDatabase;
import com.example.crud_sqlite_mvvm.model.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtUsername, edtEmail,edtPassword;
    private Button btnRegister;
    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register); // Đảm bảo layout chính xác

        edtUsername = findViewById(R.id.editTexUsername); // ID của EditText Username
        edtEmail = findViewById(R.id.editTextEmail);
        edtPassword = findViewById(R.id.editTextPassword);

        btnRegister = findViewById(R.id.button3); // ID của Button Register

        userDAO = AppDatabase.getInstance(this).userDAO();

        btnRegister.setOnClickListener(v -> {
            String username = edtUsername.getText().toString();
            String email = edtEmail.getText().toString();
            String password = edtPassword.getText().toString();

            // Kiểm tra xem username đã tồn tại chưa
            new Thread(() -> {
                if (userDAO.checkUser(username).isEmpty()) {
                    User user = new User(username, email, password);
                    userDAO.insertUser(user);
                    Log.d("RegisterActivity", "User registered: " + username);

                    runOnUiThread(() -> {
                        Toast.makeText(this, "Register successful!", Toast.LENGTH_SHORT).show();
                        // Chuyển đến LoginActivity
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish(); // Kết thúc RegisterActivity
                    });
                } else {
                    runOnUiThread(() -> {
                        Toast.makeText(this, "Username already exists!", Toast.LENGTH_SHORT).show();
                    });
                }
            }).start();
        });
    }

    public void signUp(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}
