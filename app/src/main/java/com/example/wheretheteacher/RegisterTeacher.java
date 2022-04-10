package com.example.wheretheteacher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterTeacher extends AppCompatActivity implements View.OnClickListener {

    EditText etName;
    EditText etLink;
    Button btnAdd;

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
        String name = etName.getText().toString();
        String link = etLink.getText().toString();

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

}