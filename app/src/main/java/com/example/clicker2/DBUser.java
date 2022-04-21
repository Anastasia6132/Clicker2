package com.example.clicker2;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBUser extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="contactDb";
    public static final String TABLE_LOGIN="contacts";

    //public static final String KEY_USER_ID="_id";
    public static final String KEY_USER_LOGIN="login";
    public static final String KEY_USER_PASSWORD="password";
    public DBUser(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_LOGIN+"("+
                KEY_USER_LOGIN+" text primary key, "+
                KEY_USER_PASSWORD+" text"+")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists "+ TABLE_LOGIN);
        onCreate(db);
    }
}
