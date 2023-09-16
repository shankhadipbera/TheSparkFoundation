package com.shankha.tsfbankingsystem;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class HistoryDbHelper extends SQLiteOpenHelper {

    private static final String DATABASENAMES="History";
    private static final String KEY_ID = "Id";
    private static final String KEY_SNAME = "SName";
    private static final String KEY_RNAME = "Rname";
    private static final String KEY_TAMMOUNT = "TAmmount";
    private static final String TABLE_NAME_HISTORY = "Transfer_History";
    public HistoryDbHelper(@Nullable Context context) {
        super(context, DATABASENAMES, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME_HISTORY + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_SNAME + " TEXT NOT NULL," + KEY_RNAME + " TEXT NOT NULL," + KEY_TAMMOUNT + " INTEGER NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_HISTORY);
        onCreate(db);
    }

    public void addHistory(String Sname, String Rname, int amount) {
        SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_SNAME, Sname);
            values.put(KEY_RNAME, Rname);
            values.put(KEY_TAMMOUNT, amount);
            db.insert(TABLE_NAME_HISTORY, null, values);
        db.close(); // Close the database properly.
    }

    public ArrayList<Modelhistory> getHistory() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_HISTORY, null);

        ArrayList<Modelhistory> historyDetails = new ArrayList<>();
        while (cursor.moveToNext()) {
            Modelhistory model = new Modelhistory();
            model.id = cursor.getInt(0);
            model.S_name = cursor.getString(1);
            model.R_name = cursor.getString(2);
            model.T_amount = cursor.getInt(3);
            historyDetails.add(model);
        }
        cursor.close();
        return historyDetails;
    }

}
