package com.example.appdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {
    int id  = 0;
    EditText fname,mname,lname,db,email;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        id = intent.getExtras().getInt("id",0);
        divisible();

    }


    public void divisible() {
        fname = (EditText) findViewById(R.id.fname);
        mname = (EditText) findViewById(R.id.fname2);
        lname = (EditText) findViewById(R.id.fname3);
        db = (EditText) findViewById(R.id.fname4);
        email = (EditText) findViewById(R.id.fname5);

try {
        SQLiteOpenHelper sqLiteOpenHelper = new Connecting(this);

            SQLiteDatabase lite = sqLiteOpenHelper.getReadableDatabase();
            Cursor cursor = lite.rawQuery("select * from My_Table where id=?",new String[]{String.valueOf(id)});
            if (cursor.moveToFirst()) {
                String f = cursor.getString(1);
                fname.setText(f);
                String m = cursor.getString(2);
                mname.setText(m);
                String l = cursor.getString(3);
                lname.setText(l);
                String d = cursor.getString(4);
                db.setText(d);
                String em = cursor.getString(5);
                email.setText(em);

            }
            fname.setEnabled(false);
            mname.setEnabled(false);
            lname.setEnabled(false);
            db.setEnabled(false);
            email.setEnabled(false);

        }
        catch (SQLException e){
        e.printStackTrace();
        }
    }

    public void setVisible(View v){
        fname.setEnabled(true);
        mname.setEnabled(true);
        lname.setEnabled(true);
        db.setEnabled(true);
        email.setEnabled(true);

    }



    public void querying(){

    }

    public void updateData(View view) {
        try {
            SQLiteOpenHelper sqLiteOpenHelper = new Connecting(this);

            SQLiteDatabase lite = sqLiteOpenHelper.getReadableDatabase();
            String f = fname.getText().toString();
            String m = mname.getText().toString();
            String l = lname.getText().toString();
            String d = db.getText().toString();
            String e = email.getText().toString();
            ContentValues cv = new ContentValues();
            cv.put("Firstname",f);
            cv.put("Middlename",m);
            cv.put("Lastname",l);
            cv.put("Dob",d);
            cv.put("Email",e);


            int cursor = lite.update("My_Table",cv,"id=?", new String[]{String.valueOf(id)});
            if (cursor == 1) {
                Toast.makeText(this, "Updated Sucessfully", Toast.LENGTH_SHORT).show();

            }

        }
        catch (SQLException e){
            e.printStackTrace();
        }


    }
}