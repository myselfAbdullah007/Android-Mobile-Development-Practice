package com.example.pmd_se_a_java.FirebaseExamples;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmd_se_a_java.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FireBaseExampleTwoMainActivity extends AppCompatActivity {
RecyclerView recyclerView;
DatabaseReference reference;
FirebaseDatabase database;

FirebaseAdapter adapter;

ArrayList<Student> studentArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fire_base_example_two_main);
        recyclerView=findViewById(R.id.firebaserecyclerview);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);


        database= FirebaseDatabase.getInstance("https://pmd-se-a-java-7b92b-default-rtdb.firebaseio.com/");
        reference=database.getReference("Student");
        studentArrayList=new ArrayList<>();
        LoadData();
    }
    public void LoadData()
    {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    Student obj= new Student();
                    obj.setName(dataSnapshot.child("Name").getValue().toString());
                    obj.setUrl(dataSnapshot.child("Picture").getValue().toString());
                }
                adapter=new FirebaseAdapter(studentArrayList,getApplicationContext());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}