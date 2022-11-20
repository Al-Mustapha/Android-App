package com.example.appdesign;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

public class Pizza {

    private String name;
    private byte[] images;

    public Pizza(String name, byte[] images) {
        this.name = name;
        this.images = images;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImages(byte[] images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public byte[] getImages() {
        return images;
    }


    public byte[] bitmaptoString(Bitmap bitmap) {


            ByteArrayOutputStream out = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, out);
            return out.toByteArray();
    }

   public Bitmap stringtoBitmap(byte[] image){
    return BitmapFactory.decodeByteArray(image,0, image.length);


   }

}
