package com.example.crud_sqlite_mvvm.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.crud_sqlite_mvvm.model.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM user")
    List<User> getListUser();

    @Query("SELECT * FROM user WHERE username = :username")
    List<User> checkUser(String username);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("DELETE FROM user")
    void deleteAllUsers();

    @Query("SELECT * FROM user WHERE username = :username AND password = :password LIMIT 1")
    User login(String username, String password);

    @Query("SELECT * FROM user WHERE id = :id LIMIT 1")
    User getUserById(int id);
}
