package com.example.pmd_se_a_java.ClassTask7;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pmd_se_a_java.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.storage.FirebaseStorage;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditText, companyEditText, priceEditText;
    private ImageView imageView;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        nameEditText = findViewById(R.id.nameEditText);
        companyEditText = findViewById(R.id.companyEditText);
        priceEditText = findViewById(R.id.priceEditText);
        imageView = findViewById(R.id.imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open image picker
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }

    public void onSaveClicked(View view) {
        // Save data to Firebase
        String name = nameEditText.getText().toString();
        String company = companyEditText.getText().toString();
        String price = priceEditText.getText().toString();

        // Upload image to Firebase Storage
        if (imageUri != null) {
            com.google.firebase.storage.StorageReference storageRef = FirebaseStorage.getInstance().getReference()
                    .child("images/" + UUID.randomUUID().toString()); // Generate unique name for the image
            storageRef.putFile(imageUri)
                    .addOnSuccessListener(taskSnapshot -> {
                        // Image uploaded successfully, get download URL
                        storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                            String imageUrl = uri.toString();

                            // Saving data to firebase
                            DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("items");
                            String itemId = databaseRef.push().getKey();
                            product item = new product(itemId, name, company, price, imageUrl);
                            assert itemId != null;
                            databaseRef.child(itemId).setValue(item);

                            Toast.makeText(MainActivity.this, "Item saved successfully", Toast.LENGTH_SHORT).show();
                        });
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(MainActivity.this, "Failed to upload image: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        } else {
            Toast.makeText(MainActivity.this, "Please select an image", Toast.LENGTH_SHORT).show();
        }
    }



    public void onListClicked(View view) {
        Intent intent = new Intent(MainActivity.this, ListActivity.class);
        startActivity(intent);
    }
}
