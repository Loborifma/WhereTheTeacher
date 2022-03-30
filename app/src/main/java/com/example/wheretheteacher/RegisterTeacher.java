package com.example.wheretheteacher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
        Intent intent = new Intent();
        intent.putExtra("name", etName.getText().toString());
        intent.putExtra("link", etLink.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }

}