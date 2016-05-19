package com.example.computer.ctw_4_24_16;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "User";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_USER = "user";
    public static final String user_id = "_id";
    public static final String name_column = "name";
    public static final String email_column = "email";
    public static final String password_column = "password";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("create table " + TABLE_USER + "("
                + user_id + " integer primary key autoincrement, "
                + name_column + " text, "
                + email_column + " text, "
                + password_column + " text" +
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DatabaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public long insertUser(User user) {
        ContentValues cv = new ContentValues();
        cv.put(name_column, user.getName());
        cv.put(email_column, user.getemail());
        cv.put(password_column, user.getpassword());
        // return id of new trip
        return getWritableDatabase().insert(TABLE_USER, null, cv);
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_USER, null);
        // loop through all query results
        for (cursor.moveToFirst(); !cursor.isAfterLast();
             cursor.moveToNext()) {
            String name = cursor.getString(1);
            String email = cursor.getString(2);
            String password = cursor.getString(3);

            User user = new User(name, email, password);
            userList.add(user);
        }
        return userList;
    }
}
