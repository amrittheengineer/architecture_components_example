package com.example.architectural_components;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditNoteActivity extends AppCompatActivity {
    public static final String EXTRA_TITLE =
            "com.example.architectural_components.EXTRA_TITLE";

    public static final String EXTRA_PRIORITY =
            "com.example.architectural_components.EXTRA_PRIORITY";

    public static final String EXTRA_DESCRIPTION =
            "com.example.architectural_components.EXTRA_DESCRIPTION";

    public static final String EXTRA_ID =
            "com.example.architectural_components.EXTRA_ID";
    private EditText title, description;
    NumberPicker priority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        title = findViewById(R.id.new_note_title);
        description = findViewById(R.id.new_note_description);
        priority = findViewById(R.id.new_note_priority);

        priority.setMaxValue(10);
        priority.setMinValue(1);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Note");
            title.setText(intent.getStringExtra(EXTRA_TITLE));
            priority.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
            description.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
        }else {
            setTitle("Add note");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_note:
                saveNote();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void saveNote(){
        String note_title = title.getText().toString();
        String note_description = description.getText().toString();
        int note_priority = priority.getValue();

        if (note_title.trim().isEmpty() || note_description.trim().isEmpty()){
            Toast.makeText(this, "Please insert title/description", Toast.LENGTH_SHORT).show();
        }else{
            Intent data = new Intent();

            int id = getIntent().getIntExtra(EXTRA_ID, -1);

            if (id != -1){
//                Toast.makeText(this, id+"", Toast.LENGTH_SHORT).show();
                data.putExtra(EXTRA_ID, id);
            }
            data.putExtra(EXTRA_TITLE, note_title);
            data.putExtra(EXTRA_PRIORITY, note_priority);
            data.putExtra(EXTRA_DESCRIPTION, note_description);

            setResult(RESULT_OK, data);
            finish();
        }
    }
}
