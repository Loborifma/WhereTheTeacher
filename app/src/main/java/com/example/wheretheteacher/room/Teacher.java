package com.example.wheretheteacher.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Teacher {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String teacherLink;

    private String teacherName;

    public Teacher(String teacherName, String teacherLink) {
        this.teacherLink = teacherLink;
        this.teacherName = teacherName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTeacherLink() {
        return teacherLink;
    }

    public void setTeacherLink(String teacherLink) {
        this.teacherLink = teacherLink;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
