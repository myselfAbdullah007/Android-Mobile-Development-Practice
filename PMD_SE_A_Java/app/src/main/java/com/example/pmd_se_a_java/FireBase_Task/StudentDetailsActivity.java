package com.example.pmd_se_a_java.FireBase_Task;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pmd_se_a_java.FireBase_Task.Student;
import com.example.pmd_se_a_java.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentDetailsActivity extends AppCompatActivity {

    private EditText editTextName, editTextSection, editTextRollNo, editTextCnic, editTextCgpa;
    private Button buttonEdit, buttonDelete;
    Student student;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("students");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        editTextName = findViewById(R.id.editTextName);
        editTextSection = findViewById(R.id.editTextSection);
        editTextRollNo = findViewById(R.id.editTextRollNo);
        editTextCnic = findViewById(R.id.editTextCnic);
        editTextCgpa = findViewById(R.id.editTextCgpa);
        buttonEdit = findViewById(R.id.buttonEdit);
        buttonDelete = findViewById(R.id.buttonDelete);

        // Get student details from intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("student")) {
            student = (Student) intent.getSerializableExtra("student");
            if (student != null) {
                // Set student details to EditText fields
                editTextName.setText(student.getName());
                editTextSection.setText(student.getSection());
                editTextRollNo.setText(student.getRollNo());
                editTextCnic.setText(student.getCnic());
                editTextCgpa.setText(student.getCgpa());
            }
        }

        // Set click listeners
        buttonEdit.setOnClickListener(view -> {
            updateStudentDetails();
        });
        buttonDelete.setOnClickListener(view -> {
            deleteStudent();
        });
    }

    private void updateStudentDetails() {
        String updatedName = editTextName.getText().toString();
        String updatedSection = editTextSection.getText().toString();
        String updatedRollNo = editTextRollNo.getText().toString();
        String updatedCnic = editTextCnic.getText().toString();
        String updatedCgpa = editTextCgpa.getText().toString();

        student.setName(updatedName);
        student.setSection(updatedSection);
        student.setRollNo(updatedRollNo);
        student.setCnic(updatedCnic);
        student.setCgpa(updatedCgpa);

        databaseReference.push().setValue(student, (databaseError, databaseReference) -> {
                if (databaseError != null) {
                    Toast.makeText(StudentDetailsActivity.this, "Failed to push data: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(StudentDetailsActivity.this, "Data Updated database successfully", Toast.LENGTH_SHORT).show();
                }
            });
    }

    private void deleteStudent() {
        if (student != null) {
            String rollNo = student.getRollNo();
            databaseReference.orderByChild("rollNo").equalTo(rollNo).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    // Check if any student with the specified roll number exists
                    if (dataSnapshot.exists()) {
                        // Iterate over the dataSnapshot to get the child node reference
                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                            // Remove the student data from the database
                            childSnapshot.getRef().removeValue().addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    Toast.makeText(StudentDetailsActivity.this, "Student deleted from database", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    Toast.makeText(StudentDetailsActivity.this, "Failed to delete student: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    } else {
                        Toast.makeText(StudentDetailsActivity.this, "Student not found in the database", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        }
    }


}
