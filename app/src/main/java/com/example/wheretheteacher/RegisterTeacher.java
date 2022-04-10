package com.example.wheretheteacher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class RegisterTeacher extends AppCompatActivity implements View.OnClickListener {

    EditText etName;
    EditText etLink;
    Button btnAdd;
    StringBuffer stringBuffer = new StringBuffer();
    String name;
    String link;

    static final int FIRST_EIGHT_CHAR_HTTPS = 8;
    static final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_teacher);

        etName = findViewById(R.id.etName);
        etLink = findViewById(R.id.etLink);

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        name = etName.getText().toString();
        link = checkOnHttps(etLink.getText().toString());

        if(name.isEmpty() || link.isEmpty()){
            Toast.makeText(this, "Одно из полей пустое", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent();
        intent.putExtra("name", name);
        intent.putExtra("link", link);
        setResult(RESULT_OK, intent);
        finish();
    }

    public String checkOnHttps(String str){
        String tmp;

        splitWordOnCharHttps(str);

        tmp = stringBuffer.toString();

        if(tmp.equals("https://")){
            return str;
        }else if(Pattern.matches("http://.", tmp)){
            return str;
        }

        tmp = "https://" + str;
        return tmp;
    }

    public void splitWordOnCharHttps(String str){
        for(int i = 0; i < FIRST_EIGHT_CHAR_HTTPS; i++){
            stringBuffer.append(str.charAt(i));
        }
    }

}