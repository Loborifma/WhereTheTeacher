package com.example.wheretheteacher;

import static androidx.core.content.PackageManagerCompat.LOG_TAG;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    List<String> name = new ArrayList<>();
    List<String> link = new ArrayList<>();

    ListView lvTeacher;
    Button btnAddMain;

    final String FILENAME = "file";
    final String FILELINK = "file_link";

    private static final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        btnAddMain = findViewById(R.id.btnAddMain);
        btnAddMain.setOnClickListener(this);

        lvTeacher = findViewById(R.id.lvTeacher);
        lvTeacher.setOnItemClickListener(this);

        readFileName();
        readFileLink();
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

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.my_list_item, name);
        lvTeacher.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link.get((int) id)));
        startActivity(intent);
    }

    public void writeFileName(){
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(openFileOutput(FILENAME, MODE_PRIVATE)));
            for(String names:name){
                bw.write(names);
            }
            bw.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFileLink(){
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(openFileOutput(FILELINK, MODE_PRIVATE)));
            for(String links:link){
                bw.write(links);
            }
            bw.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFileName(){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(FILENAME)));
            String str = "";
            while ((str = br.readLine()) != null){
                name.add(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.my_list_item, name);
        lvTeacher.setAdapter(adapter);
    }

    public void readFileLink(){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(FILELINK)));
            String str = "";
            while ((str = br.readLine()) != null){
                link.add(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        writeFileLink();
        writeFileName();
    }
}
