package com.example.wheretheteacher;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import javax.inject.Singleton;

@Database(entities = {Teacher.class}, version = 1)
@Singleton
public abstract class TeachersDB extends RoomDatabase {
    public abstract TeachersDao teachersDao();
}