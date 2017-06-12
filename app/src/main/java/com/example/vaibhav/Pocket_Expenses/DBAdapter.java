package com.example.vaibhav.Pocket_Expenses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Vaibhav on 26-04-2017.
 */

public class DBAdapter  {
    public static final String KEY_ROWID = "_id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_WORKOUTDATE = "workoutDate";
    public static final String KEY_EXERCISE_NOTES = "notes";

    private static final String TAG = "WorkoutDBAdapter";
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mdb;

    private static final String DATABASE_NAME = "WorkoutDB";
    private static final String DATABASE_TABLE = "workouts";
    private static final int DATABASE_VERSION = 2;

    private final Context mCtx;

    private static final String DATABASE_CREATE =
            "create table if not exists workouts " +
                    "(_id integer primary key autoincrement, " +
                    "title VARCHAR not null, " +
                    "workoutDate date, " +
                    "notes VARCHAR );";

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.w(TAG, DATABASE_CREATE);
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS workouts");
            onCreate(db);
        }
    }


    public DBAdapter(Context ctx) {
        this.mCtx = ctx;
    }


    public DBAdapter open() throws SQLException {
        mDBHelper = new DatabaseHelper(mCtx);
        mdb = mDBHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDBHelper != null) {
            mDBHelper.close();
        }
    }

    //---insert a record into the database---
    public long insertRecord(String title, String workoutdate, String notes) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_TITLE, title);
        initialValues.put(KEY_WORKOUTDATE, workoutdate);
        initialValues.put(KEY_EXERCISE_NOTES, notes);

        return mdb.insert(DATABASE_TABLE, null, initialValues);
    }


    //---retrieves all the records---
    public Cursor getAllRecords() {
        Cursor mCursor = mdb.query(DATABASE_TABLE, new String[]{KEY_ROWID, KEY_TITLE,
                KEY_WORKOUTDATE, KEY_EXERCISE_NOTES}, null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public boolean deleteRow(long rowId) {
        String where = KEY_ROWID + "=" + rowId;
        return mdb.delete(DATABASE_TABLE, where, null) != 0;

    }
}
