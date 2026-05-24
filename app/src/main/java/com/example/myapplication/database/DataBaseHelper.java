package com.example.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "CampusOasis.db";
    private static final int DATABASE_VERSION = 1;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creates the table with an ID, Name, and Description
        db.execSQL("CREATE TABLE study_spots (ID INTEGER PRIMARY KEY AUTOINCREMENT, SPOT_NAME TEXT, DESCRIPTION TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS study_spots");
        onCreate(db);
    }

    // Method to add a new spot
    public boolean insertSpot(String name, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("SPOT_NAME", name);
        contentValues.put("DESCRIPTION", description);
        long result = db.insert("study_spots", null, contentValues);
        return result != -1;
    }

    // Method to read all spots
    public Cursor getAllSpots() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM study_spots", null);
    }
}