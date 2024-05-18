package com.example.pmd_se_a_java.FireBase_Task;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmd_se_a_java.FireBase_Task.Student;
import com.example.pmd_se_a_java.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewStudents;
    private Button buttonAddStudent;
    private StudentAdapter adapter;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerViewStudents = findViewById(R.id.recyclerViewStudents);
        buttonAddStudent = findViewById(R.id.buttonAddStudent);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("students");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewStudents.setLayoutManager(layoutManager);

        adapter = new StudentAdapter(new ArrayList<>());
        recyclerViewStudents.setAdapter(adapter);

        buttonAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
                startActivity(intent);
            }
        });

        fetchStudentData();

        // Seting item click listener for RecyclerView
        adapter.setOnItemClickListener(new StudentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Student clickedStudent = adapter.getStudentList().get(position);
                Intent intent = new Intent(MainActivity.this, StudentDetailsActivity.class);
                intent.putExtra("student", clickedStudent);
                startActivity(intent);
            }
        });
    }
    private void fetchStudentData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Student> studentList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Student student = snapshot.getValue(Student.class);
                    if (student != null) {
                        studentList.add(student);
                    }
                }
                adapter.setStudentList(studentList);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
