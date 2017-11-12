package com.example.atriox.guia6_moviles;

/**
 * Created by root on 11-11-17.
 */

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by admin on 16/5/17.
 */

public class SaveImage extends AsyncTask<Bitmap, Void, Boolean> {
    String path;
    String name;
    Context context;
    public SaveImage(Context context, String path, String name){
        this.context    = context;
        this.path       = path;
        this.name       = name;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(context,"Guardando porfavor espere...",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        Toast.makeText(context,"Img :" +name+" guardada en : "+path,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Boolean doInBackground(Bitmap... params) {
        Boolean saved=false;
        for (Bitmap img : params) {
            saved = saveToInternalStorage(img,path,name);
        }
        return saved;
    }

    private Boolean saveToInternalStorage(Bitmap bitmapImage, String Path, String name){
        Boolean saved=false;
        File sdCard = Environment.getExternalStorageDirectory();
        File dir = new File(sdCard.getAbsolutePath() + "/"+path);
        dir.mkdirs();

        FileOutputStream fos1 = null;
        try {
            fos1 = new FileOutputStream(new File(dir,name));
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos1.close();
                saved=true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return saved;
    }
}
