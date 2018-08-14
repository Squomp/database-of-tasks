package com.example.mitch.databaseoftasks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Tasks extends SQLiteOpenHelper{

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "Tasks";
    // Contacts table name
    private static final String TABLE_TASKS = "Tasks";
    // Shops Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_DESC = "desc";
    private static final String KEY_TIME = "time";
    private static final String KEY_COMPLETED = "completed";
    public Tasks(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_TASKS + "("
        + KEY_ID + " INTEGER PRIMARY KEY," + KEY_DESC + " TEXT,"
        + KEY_TIME + " TEXT," + KEY_COMPLETED + " BOOLEAN" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
        // Creating tables again
        onCreate(db);
    }

    // Adding new task
    public void addTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        // Inserting Row
        db.insert(TABLE_TASKS, null, values);
        db.close(); // Closing database connection
    }

    // Getting one task
    public Task getTask(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_TASKS, new String[] { KEY_ID,
                        KEY_DESC, KEY_TIME, KEY_COMPLETED }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Task task = new Task(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getDouble(2), cursor.getInt(3) > 0);
    // return task
        return task;
    }

    // Getting All task
    public List<Task> getAllShops() {
        List<Task> taskList = new ArrayList<Task>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_TASKS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Task task = new Task(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getDouble(2), cursor.getInt(3) > 0);
                // Adding contact to list
                taskList.add(task);
            } while (cursor.moveToNext());
        }
        // return contact list
        return taskList;
    }

    // Updating a task
    public int updateTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DESC, task.getTaskDesc());
        values.put(KEY_TIME, task.getTimeSpent());
        values.put(KEY_COMPLETED, task.isCompleted());
        // updating row
        return db.update(TABLE_TASKS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(task.getID())});
    }

    // Deleting a shop
    public void deleteTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TASKS, KEY_ID + " = ?",
                new String[] { String.valueOf(task.getID()) });
        db.close();
    }
}
