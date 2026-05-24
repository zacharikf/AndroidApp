package com.example.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static  final String DATABASE_NAME = "CampusOasis.db";
    // CHANGED TO VERSION 2 TO TRIGGER THE UPGRADE
    private static final int DATABASE_VERSION = 2;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Added LATITUDE and LONGITUDE as REAL (decimal) numbers
        db.execSQL("CREATE TABLE study_spots (ID INTEGER PRIMARY KEY AUTOINCREMENT, SPOT_NAME TEXT, DESCRIPTION TEXT, LATITUDE REAL, LONGITUDE REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS study_spots");
        onCreate(db);
    }

    // Updated insert method to require coordinates
    public boolean insertSpot(String name, String description, double lat, double lng) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("SPOT_NAME", name);
        contentValues.put("DESCRIPTION", description);
        contentValues.put("LATITUDE", lat);
        contentValues.put("LONGITUDE", lng);
        long result = db.insert("study_spots", null, contentValues);
        return result != -1;
    }

    public Cursor getAllSpots() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM study_spots", null);
    }
}