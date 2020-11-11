package com.example.sd;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private Button write,read;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        write = findViewById(R.id.write);
        read = findViewById(R.id.read);

        write.setOnClickListener(new View.OnClickListener(){

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                OutputStream out = null;
                try {
                    FileOutputStream fileOutputStream = openFileOutput("ljw",MODE_PRIVATE);
                    out = new BufferedOutputStream(fileOutputStream);
                    String content = "hello ljw!";
                    try{
                        out.write(content.getBytes(StandardCharsets.UTF_8));
                    }
                    finally {
                        if(out!=null)
                            out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputStream in = null;
                try {
                    FileInputStream fileInputStream = openFileInput("ljw");
                    in = new BufferedInputStream(fileInputStream);
                    int temp;
                    StringBuilder builder = new StringBuilder("");
                    try{
                        while ((temp=in.read())!=-1){
                            builder.append((char)temp);
                        }
                        Toast.makeText(MainActivity.this,builder.toString(),Toast.LENGTH_LONG).show();
                    } finally {
                        if(in!=null){
                            in.close();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}