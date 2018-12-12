package com.example.utsav.qrmul;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ExampleDB extends SQLiteOpenHelper {
    private static final String DATABASE = "sample_db";
    private static final String TABLE_NAME = "mul_qrcode";
    public static final String CODE1 = "code1";
    public ExampleDB(Context context) {

        super(context, DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table " + TABLE_NAME + "(" + CODE1 +" TEXT" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void insert(String s1) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CODE1, s1);
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public ArrayList<String> getData() {
        ArrayList arr1 = new ArrayList();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor != null && cursor.moveToFirst()) {

            while (!cursor.isAfterLast()) {
                arr1.add(cursor.getString(0));
                cursor.moveToNext();
            }
        }
        return arr1;


    }
}