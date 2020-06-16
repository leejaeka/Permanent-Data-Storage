package com.jaekanglee.sqlite_database;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            SQLiteDatabase myDatabase = this.openOrCreateDatabase("Users",MODE_PRIVATE,null);
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(3), id INTEGER PRIMARY KEY)");
            /*myDatabase.execSQL("INSERT INTO users (name,age) VALUES ('Rob', 34)");
            myDatabase.execSQL("INSERT INTO users (name,age) VALUES ('Bob', 4)");
            myDatabase.execSQL("INSERT INTO users (name,age) VALUES ('Cob', 1)");
            myDatabase.execSQL("INSERT INTO users (name,age) VALUES ('Dob', 22)");*/
            Cursor c = myDatabase.rawQuery("SELECT * FROM users", null);
           // Cursor k = myDatabase.rawQuery("SELECT * FROM users WHERE name LIKE '%o%' LIMIT 3", null);
            // DELETING
            //myDatabase.execSQL("DELETE FROM users WHERE name = 'Rob' LIMIT 1");
            // CHANGE
            myDatabase.execSQL("UPDATE users SET age=2 WHERE name='Rob'");
            // WORKING WITH ID AND KEY
            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");
            int idIndex = c.getColumnIndex("id");
            c.moveToFirst();
            while (c!=null) {
                Log.i("name", c.getString(nameIndex));
                Log.i("age", Integer.toString(c.getInt(ageIndex)));
                Log.i("UserResults-id", Integer.toString(c.getInt(idIndex)));
                c.moveToNext();
            }
            c.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        /*try{
            SQLiteDatabase eventsDB = this.openOrCreateDatabase("Events", MODE_PRIVATE,null);
            eventsDB.execSQL("CREATE TABLE IF NOT EXISTS events (event VARCHAR, year INT(4))");
            eventsDB.execSQL("INSERT INTO EVENTS (event, year) VALUES ('END OF WW2', 1945)");
            eventsDB.execSQL("INSERT INTO EVENTS (event, year) VALUES ('WHAM SPLIT UP', 1986)");
            Cursor c = eventsDB.rawQuery("SELECT * FROM events", null);
            int eventIndex = c.getColumnIndex("event");
            int yearIndex = c.getColumnIndex("year");
            c.moveToFirst();
            while(c!=null){
                Log.i("Results-event", c.getString(eventIndex));
                Log.i("Results-year", Integer.toString(c.getInt(yearIndex)));
                c.moveToNext();
            }
        } catch (Exception e){
            e.printStackTrace();
        }*/
    }
}