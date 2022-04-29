package com.example.wheretheteacher.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface TeachersDao {

    @Query("SELECT * FROM Teacher")
    Flowable<List<Teacher>> getTeachers();

    @Insert
    void insert(Teacher teacher);

    @Delete
    void deleteTeacher(Teacher teacher);
}
