package com.example.wheretheteacher.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Teacher {

    @PrimaryKey(autoGenerate = true)
    public long id;

    public String link;

    public String teacherName;
}
