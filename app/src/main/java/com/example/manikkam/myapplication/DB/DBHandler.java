package com.example.manikkam.myapplication.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by manikkam on 19/3/18.
 */

public class DBHandler extends SQLiteOpenHelper {

    private final static String TAG="ConnectDB";
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "sample";

    // Contacts table name
    private static final String TABLE_NAME= "jsondata";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE="create table jsondata (id TEXT," +
                "name TEXT,price TEXT,uom TEXT)";
        db.execSQL(CREATE_TABLE);

        Log.d(TAG,"Table Create Successfully");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new Data [Insert Function]
    public void addProductJsonData(String keyId,String keyName,
                                   String keyPrice,String keyUOM) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id", keyId); // Contact Name
        values.put("name", keyName); // ratin
        values.put("price", keyPrice); // datetime
        values.put("uom", keyUOM); // comment

        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
        Log.d(TAG,"Insert Successfully");
    }

    // Getting All Contacts [Get All Records from Table]
    public Cursor getControlData() {
        // Select All Query
        String selectQuery = "SELECT id,name,uom,price FROM jsondata";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // return contact list
        return cursor;
    }

    // Delete Record
    public void deleteAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("delete from jsondata" );
        db.close();
        Log.d(TAG,"Count:"+"Record Deleted");
    }

}
