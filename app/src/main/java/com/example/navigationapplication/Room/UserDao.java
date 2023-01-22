package com.example.navigationapplication.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void InsertUser(User user);

//
//    @Update
//    Void UpdateUser(User user);

    @Delete
    void DeleteUser(User user);


    @Query("SELECT * FROM users WHERE Uid= uid")
    List<User> getUser();

    @Query("SELECT * FROM users" )
    List<User> getAllusers();
    @Query("UPDATE users SET name=:nameupd WHERE Uid= :uid")
     void Updateuser(String nameupd ,int uid);
}

