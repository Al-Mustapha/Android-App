package com.example.appdesign;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View view) {
        Intent intent = new Intent(this,Homepage.class);
        Connecting conn = new Connecting(this);
        EditText us1 = (EditText) findViewById(R.id.usname);
        EditText ps = (EditText) findViewById(R.id.password);
        String name = us1.getText().toString();
        String word = ps.getText().toString();


        Cursor bool = conn.fetching(name,word);


        if(bool.moveToFirst()){
            int newid = bool.getInt(Integer.parseInt("0"));
            String userfname = bool.getString(Integer.parseInt("1"));
            Toast toast = Toast.makeText(this, "Login Successfull", Toast.LENGTH_SHORT);
            toast.show();
            intent.putExtra("id", newid);
            intent.putExtra("userfname",userfname);

            startActivity(intent);

        } else {
            Toast toast = Toast.makeText(this, "Username or Password Incorrect\nTry Again!", Toast.LENGTH_SHORT);
            toast.show();


        }







    }

    public void signup(View view) {
        Intent intent = new Intent(this,signup.class);
        startActivity(intent);
    }
}