package com.example.vaibhav.Pocket_Expenses;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Vaibhav on 06-04-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "personal.db";
    public static final String TABLE_NAME = "pexpense_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "CATEGORY";
    public static final String COL_3 = "DATE";
    public static final String COL_4 = "ITEMNAME";
    public static final String COL_5 = "PRICE";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
           sqLiteDatabase.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,CATEGORY TEXT,DATE TEXT,ITEMNAME TEXT,PRICE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public boolean insertData(String category, String date,String itemName, String itemPrice){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,category);
        contentValues.put(COL_3,date);
        contentValues.put(COL_4,itemName);
        contentValues.put(COL_5,itemPrice);
       long result = sqLiteDatabase.insert(TABLE_NAME,null ,contentValues);
        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public boolean upgradeData(String id, String category, String date, String itemName, String itemPrice){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,category);
        contentValues.put(COL_3,date);
        contentValues.put(COL_4,itemName);
        contentValues.put(COL_5,itemPrice);
        sqLiteDatabase.update(TABLE_NAME,contentValues,"id = ?",new String[]{id});
        return true;
    }


}
