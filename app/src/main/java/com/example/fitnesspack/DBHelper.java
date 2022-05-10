package com.example.fitnesspack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "fitness.db";

    public DBHelper(Context context) {
        super(context, "fitness.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {

        MyDB.execSQL("create table users(username TEXT primary key, name TEXT, email TEXT, age TEXT, gender TEXT, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {

        MyDB.execSQL("drop table if exists users");

    }
    public Boolean insertData(String username, String name, String email, String age, String gender,String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("username", username);
        values.put("name", name);
        values.put("email", email);
        values.put("age", age);
        values.put("gender", gender);
        values.put("password", password);

        long result = MyDB.insert("users", null, values);
        if (result == -1) return false;
        else
            return true;

    }

    public Boolean checkusername(String username) {

        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where username=?", new String[]{username});

        if (cursor.getCount() > 0) {
            return true;
        } else
            return false;

    }
    public Boolean checkusernamepassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where username=? and password=?", new String[]{username,password});

        if (cursor.getCount() > 0) {
            return true;
        } else
            return false;

    }

    public Cursor getdata(String user) {

        SQLiteDatabase MyDB = this.getWritableDatabase();


        Cursor cursor = MyDB.rawQuery("select * from users where username=?", new String[]{user});

        return cursor;

    }

    public Boolean updateuserdata(String username, String name, String email, String age, String gender,String password) {

        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("username", username);
        value.put("name", name);
        value.put("email", email);
        value.put("age", age);
        value.put("gender", gender);
        value.put("password", password);

        Cursor cur = MyDB.rawQuery("select * from users where username=?", new String[]{username});
        if (cur.getCount() > 0) {

            long result = MyDB.update("users", value, "username=?", new String[]{username});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }
    public Boolean deleteuserdata(String username) {

        SQLiteDatabase MyDB = this.getWritableDatabase();

        Cursor cur = MyDB.rawQuery("select * from users where username=?", new String[]{username});
        if (cur.getCount() > 0) {

            long result = MyDB.delete("users", "username=?",  new String[]{username});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }
}
