package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ShowFireBaseDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_fire_base_data);
    }

    public void changeToMain(View view) {
        Intent nIntent = new Intent(ShowFireBaseDataActivity.this, MainActivity.class);
        startActivity(nIntent);
    }
}