package com.example.crud_sqlite_mvvm.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud_sqlite_mvvm.R;
import com.example.crud_sqlite_mvvm.database.UserDatabase;
import com.example.crud_sqlite_mvvm.model.User;
import com.example.crud_sqlite_mvvm.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 10;
    private EditText editUsername;
    private EditText editAddress;
    private RecyclerView recyclerViewUser;
    private Button button;
    private UserAdapter userAdapter;
    private List<User> listUser;
    private UserViewModel userViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        initUI();

        userAdapter = new UserAdapter(new UserAdapter.IClickItemUser() {
            @Override
            public void updateUser(User user) {
                clickUpdateUser(user);
            }

            @Override
            public void DeleteUser(User user) {
                clickDeleteUser(user);
            }
        });

        recyclerViewUser.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewUser.setAdapter(userAdapter);

        // Sử dụng ViewModel
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        // Lắng nghe thay đổi dữ liệu từ ViewModel
        userViewModel.getUserListLiveData().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                userAdapter.setData(users);
            }
        });

        // Thêm user
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser();
            }
        });

    }

    private void addUser() {
        String username = editUsername.getText().toString().trim();
        String address = editAddress.getText().toString().trim();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(address)) {
            return;
        }
        User user = new User(username, address);

        //check exist
        if (userViewModel.isUserExist(user)) {
            Toast.makeText(this, "User already exists", Toast.LENGTH_SHORT).show();
        } else {
            userViewModel.addUser(user);
            Toast.makeText(this, "User added successfully", Toast.LENGTH_SHORT).show();
        }

        editUsername.setText("");
        editAddress.setText("");
        hideSoftkeyboard();

        userViewModel.loadData();

    }

    public void hideSoftkeyboard() {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    private void initUI() {
        editUsername = findViewById(R.id.edit_username);
        editAddress = findViewById(R.id.edit_address);
        recyclerViewUser = findViewById(R.id.recycleviewUser);
        button = findViewById(R.id.btnAdd);
    }

    private void clickUpdateUser(User user) {
        Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_user", user);
        intent.putExtras(bundle);
        startActivityForResult(intent, REQUEST_CODE);
    }
    private void clickDeleteUser(User user) {
        new AlertDialog.Builder(this)
                .setTitle("Confirm delete user")
                .setMessage("Are you sure?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        userViewModel.deleteUser(user);
                        Toast.makeText(MainActivity.this, "User deleted", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("NO", null)
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            userViewModel.loadData();
        }
    }
}