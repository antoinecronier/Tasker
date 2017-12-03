package com.tactfactory.tasker.repository.fixture;

import android.content.Context;
import android.util.Log;

import com.tactfactory.tasker.entity.SubTask;
import com.tactfactory.tasker.entity.Task;
import com.tactfactory.tasker.repository.SubTaskRepository;

public abstract class SubTaskFixture {
    private static final String TAG = SubTaskFixture.class.getSimpleName();

    public static void add(Context context, Task task) {
        Log.d(TAG, "Add Sub-Tasks Fixture...");
        SubTaskRepository repository = new SubTaskRepository(context);

        for (int i = 0 ; i < 10 ; i++) {
            repository.persist(new SubTask("test " + String.valueOf(i), task));
        }
    }
}
