package com.example.crud_sqlite_mvvm.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "user")
public class User implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String username;
    private String address;
    private String email;
    private String password;
    private String phone;
    private String dateOfBirth;

    public User(String username, String address, String email, String password, String phone, String dateOfBirth) {
        this.username = username;
        this.address = address;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
    }
    @Ignore
    public User(String username, String email,String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Getters và Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhone() { // Getter cho trường mới
        return phone;
    }

    public void setPhone(String phone) { // Setter cho trường mới
        this.phone = phone;
    }

    public String getDateOfBirth() { // Getter cho trường mới
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) { // Setter cho trường mới
        this.dateOfBirth = dateOfBirth;
    }
}
