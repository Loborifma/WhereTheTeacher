package com.example.wheretheteacher.remote;

import android.content.Context;

import androidx.room.Room;

import com.example.wheretheteacher.room.Teacher;
import com.example.wheretheteacher.room.TeachersDB;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DataManager {

    private Context context;
    private TeachersDB teachersDB;

    public DataManager(Context context) {
        this.context = context;
        teachersDB = Room.databaseBuilder(context, TeachersDB.class, "teachers.db").build();
    }

    public void addData(final DataCallBack dataCallBack, final String teacherName, final String teacherLink){
        Completable.fromAction(() -> {
            Teacher teacher = new Teacher(teacherName, teacherLink);
            teachersDB.getTeachersDao().insert(teacher);

        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }

            @Override
            public void onComplete() {
                dataCallBack.dataAdded();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                dataCallBack.errorAdded();
            }
        });
    }

    public void deleteData(final DataCallBack dataCallBack, final Teacher teacher){
        Completable.fromAction(()->{
            teachersDB.getTeachersDao().deleteTeacher(teacher);
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(dataCallBack::dataDeleted);
    }

    public void linksLoad(final DataCallBack dataCallBack){
        teachersDB.getTeachersDao().getTeachers().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataCallBack::loadLinks);
    }

    public void loadData(final DataCallBack dataCallBack){
        teachersDB.getTeachersDao().getTeachers().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataCallBack::loadTeachersData);
    }

    public void empty(final DataCallBack dataCallBack){
        teachersDB.getTeachersDao().getTeachers().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataCallBack::isEmpty);
    }

}
