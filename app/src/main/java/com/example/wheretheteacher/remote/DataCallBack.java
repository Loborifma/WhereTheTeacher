package com.example.wheretheteacher.remote;

import com.example.wheretheteacher.room.Teacher;

import java.util.List;

public interface DataCallBack {

    void dataAdded();
    void errorAdded();
    void loadLinks(List<Teacher> teachersLinkList);
    void loadTeachersData(List<Teacher> teachersNameList);
    void isEmpty(List<Teacher> teachersNameList);
    void dataDeleted();
}
