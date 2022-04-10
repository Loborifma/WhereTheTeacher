package com.example.wheretheteacher;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TeachersDao {

    @Query("SELECT teacherName FROM Teacher")
    List<String> getTeacherName();

    @Query("SELECT link FROM Teacher")
    List<String> getTeacherLink();

    @Insert
    void insert(Teacher teacher);

    @Query("DELETE FROM Teacher WHERE teacherName = :teacherName")
    void delete(String teacherName);
}
