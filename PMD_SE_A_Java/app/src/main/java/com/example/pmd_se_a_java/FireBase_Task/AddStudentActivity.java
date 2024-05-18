package com.example.pmd_se_a_java.FireBase_Task;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pmd_se_a_java.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddStudentActivity extends AppCompatActivity {

    private EditText editTextName, editTextSection, editTextRollNo, editTextCnic, editTextCgpa;
    private Button buttonAddToDatabase;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        editTextName = findViewById(R.id.editTextName);
        editTextSection = findViewById(R.id.editTextSection);
        editTextRollNo = findViewById(R.id.editTextRollNo);
        editTextCnic = findViewById(R.id.editTextCnic);
        editTextCgpa = findViewById(R.id.editTextCgpa);
        buttonAddToDatabase = findViewById(R.id.buttonAddToDatabase);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("students");

        buttonAddToDatabase.setOnClickListener(view -> {
            String name = editTextName.getText().toString().trim();
            String section = editTextSection.getText().toString().trim();
            String rollNo = editTextRollNo.getText().toString().trim();
            String cnic = editTextCnic.getText().toString().trim();
            String cgpa = editTextCgpa.getText().toString().trim();

            if (!name.isEmpty() && !section.isEmpty() && !rollNo.isEmpty() && !cnic.isEmpty() && !cgpa.isEmpty()) {
                Student student = new Student(name, section, rollNo, cnic, cgpa);
                databaseReference.push().setValue(student, (databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        // Error handling
                        Toast.makeText(AddStudentActivity.this, "Failed to push data: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        // Success message
                        Toast.makeText(AddStudentActivity.this, "Data pushed to database successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
        });
    }
}
