package com.example.accesscontrolapp;


import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import static com.example.accesscontrolapp.MainActivity4.appNames;

public class MainActivity5 extends AppCompatActivity {

    Button timeButton;
   public int hour, minute;
   public static String dataTime;
   DBHandler dbHandler;


String store;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        timeButton = findViewById(R.id.timeButton);
    }

    public void popTimePicker(View view)
    {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
            {
                hour = selectedHour;
                minute = selectedMinute;

                timeButton.setText(String.format(Locale.getDefault(), "%02d:%02d",hour, minute));
           dataTime    = (String) timeButton.getText();

                Log.e("END TIME:", String.valueOf(dataTime));
          // for(int j=0;j<=MainActivity4.i;j++)
       dbHandler = new DBHandler(MainActivity5.this);
             dbHandler.addNewItem(appNames,dataTime);
         //  }


                Intent intent=new Intent(MainActivity5.this,MainActivity3.class);

               startActivity(intent);

            }
        };

        // int style = AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, /*style,*/ onTimeSetListener, hour, minute, true);

        timePickerDialog.setTitle("Select End Time");
        timePickerDialog.show();



    }

}