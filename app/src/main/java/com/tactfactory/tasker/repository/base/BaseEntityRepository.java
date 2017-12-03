package com.tactfactory.tasker.repository.base;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.tactfactory.tasker.entity.base.BaseEntity;

public abstract class BaseEntityRepository<T extends BaseEntity> {
    private static final String TAG = "EntityRepository";

    public static final String COLUMN_ID = "_id";

    protected SQLiteDatabase database;

    public BaseEntityRepository(Context context) {
        this.database = new DatabaseSQLiteHelper(context).getWritableDatabase();
    }

    public void persist(final String table, final T entity, final ContentValues values) {
        if (entity.getId() == 0) {
            Log.d(TAG, "Insert " + entity.getClass().getSimpleName() + " !");
            long newRowId = database.insert(table, null, values);

            if (newRowId > 0) {
                entity.setId(newRowId);
            }
        } else {
            Log.d(TAG, "Update " + entity.getClass().getSimpleName() + " !");
            String[] selectArgs = { String.valueOf(entity.getId()) };
            database.update(table, values, COLUMN_ID + "=?", selectArgs);
        }
    }
}
