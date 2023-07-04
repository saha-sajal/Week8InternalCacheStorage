package com.week6.week8internalcachestorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = findViewById(R.id.editText);

        Button buttonSaveInternal = findViewById(R.id.buttonSaveInternal);
        Button readButtonInternal = findViewById(R.id.buttonReadInternal);


        Button buttonSaveCache = findViewById(R.id.buttonSaveCache);
        Button readButtonCache = findViewById(R.id.buttonReadCache);




        TextView textView = findViewById(R.id.textView);


        String filenameCache = "myfile_cache.txt";
        String filename = "myfile.txt";

        buttonSaveInternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String info = editText.getText().toString();
                storeInternalStorage(filename, info);

            }
        });


        readButtonInternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = readFromInternalStorage(getApplicationContext(), filename);
                textView.setText(data);
            }
        });

        buttonSaveCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String info = editText.getText().toString();
                storeCacheStorage(getApplicationContext(),filenameCache, info);

            }
        });


        readButtonCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = readFromCacheStorage(getApplicationContext(), filenameCache);
                textView.setText(data);
            }
        });
    }

    public void storeInternalStorage(String filename, String info)
    {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = getApplicationContext().openFileOutput(filename,  Context.MODE_APPEND);
            fileOutputStream.write(info.getBytes());
            Log.i("Data:", "Saved");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void storeCacheStorage(Context context, String filename, String info)
    {
        File cacheDir = context.getCacheDir();
        File file = new File(cacheDir, filename);

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(info.getBytes());
            Log.i("Data:", "Cache Saved");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String readFromInternalStorage(Context context, String filename)
    {

        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();


        try {
            fileInputStream = context.openFileInput(filename);
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);

            String line;

            while((line = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return stringBuilder.toString();
    }

    public String readFromCacheStorage(Context context, String filename)
    {


        File cacheDir = context.getCacheDir();
        File file = new File(cacheDir, filename);


        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            fileInputStream = new FileInputStream(file);
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);

            String line;

            while((line = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return stringBuilder.toString();
    }


}