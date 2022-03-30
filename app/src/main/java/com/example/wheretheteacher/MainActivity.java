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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    List<String> name = new ArrayList<>();
    List<String> link = new ArrayList<>();

    ListView lvTeacher;
    Button btnAddMain;

    SharedPreferences sPref;

    private static final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        btnAddMain = findViewById(R.id.btnAddMain);
        btnAddMain.setOnClickListener(this);

        lvTeacher = findViewById(R.id.lvTeacher);
        lvTeacher.setOnItemClickListener(this);

        sPref = getSharedPreferences("my_settings", MODE_PRIVATE);

        boolean hasVisited = sPref.getBoolean("hasVisited", false);

        if(!hasVisited){
            Log.d(TAG, "check");
            SharedPreferences.Editor editor = sPref.edit();
            editor.putBoolean("hasVisited", true);
            editor.apply();
        }else {
            Log.d(TAG, "uncheck");
            load();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
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

        name.add(data.getStringExtra("name"));
        link.add(data.getStringExtra("link"));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link.get((int) id)));
        startActivity(intent);
    }

    public void save(){
        Set<String> nameSetSave = new HashSet<>(name);
        Set<String> linkSetSave = new HashSet<>(link);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putStringSet("name", nameSetSave);
        editor.putStringSet("link", linkSetSave);
        editor.apply();
    }

    public void load(){
        Set<String> nameSetLoad = sPref.getStringSet("name", new HashSet<>());
        Set<String> linkSetLoad = sPref.getStringSet("link", new HashSet<>());

        name = new ArrayList<>(nameSetLoad);
        link = new ArrayList<>(linkSetLoad);
        Collections.sort(name);
        Collections.sort(link);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        save();
    }
}
