package com.example.pmd_se_a_java.FirebaseExamples;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pmd_se_a_java.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseExampleOneMainActivity extends AppCompatActivity {

    DatabaseReference myreference;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_firebase_example_one_main);
        textView=findViewById(R.id.txtfirebase);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        FirebaseDatabase database =FirebaseDatabase.getInstance("https://pmd-se-a-java-7b92b-default-rtdb.firebaseio.com/");
        myreference= database.getReference("Student");
        //myreference.setValue("this is first value");
        //myreference.child("BSSE").child("PMD").child("Student").setValue("Abdullah");
    }

    public void send(View view) {
//        myreference.child("BSSE").child("PMD").child("Student 1").setValue("Abdullah");
//        myreference.child("BSSE").child("SMD").child("Student 2").setValue("Chaudhary");

        myreference.setValue("The First vcv Value To be Retrieved");
    }

    public void RetriveValue(View view) {

        myreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String value=snapshot.getValue().toString();
//                textView.setText(value);
                if(snapshot.getValue()!=null)
                {
                    for(DataSnapshot dataSnapshot:snapshot.getChildren())
                    {
                        String value= (String) dataSnapshot.getValue();
                        textView.setText(value+" ");
                        Log.d("TAG",""+value);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}