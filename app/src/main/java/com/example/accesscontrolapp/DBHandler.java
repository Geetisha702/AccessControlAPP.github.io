package com.example.accesscontrolapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBHandler extends SQLiteOpenHelper {
    MainActivity3 mainActivity3;


        // creating a constant variables for our database.
        // below variable is for our database name.
        private static final String DB_NAME = "appdbb";

        // below int is our database version
        private static final int DB_VERSION = 1;

        // below variable is for our table name.
        private static final String TABLE_NAME = "querytablee";
        private static final String ID_COL = "id";
        // below variable is for our id column.
        private static String select_Apps = "selectedApps";

        // below variable is for our course name column
        private static final String time_to_end= "finishtime";





        // creating a constructor for our database handler.
        public DBHandler(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        // below method is for creating a database by running a sqlite query
        @Override
        public void onCreate(SQLiteDatabase db) {
            // on below line we are creating
            // an sqlite query and we are
            // setting our column names
            // along with their data types.
            String query = "CREATE TABLE " + TABLE_NAME + " ("
                    + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + select_Apps + " TEXT,"
                    + time_to_end  + " TEXT)";

            // at last we are calling a exec sql
            // method to execute above sql query
            db.execSQL(query);
        }

        // this method is use to add new course to our sqlite database.
        public void addNewItem(String appNames, String dataTime) {

            // on below line we are creating a variable for
            // our sqlite database and calling writable method
            // as we are writing data in our database.
            SQLiteDatabase db = this.getWritableDatabase();

            // on below line we are creating a
            // variable for content values.
            ContentValues values = new ContentValues();


            // on below line we are passing all values
            // along with its key and value pair.
            values.put(select_Apps, String.valueOf(appNames));
           // values.put(time_to_end, duration);
            values.put(time_to_end, String.valueOf(dataTime));
            // after adding all values we are passing
            // content values to our table.
            db.insert(TABLE_NAME, null, values);
          db.update(TABLE_NAME,values,"selectedApps=?",new String[]{appNames});
            // at last we are closing our
            // database after adding database.
            Log.e( "addNewItem: ",appNames+dataTime );

        }
        public String selectItems()
        {
           String time="NULL";
            SQLiteDatabase db=this.getReadableDatabase();
          Cursor c= db.rawQuery("SELECT finishtime FROM querytablee WHERE selectedApps= "+MainActivity3.currentApp, null);


          return String.valueOf(c);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // this method is called to check if the table exists already.
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }


