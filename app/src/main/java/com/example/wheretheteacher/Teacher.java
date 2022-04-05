package com.example.wheretheteacher;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Teacher {

    @PrimaryKey(autoGenerate = true)
    public long id;

    public String link;

    public String teacherName;
}
