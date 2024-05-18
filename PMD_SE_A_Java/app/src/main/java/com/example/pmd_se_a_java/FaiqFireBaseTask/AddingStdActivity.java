package com.example.pmd_se_a_java.FaiqFireBaseTask;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pmd_se_a_java.FireBase_Task.Student;
import com.example.pmd_se_a_java.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddingStdActivity extends AppCompatActivity {
     EditText editTextName, editTextSection, editTextRollNo, editTextCnic, editTextCgpa;
     DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        editTextName = findViewById(R.id.editTextName);
        editTextSection = findViewById(R.id.editTextSection);
        editTextRollNo = findViewById(R.id.editTextRollNo);
        editTextCnic = findViewById(R.id.editTextCnic);
        editTextCgpa = findViewById(R.id.editTextCgpa);
        Button buttonAddToDatabase = findViewById(R.id.buttonAddToDatabase);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        buttonAddToDatabase.setOnClickListener(view -> {
            String name = editTextName.getText().toString().trim();
            String section = editTextSection.getText().toString().trim();
            String rollNo = editTextRollNo.getText().toString().trim();
            String cnic = editTextCnic.getText().toString().trim();
            String cgpa = editTextCgpa.getText().toString().trim();

            if (!name.isEmpty() && !section.isEmpty() && !rollNo.isEmpty() && !cnic.isEmpty() && !cgpa.isEmpty()) {
                StdModel student = new StdModel(name, section, rollNo, cnic, cgpa);
                databaseReference.push().setValue(student, databaseReference);

            }
        });
    }
}
