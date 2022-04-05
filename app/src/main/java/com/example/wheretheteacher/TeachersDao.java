package com.example.wheretheteacher;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TeachersDao {

    @Query("SELECT teacherName FROM Teacher")
    List<String> getTeacherName();

    @Query("SELECT link FROM Teacher")
    List<String> getTeacherLink();
}
