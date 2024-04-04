package com.example.pmd_se_a_java.SQLiteExample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pmd_se_a_java.R;

import java.util.ArrayList;
import java.util.HashMap;

public class EditContactEntry extends AppCompatActivity {
    EditText editFirstName ,editSecondName, editPhoneNumber,editEmailAddress,editHomeAddress;

    Button update;

    Button delete;
    DbTools dbTools;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_contact_entry);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        editFirstName = findViewById(R.id.editfirstname);
        editSecondName = findViewById(R.id.editsecondname);
        editPhoneNumber = findViewById(R.id.editphonenumber);
        editEmailAddress = findViewById(R.id.editemailaddress);
        editHomeAddress = findViewById(R.id.edithomeaddress);
        update = findViewById(R.id.btnupdate);
        delete = findViewById(R.id.btndelete);
        dbTools = new DbTools(getApplicationContext());
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        HashMap<String,String> singleContact = dbTools.getSingleContact(id);
        setEdtText(singleContact);
        Toast.makeText(this, "Data Retrieved", Toast.LENGTH_SHORT).show();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,String> updatedContact = new HashMap<String,String>();
                updatedContact.put("firstName",editFirstName.getText().toString());
                updatedContact.put("secondName",editSecondName.getText().toString());
                updatedContact.put("phoneNumber",editPhoneNumber.getText().toString());
                updatedContact.put("emailAddress",editEmailAddress.getText().toString());
                updatedContact.put("homeAddress",editHomeAddress.getText().toString());
                Log.d("ID",id);
                dbTools.updateContact(id,updatedContact);
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbTools.deleteContact(id);
                finish();

            }
        });



    }
    void setEdtText(HashMap<String,String> contact){
        editFirstName.setText(contact.get("firstName"));
        editSecondName.setText(contact.get("secondName"));
        editPhoneNumber.setText(contact.get("phoneNumber"));
        editEmailAddress.setText(contact.get("emailAddress"));
        editHomeAddress.setText(contact.get("homeAddress"));
    }

}