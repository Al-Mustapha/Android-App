package com.example.appdesign;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Connecting extends SQLiteOpenHelper {

    String firstTable = "CREATE TABLE My_Table(id INTEGER PRIMARY KEY AUTOINCREMENT, Firstname TEXT, Middlename TEXT, Lastname TEXT, Dob TEXT, Email TEXT, Username TEXT, Password NUMBER)";
    String firstTable1 = "CREATE TABLE Products_Table(id INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Description TEXT, Price INTEGER, Imagename BLOB)";
    //  String secondTable = "CREATE TABLE Products(id primary key autoincrement, Category TEXT, Price NUMBER, Description TEXT, Image TEXT)";


    public Connecting(Context context) {
        super(context, "TestApp", null, 5);


    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(firstTable);
        db.execSQL(firstTable1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP Table IF EXISTS My_Table");
        db.execSQL("DROP Table IF EXISTS Products_Table");
        onCreate(db);
    }

    public void createTable(SQLiteDatabase db) {

        ContentValues cv = new ContentValues();
        cv.put("id", 0);
        cv.put("Category", "Foods");
        cv.put("Description", "welcoming toy to");
        cv.put("Image", "testing");
        db.insert("Products", null, cv);

    }

    public void inserting(int identity, String firstname, String middlename, String lastname, String dob, String email, String username, String password) {
        try {
            SQLiteDatabase dbase = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("id", identity);
            cv.put("Firstname", firstname);
            cv.put("Middlename", middlename);
            cv.put("Lastname", lastname);
            cv.put("Dob", dob);
            cv.put("Email", email);
            cv.put("Username", username);
            cv.put("Password", password);
            dbase.insert("My_Table", String.valueOf(0), cv);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Cursor fetching(String username, String password) {
        SQLiteDatabase dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery("select * from My_Table where Username=? and Password=?", new String[]{username, password});
        Boolean condi = false;


        if (cursor.moveToFirst()) {
            condi = true;
        } else {
            condi = false;
        }
        return cursor;

    }

    public void createTable1(SQLiteDatabase db) {

    }

    public Cursor getProducts() {
        SQLiteDatabase dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery("select * from Products_Table", null);
        return cursor;
    }

    public byte[] getBitmapByName(String name) {
        SQLiteDatabase dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery("select Imagename from Products_Table where Name = ?", new String[]{name});
        byte[] bytes = null;
        if (cursor.moveToFirst()) {

            do {
                bytes = cursor.getBlob(cursor.getColumnIndexOrThrow("Imagename"));

            }
            while (cursor.moveToNext());

        }

        return bytes;
    }
}

