package com.example.wheretheteacher;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    List<String> name = new ArrayList<>();
    List<String> link = new ArrayList<>();

    ListView lvTeacher;
    Button btnAddMain;

    TeachersDB database;
    private TeachersDao teachersDao;

    private static final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        database = Room.databaseBuilder(this, TeachersDB.class, "database").allowMainThreadQueries().build();
        teachersDao = database.teachersDao();

        btnAddMain = findViewById(R.id.btnAddMain);
        btnAddMain.setOnClickListener(this);

        lvTeacher = findViewById(R.id.lvTeacher);
        lvTeacher.setOnItemClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        name = teachersDao.getTeacherName();
        link = teachersDao.getTeacherLink();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.my_list_item, name);
        lvTeacher.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, RegisterTeacher.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data == null){return;}

        Teacher teacher = new Teacher();
        teacher.teacherName = data.getStringExtra("name");
        teacher.link = data.getStringExtra("link");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link.get((int) id)));
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
