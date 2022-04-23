package com.example.wheretheteacher;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.wheretheteacher.room.Teacher;
import com.example.wheretheteacher.room.TeachersDB;
import com.example.wheretheteacher.room.TeachersDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener,
        AdapterView.OnItemLongClickListener {

    List<String> name = new ArrayList<>();
    List<String> link = new ArrayList<>();

    ListView lvTeacher;
    Button btnAddMain;

    String nameElement;

    Teacher teacher;

    TeachersDB database;
    TeachersDao teachersDao;

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
        lvTeacher.setOnItemLongClickListener(this);

        registerForContextMenu(lvTeacher);
    }

    @Override
    protected void onResume() {
        super.onResume();

        name = teachersDao.getTeacherName();
        link = teachersDao.getTeacherLink();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.my_list_item, name);
        lvTeacher.setAdapter(adapter);

        if(name.isEmpty()){
            Intent intent = new Intent(this, StartActivity.class);
            startActivityForResult(intent, 1);
        }
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

        teacher = new Teacher();
        teacher.teacherName = data.getStringExtra("name");
        teacher.link = data.getStringExtra("link");
        teachersDao.insert(teacher);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link.get((int) id)));
        startActivity(intent);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, 1, 0, "Delete");
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        nameElement = name.get(position);
        return false;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        teachersDao.delete(nameElement);
        onResume();
        return super.onContextItemSelected(item);
    }
}
