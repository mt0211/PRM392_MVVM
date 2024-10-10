package com.example.crud_sqlite_mvvm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.crud_sqlite_mvvm.database.UserDatabase;
import com.example.crud_sqlite_mvvm.model.User;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private UserDatabase userDatabase;
    private MutableLiveData<List<User>> userListLiveData = new MutableLiveData<>();

    public UserViewModel(@NonNull Application application) {
        super(application);
        userDatabase = UserDatabase.getInstance(application);
        loadData();
    }
    public void loadData() {
        List<User> userList = userDatabase.userDAO().getListUser();
        userListLiveData.setValue(userList);
    }
    public LiveData<List<User>> getUserListLiveData() {
        return userListLiveData;
    }

    // Thêm user và cập nhật lại dữ liệu
    public void addUser(User user) {
        if (!isUserExist(user)) {
            userDatabase.userDAO().insertUser(user);
            loadData();
        }
    }

    // Kiểm tra xem user đã tồn tại hay chưa
    public boolean isUserExist(User user) {
        List<User> list = userDatabase.userDAO().checkUser(user.getUsername());
        return list != null && !list.isEmpty();
    }

    // Xóa một user
    public void deleteUser(User user) {
        userDatabase.userDAO().deleteUser(user);
        loadData();
    }

    // Cập nhật user
    public void updateUser(User user) {
        userDatabase.userDAO().updateUser(user);
        loadData();
    }
}
