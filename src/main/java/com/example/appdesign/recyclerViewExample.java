package com.example.appdesign;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class recyclerViewExample extends RecyclerView.Adapter<recyclerViewExample.MyViewHolder> {
private ArrayList<Pizza> newList;
private Context context;

    Connecting connecting;

    public recyclerViewExample(Context context, ArrayList<Pizza> newList) {
        this.newList = newList;
        this.context = context;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textView;


        public MyViewHolder(@NonNull final View v) {
            super(v);

            imageView = v.findViewById(R.id.image1);
            textView = v.findViewById(R.id.text1);



        }


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_foods,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    String name = newList.get(position).getName();
    holder.textView.setText(name);

    byte[] image = newList.get(position).getImages();
  //  String string = image.toString();
    Bitmap fo = BitmapFactory.decodeByteArray(image,0,image.length);
    Uri uri = Uri.parse(String.valueOf(image));
  //  holder.imageView.setImageURI(uri);
    
Glide.with(holder.imageView).load(uri).into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return newList.size();
    }


    public Bitmap stringtoBitmap(byte[] image){
        return BitmapFactory.decodeByteArray(image,0, image.length);


    }

    public byte[] bitmaptoString(Bitmap bitmap) {


        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, out);
        return out.toByteArray();
    }

    public Uri getImageUri(Context context, Bitmap bitmap){
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(),bitmap,null,null);
        return Uri.parse(path);

    }



}
