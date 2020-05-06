package com.vs.room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddName extends AppCompatActivity {
    public static final String EXTRA_FIRST_NAME =
            "EXTRA_TITLE";
    public static final String EXTRA_LAST_NAME =
            "EXTRA_DESCRIPTION";
    private EditText editTextTitle;
    private EditText editTextDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_name);
        editTextTitle = findViewById(R.id.first_name);
        editTextDescription = findViewById(R.id.last_name);
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });
        setTitle("Add Note");
    }

    private void saveNote() {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_FIRST_NAME, title);
        data.putExtra(EXTRA_LAST_NAME, description);

        setResult(RESULT_OK, data);
        finish();
    }
}
