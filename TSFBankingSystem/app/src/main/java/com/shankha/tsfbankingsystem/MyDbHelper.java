package com.shankha.tsfbankingsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDbHelper extends SQLiteOpenHelper {
    public static final String DATABASENAME = "CustomerInfo";
    private static final String KEY_ID = "Id";
    private static final String KEY_NAME = "Name";
    private static final String KEY_EMAIL = "Email_id";
    private static final String KEY_AMMOUNT = "Ballance";
    private static final String TABLE_NAME_CUSTOMER = "Customer";

    public MyDbHelper(@Nullable Context context) {
        super(context, DATABASENAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME_CUSTOMER + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + KEY_NAME + " TEXT NOT NULL," + KEY_EMAIL + " TEXT NOT NULL," + KEY_AMMOUNT + " INTEGER NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_CUSTOMER);
        onCreate(db);

    }


    public void addCustomer(String name, String email, int amount) {
        SQLiteDatabase db = this.getWritableDatabase();


        if (!isCustomerExists(email, db)) {
            ContentValues values = new ContentValues();
            values.put(KEY_NAME, name);
            values.put(KEY_EMAIL, email);
            values.put(KEY_AMMOUNT, amount);
            db.insert(TABLE_NAME_CUSTOMER, null, values);
        }

        db.close(); // Close the database properly.

    }


    private boolean isCustomerExists(String email, SQLiteDatabase db) {

        Cursor cursor = null;

        try {
            String query = "SELECT * FROM " + TABLE_NAME_CUSTOMER + " WHERE " + KEY_EMAIL + "=?";
            cursor = db.rawQuery(query, new String[]{email});

            return cursor.getCount() > 0;
        } catch (Exception e) {
            // Handle any exceptions here.
            return false;
        } finally {
            if (cursor != null) {
                cursor.close(); // Close the cursor properly.
            }
            // Close the database properly.
        }
    }


    public ArrayList<Model> getCustomer() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_CUSTOMER, null);

        ArrayList<Model> customerDetails = new ArrayList<>();
        while (cursor.moveToNext()) {
            Model model = new Model();
            model.id = cursor.getInt(0);
            model.name = cursor.getString(1);
            model.email = cursor.getString(2);
            model.ballance = cursor.getInt(3);
            customerDetails.add(model);
        }
        cursor.close();
        return customerDetails;
    }

    public void updateBalance(int customerId, int newBalance) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_AMMOUNT, newBalance);
        db.update(TABLE_NAME_CUSTOMER, values, KEY_ID + " = ?", new String[]{String.valueOf(customerId)});
        db.close();
    }

    public ArrayList<String> getAllNamesExceptId(int excludedId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        ArrayList<String> names = new ArrayList<>();
        try {
            String query = "SELECT " + KEY_NAME + " FROM " + TABLE_NAME_CUSTOMER + " WHERE " + KEY_ID + " != ? AND " + KEY_ID + " != -1";
            cursor = db.rawQuery(query, new String[]{String.valueOf(excludedId)});
            while (cursor.moveToNext()) {
                String name = cursor.getString(0);
                names.add(name);
            }
        } catch (Exception e) {
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return names;
    }

    public ArrayList<String> getEmailIdsByName(String nameToFind) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        ArrayList<String> emailIds = new ArrayList<>();
        try {
            String query = "SELECT " + KEY_EMAIL + " FROM " + TABLE_NAME_CUSTOMER + " WHERE " + KEY_NAME + " = ?";
            cursor = db.rawQuery(query, new String[]{nameToFind});
            while (cursor.moveToNext()) {
                String email = cursor.getString(0);
                emailIds.add(email);
            }
        } catch (Exception e) {
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return emailIds;
    }

    public int getIdByEmail(String emilToFind) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        int id = -1;
        try {
            String query = "SELECT " + KEY_ID + " FROM " + TABLE_NAME_CUSTOMER + " WHERE " + KEY_EMAIL + " = ?";
            cursor = db.rawQuery(query, new String[]{emilToFind});
            if (cursor.moveToFirst()) {
                id = cursor.getInt(0);
            }
        } catch (Exception e) {
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return id;
    }


    public int getCustomerBalance(int customerId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        int balance = -1;
        try {
            String query = "SELECT " + KEY_AMMOUNT + " FROM " + TABLE_NAME_CUSTOMER + " WHERE " + KEY_ID + " = ?";
            cursor = db.rawQuery(query, new String[]{String.valueOf(customerId)});

            if (cursor.moveToFirst()) {
                balance = cursor.getInt(0);
            }
        } catch (Exception e) {
            // Handle any exceptions here.
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return balance;
    }


}
