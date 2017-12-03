package com.tactfactory.tasker.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.tactfactory.tasker.entity.Task;
import com.tactfactory.tasker.repository.base.BaseEntityRepository;

public class TaskRepository extends BaseEntityRepository {
    private static final String TAG = TaskRepository.class.getSimpleName();

    public static final String TABLE_NAME = "app_task";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT)";
    private static final String[] PROJECTION = { COLUMN_ID , COLUMN_NAME};

    public TaskRepository(Context context) {
        super(context);
    }

    public void persist(Task task) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, task.getName());

        this.persist(TABLE_NAME, task, values);
    }

    public Cursor findAll() {
        Log.d(TAG, "Get all Tasks...");
        Cursor cursor = database.query(
                TABLE_NAME,                               // The table to query
                PROJECTION,                               // The columns to return
                "",                              // The columns for the WHERE clause
                null,                         // The values for the WHERE clause
                null,                             // don't group the rows
                null,                              // don't filter by row groups
                null                              // don't sort
        );

        return cursor;
    }

    public static Task loadFromCursor(Cursor cursor) {
        Task task = new Task(cursor.getString(
                cursor.getColumnIndexOrThrow(TaskRepository.COLUMN_NAME)
        ));
        task.setId(cursor.getLong(cursor.getColumnIndexOrThrow(TaskRepository.COLUMN_ID)));

        return task;
    }
}
