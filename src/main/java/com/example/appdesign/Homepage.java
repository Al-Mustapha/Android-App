package com.example.appdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Homepage extends AppCompatActivity {
    int id = 0;
    String userfname = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        TextView textView = findViewById(R.id.textView3);
        Intent intent = getIntent();
        id = intent.getExtras().getInt("id",0);
        userfname = intent.getExtras().getString("userfname");
        textView.setText("Welcome, "+userfname);

    }




    public void profile(View view) {
     Intent intent = new Intent(this,Profile.class);
     intent.putExtra("id",id);
     startActivity(intent);

    }

    public void foods(View view) {
        Intent intent = new Intent(this,Abinci.class);
        startActivity(intent);
    }

    public void beverages(View view) {
    }

    public void contact(View view) {
    }
}