package com.example.appdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void login(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }


    public void signup(View view) {
        EditText first = (EditText)findViewById(R.id.firstname);
        EditText middle = (EditText)findViewById(R.id.firstname2);
        EditText last = (EditText)findViewById(R.id.firstname3);
        EditText dob = (EditText)findViewById(R.id.firstname7);
        EditText email = (EditText)findViewById(R.id.firstname4);
        EditText usname = (EditText)findViewById(R.id.firstname5);
        EditText password = (EditText)findViewById(R.id.firstname6);
        String fname = first.getText().toString();
        String mname = middle.getText().toString();
        String lname = last.getText().toString();
        String dobo = dob.getText().toString();
        String emai = email.getText().toString();
        String user = usname.getText().toString();
        String psw = password.getText().toString();


      Connecting conn = new Connecting(this);
      if(fname.equals("") || lname.equals("") || dobo.equals("") || emai.equals("") || user.equals("") || psw.equals("")) {
          Toast.makeText(this, "Please fill in the required fields", Toast.LENGTH_SHORT).show();

      }
      else {
          Random random = new Random();
          int random1 = random.nextInt(100);
          conn.inserting(random1,fname, mname, lname, dobo, emai, user, psw);
          first.setText("");
          middle.setText("");
          last.setText("");
          dob.setText("");
          email.setText("");
          usname.setText("");
          password.setText("");
          Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show();
      }
    }
}