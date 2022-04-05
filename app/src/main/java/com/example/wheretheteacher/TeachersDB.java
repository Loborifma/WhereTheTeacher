package com.example.wheretheteacher;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Teacher.class}, version = 1)
public abstract class TeachersDB extends RoomDatabase {
    public abstract TeachersDao teachersDao();
}
