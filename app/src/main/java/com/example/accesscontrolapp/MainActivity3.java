package com.example.accesscontrolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.app.IntentService;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;


import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class MainActivity3 extends AppCompatActivity {
public Button btn;
public String currentAppNames;
DBHandler dbHandler;
public static String time;
    //HideFunnction hideFunnction;
    public static String currentApp;
    Date currentTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main3);
        Button bn=findViewById(R.id.button);
        Button btnn=findViewById(R.id.button3);
        Button btn=findViewById(R.id.button2);
        dbHandler=new DBHandler(this);

    bn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(MainActivity3.this,MainActivity4.class);

            startActivity(intent);

        }
    })
        ;
    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(MainActivity3.this,MainActivity5.class);

            startActivity(intent);
        }
    });
    btnn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            time=dbHandler.selectItems();
            do{    currentApp=getForegrround();
                showHome();}while (time.equals(currentTime));

         }



    });
        }
public String getForegrround()
{String currentApp = null;
//String packageName = null;
    ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
   List<ActivityManager.RunningAppProcessInfo> tasks = am.getRunningAppProcesses();
   currentApp = tasks.get(0).processName;
  Log.e( "getForegrround: ", currentApp);

    return currentApp; }




       public boolean showHome() {


           Date currentTime  = Calendar.getInstance().getTime();
           String currentApp=getForegrround();
           Intent startMain = new Intent(Intent.ACTION_MAIN);
           startMain.addCategory(Intent.CATEGORY_HOME);
           startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
           startActivity(startMain);


           return true;


       }
    }






