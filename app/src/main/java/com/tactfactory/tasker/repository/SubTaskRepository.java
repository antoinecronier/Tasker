package com.tactfactory.tasker.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.tactfactory.tasker.entity.SubTask;
import com.tactfactory.tasker.entity.Task;
import com.tactfactory.tasker.repository.base.BaseEntityRepository;

public class SubTaskRepository extends BaseEntityRepository<SubTask> {
    private static final String TAG = SubTaskRepository.class.getSimpleName();

    public static final String TABLE_NAME = "app_subtask";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TASK_ID = "task_id";

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT, " + COLUMN_TASK_ID + " INTEGER)";
    private static final String[] PROJECTION = { COLUMN_ID , COLUMN_NAME, COLUMN_TASK_ID};

    public SubTaskRepository(Context context) {
        super(context);
    }

    public void persist(SubTask subTask) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, subTask.getName());
        values.put(COLUMN_TASK_ID, subTask.getOwner().getId());

        this.persist(TABLE_NAME, subTask, values);
    }

    public Cursor findAll(Task task) {
        Log.d(TAG, "Get all Sub-Tasks of Task...");
        String[] selectArgs = { String.valueOf(task.getId()) };

        Cursor cursor = database.query(
                TABLE_NAME,                               // The table to query
                PROJECTION,                               // The columns to return
                COLUMN_TASK_ID + "=?",         // The columns for the WHERE clause
                selectArgs,                               // The values for the WHERE clause
                null,                             // don't group the rows
                null,                              // don't filter by row groups
                null                              // don't sort
        );

        return cursor;
    }
}
