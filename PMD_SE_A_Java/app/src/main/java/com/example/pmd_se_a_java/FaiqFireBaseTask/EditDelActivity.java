package com.example.pmd_se_a_java.FaiqFireBaseTask;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pmd_se_a_java.FireBase_Task.Student;
import com.example.pmd_se_a_java.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditDelActivity extends AppCompatActivity {

    private EditText editTextName, editTextSection, editTextRollNo, editTextCnic, editTextCgpa;
    StdModel student;
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
        Button buttonEdit = findViewById(R.id.buttonEdit);
        Button buttonDelete = findViewById(R.id.buttonDelete);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("student")) {
            student = (StdModel) intent.getSerializableExtra("student");
            if (student != null) {
                editTextName.setText(student.getName());
                editTextSection.setText(student.getSection());
                editTextRollNo.setText(student.getRollNo());
                editTextCnic.setText(student.getCnic());
                editTextCgpa.setText(student.getCgpa());
            }
        }

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

        databaseReference.push().setValue(student, databaseReference);
    }

    private void deleteStudent() {
        if (student != null) {
            String rollNo = student.getRollNo();
            databaseReference.orderByChild("rollNo").equalTo(rollNo).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                            childSnapshot.getRef().removeValue();
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        }
    }


}
