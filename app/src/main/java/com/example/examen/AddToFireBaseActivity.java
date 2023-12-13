package com.example.examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.examen.Database.DatabaseAux;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddToFireBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_fire_base);
    }

    public void changeToMain(View view) {
        Intent nIntent = new Intent(AddToFireBaseActivity.this, MainActivity.class);
        startActivity(nIntent);
    }

    public void insertValues(View v) {
        TextView nameTextView = findViewById(R.id.insertNameAdd);
        TextView gradeTextView = findViewById(R.id.insertGradeAdd);
        TextView dateTextView = findViewById(R.id.insertDateAdd);

        String nameString = nameTextView.getText().toString();
        String gradeString = gradeTextView.getText().toString();
        String dateString = dateTextView.getText().toString();


        DatabaseAux aux = new DatabaseAux(AddToFireBaseActivity.this);
        SQLiteDatabase db = aux.getWritableDatabase();

        if (db != null && !nameString.isEmpty() && !gradeString.isEmpty() && !dateString.isEmpty()) {
            ContentValues values = new ContentValues();
            values.put("name", nameString);
            values.put("grade", gradeString);
            values.put("date", dateString);

            long res = db.insert("users", null, values);
            if (res >= 0) {
                Toast.makeText(this, "Insertado correctamente", Toast.LENGTH_LONG).show();
                nameTextView.setText("");
                gradeTextView.setText("");
                dateTextView.setText("");
            } else {
                Toast.makeText(this, "Fallo al insertar", Toast.LENGTH_LONG).show();
            }
            db.close();
        } else {
            Toast.makeText(this, "Fallo al insertar", Toast.LENGTH_LONG).show();
        }

        FirebaseFirestore firestoreDb = FirebaseFirestore.getInstance();
        Map<String, Examenes> examenes = new HashMap<>();
        Examenes ex1 = new Examenes();
        ex1.name = nameString;
        ex1.grade = gradeString;
        ex1.date = dateString;
        examenes.put(nameString, ex1);

    }
}