package com.tactfactory.tasker.repository.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tactfactory.tasker.repository.SubTaskRepository;
import com.tactfactory.tasker.repository.TaskRepository;
import com.tactfactory.tasker.repository.fixture.TaskFixture;

public class DatabaseSQLiteHelper extends SQLiteOpenHelper {
    private static final String TAG = DatabaseSQLiteHelper.class.getSimpleName();

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "tasker_database";

    public Context context;

    public DatabaseSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Create Tables...");
        db.execSQL(TaskRepository.CREATE_TABLE);
        db.execSQL(SubTaskRepository.CREATE_TABLE);

        //db.close();
        //Log.d(TAG, "Add Fixtures...");
        //TaskFixture.add(this.context);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "Delete tables !!!");
        db.execSQL("DROP TABLE IF EXISTS " + TaskRepository.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SubTaskRepository.TABLE_NAME);
        onCreate(db);
    }
}
