package com.example.appdesign;

import static com.example.appdesign.R.id.recycle;
import static com.example.appdesign.R.layout.activity_abinci;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Abinci extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Pizza> newList;


    String name = null;
    int imagename = 0;
    SQLiteOpenHelper sqLiteOpenHelper;
    SQLiteDatabase sqLiteDatabase;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abinci);
        newList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycle);


    SQLiteOpenHelper sqLiteOpenHelper = new Connecting(this);
    SQLiteDatabase sqLiteDatabase = sqLiteOpenHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from Products_Table", null);
        if(cursor.moveToFirst()) {
            do {


                byte[] ima = cursor.getBlob(4);
           //     Bitmap bitmap = (Bitmap)ima.toString();
                Bitmap bitmap = BitmapFactory.decodeByteArray(ima,0,ima.length);
                String gete = cursor.getString(1);


                newList.add(new Pizza(gete,ima));
            }
            while (cursor.moveToNext());

        }



        recyclerViewExample recyclerViewExample = new recyclerViewExample(this,newList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerViewExample);

    }


    public void add(View view) {
        Intent intent = new Intent(this,AddFood.class);
        startActivity(intent);
    }

    public Uri getImageUri(Context context, Bitmap bitmap){
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(),bitmap,null,null);
        return Uri.parse(path);

    }
}
