package com.example.wheretheteacher;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wheretheteacher.adapter.CustomAdapter;
import com.example.wheretheteacher.remote.DataCallBack;
import com.example.wheretheteacher.remote.DataManager;
import com.example.wheretheteacher.room.Teacher;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, DataCallBack,
        AdapterView.OnItemLongClickListener {
    private static final String TAG = "myLogs";

    private List<Teacher> links = new ArrayList<>();
    private ListView lvTeacher;
    private Button btnAddMain;
    private int numberElement;
    private DataManager dataManager;
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        btnAddMain = findViewById(R.id.btnAddMain);
        btnAddMain.setOnClickListener(this);

        lvTeacher = findViewById(R.id.lvTeacher);
        lvTeacher.setOnItemClickListener(this);
        lvTeacher.setOnItemLongClickListener(this);
        registerForContextMenu(lvTeacher);

        dataManager = new DataManager(this);
        dataManager.loadData(MainActivity.this);
        dataManager.linksLoad(MainActivity.this);
        dataManager.empty(MainActivity.this);
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

        dataManager.addData(MainActivity.this, data.getStringExtra("teacherName"),
                    data.getStringExtra("teacherLink"));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String tmp = links.get(position).getTeacherLink();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tmp));
        startActivity(intent);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, 1, 0, "Delete");
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        numberElement = i;
        return false;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        dataManager.deleteData(MainActivity.this, links.get(numberElement));
        return super.onContextItemSelected(item);
    }

    @Override
    public void loadLinks(List<Teacher> teachersLinkList) {
        links = teachersLinkList;
    }

    @Override
    public void loadTeachersData(List<Teacher> teachersNameList) {
        customAdapter = new CustomAdapter(this, teachersNameList);
        lvTeacher.setAdapter(customAdapter);
    }

    @Override
    public void isEmpty(List<Teacher> teachersNameList) {
        if(teachersNameList.size() == 0){
            Intent intent = new Intent(this, StartActivity.class);
            startActivityForResult(intent, 1);
        }
    }

    @Override
    public void dataAdded() {
        Log.d(TAG, "dataAdded");
    }

    @Override
    public void errorAdded() {
        Log.d(TAG, "errorAdded");
    }

    @Override
    public void dataDeleted() {
        Log.d(TAG, "dataDeleted");
    }




}
