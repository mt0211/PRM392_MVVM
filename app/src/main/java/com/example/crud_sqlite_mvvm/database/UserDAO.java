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

    @Query("select * from user")
    List<User> getListUser();

    @Query("Select * from user where username = :username")
    List<User> checkUser(String username);

   @Update
    void updateUser(User user);

   @Delete
   void deleteUser(User user);

   @Query("Delete from user")
   void deleteAllUser();
}
