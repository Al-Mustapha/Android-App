package com.example.appdesign;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AddFood extends AppCompatActivity {

    private final int IMAGE_CODE = 1000;
    ImageView imageView;
    TextView textView;
    Bitmap bitmap, bitmap1;
    TextView t1,t2,t3;
    SQLiteOpenHelper sqLiteOpenHelper;
    String path;
    Uri data2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        imageView = findViewById(R.id.selected);
        textView = findViewById(R.id.textView2);
        imageView.setMaxHeight(300);
        imageView.setMaxWidth(300);
        t1 = (TextView) findViewById(R.id.name);
        t2 = (TextView) findViewById(R.id.price);
        t3 = (TextView) findViewById(R.id.description);


    }

    public void open(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,IMAGE_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK){
            if(requestCode==IMAGE_CODE){
                 data2 = data.getData();
                 imageView.setImageURI(data2);

                try {
                    bitmap1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(),data2);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


        }
    }


    public void addtodata(View view) {
        sqLiteOpenHelper = new Connecting(this);
        SQLiteDatabase sqLiteDatabase = sqLiteOpenHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String name = t1.getText().toString();
        int price = Integer.parseInt(String.valueOf(t2.getText()));
        String description = t3.getText().toString();
        String imagename = path;
        cv.put("id",0);
        cv.put("Name",name);
        cv.put("Description",description);
        cv.put("Price",price);
        cv.put("Imagename", String.valueOf(data2));

        sqLiteDatabase.insert("Products_Table",null,cv);



    }
}