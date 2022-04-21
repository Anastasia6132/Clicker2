package com.example.clicker2;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBUserProgress extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION1=1;
    public static final String DATABASE_NAME1="userProgressDb";
    public static final String TABLE_LOGIN1="userProgress";

    //public static final String KEY_USER_ID="_id";
    public static final String KEY_USER_LOGIN1="login";
    public static final String KEY_USER_BALANCE1="balance";
    public static final String KEY_USER_UPGRADE1="upgr";
    public DBUserProgress(@Nullable Context context) {
        super(context, DATABASE_NAME1, null, DATABASE_VERSION1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_LOGIN1+"("+
                KEY_USER_LOGIN1+" text primary key, "+
                KEY_USER_BALANCE1+" integer ,"+
                KEY_USER_UPGRADE1+" integer, "+
                " FOREIGN KEY ("+KEY_USER_LOGIN1+") REFERENCES contact(login));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists "+ TABLE_LOGIN1);
        onCreate(db);
    }
}
