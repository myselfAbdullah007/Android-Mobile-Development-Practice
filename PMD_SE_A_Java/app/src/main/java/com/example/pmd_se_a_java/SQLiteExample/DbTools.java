package com.example.pmd_se_a_java.SQLiteExample;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class DbTools extends SQLiteOpenHelper {
    Context context;

    public DbTools(Context context){
        super(context,"ContactsDB",null,1);
        this.context = context;
        Log.d("TAG","DB Created");
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CreateTableQuery = "CREATE TABLE CONTACTS(" + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                +"firstName TEXT,"+"secondName TEXT,"+"phoneNumber TEXT,"+"emailAddress TEXT,"+"homeAddress TEXT)";
        db.execSQL(CreateTableQuery);


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void AddContact(HashMap<String,String> contact) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstName",contact.get("firstName"));
        contentValues.put("secondName",contact.get("secondName"));
        contentValues.put("phoneNumber",contact.get("phoneNumber"));
        contentValues.put("emailAddress",contact.get("emailAddress"));
        contentValues.put("homeAddress",contact.get("homeAddress"));
        db.insert("CONTACTS",null,contentValues);

    }

    public ArrayList<HashMap<String,String>> getAllContacts(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<HashMap<String,String>> allContacts = new ArrayList<HashMap<String,String>>();
        String query = "SELECT * FROM CONTACTS";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                HashMap<String,String> contact = new HashMap<String,String>();
                contact.put("_id", cursor.getString(0));
                contact.put("firstName",cursor.getString(1));
                contact.put("secondName", cursor.getString(2));
                contact.put("phoneNumber", cursor.getString(3));
                contact.put("emailAddress", cursor.getString(4));
                contact.put("homeAddress", cursor.getString(5));
                allContacts.add(contact);
            }while(cursor.moveToNext());
        }
        return allContacts;
    }

    public HashMap<String,String> getSingleContact(String id){
        SQLiteDatabase db = getReadableDatabase();
        HashMap<String,String> hashMap = new HashMap<String,String>();
        String query = "SELECT * FROM CONTACTS WHERE _id = "+id;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            hashMap.put("_id", cursor.getString(0));
            hashMap.put("firstName", cursor.getString(1));
            hashMap.put("secondName", cursor.getString(2));
            hashMap.put("phoneNumber", cursor.getString(3));
            hashMap.put("emailAddress", cursor.getString(4));
            hashMap.put("homeAddress", cursor.getString(5));
        }
        return hashMap;
    }

    public void updateContact(String id,HashMap<String,String> contact){
        Log.d("TAG","UPDATE CALLED");
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Log.d("VALUE",contact.get("firstName"));
        contentValues.put("firstName",contact.get("firstName"));
        contentValues.put("secondName",contact.get("secondName"));
        contentValues.put("phoneNumber",contact.get("phoneNumber"));
        contentValues.put("emailAddress",contact.get("emailAddress"));
        contentValues.put("homeAddress",contact.get("homeAddress"));
        int res = db.update("CONTACTS",contentValues,"_id"+ "=?",new String[]{id});
        if(res > 0){
            Toast.makeText(context, "Updated Successfully", Toast.LENGTH_SHORT).show();

        } else{
            Toast.makeText(context, "Update Failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteContact(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete("CONTACTS","_id"+"=?",new String[]{id});
        if(res>0){
            Toast.makeText(context, "Contact Deleted", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(context, "Deletion Failed", Toast.LENGTH_SHORT).show();
        }

    }
}
