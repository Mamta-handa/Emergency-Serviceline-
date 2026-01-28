package com.example.emergencyserviceline;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "EmergencyDB";
    private static final int DB_VERSION = 2; // ⬅️ version increased

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE users (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "name TEXT, " +
                        "email TEXT, " +
                        "password TEXT, " +
                        "mobile TEXT, " +
                        "state TEXT, " +
                        "country TEXT, " +
                        "age TEXT, " +
                        "gender TEXT, " +
                        "created_at TEXT" +
                        ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    // INSERT USER WITH DATE TIME
    public boolean insertUser(String name, String email, String password,
                              String mobile, String state, String country,
                              String age, String gender, String createdAt) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("name", name);
        cv.put("email", email);
        cv.put("password", password);
        cv.put("mobile", mobile);
        cv.put("state", state);
        cv.put("country", country);
        cv.put("age", age);
        cv.put("gender", gender);
        cv.put("created_at", createdAt);

        return db.insert("users", null, cv) != -1;
    }

    // GET ALL USERS
    public Cursor getAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM users", null);
    }

    // DELETE USER BY ID
    public boolean deleteUserById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("users", "id=?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    // COUNT USERS
    public int getUserCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM users", null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count;
    }
}
