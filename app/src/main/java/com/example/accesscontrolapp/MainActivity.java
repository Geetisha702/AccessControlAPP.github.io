package com.example.accesscontrolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Handler;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    public Button btn;
    public ProgressBar progressBar;
    public int a=0;
    Handler handler=new Handler();
Date currentTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar=findViewById(R.id.pgb);
        Button button=findViewById(R.id.bn);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MainActivity2.class);

                a=progressBar.getProgress();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(a<100)
                        {a+=1;
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setProgress(a);
                                if(a==100)
                                {  startActivity(intent);}
                            }
                        });
                        try{
                        Thread.sleep(10);}
                        catch (InterruptedException ex)
                        {ex.printStackTrace();}
                        }
                    }
                }).start();
            }
        });
    }
}