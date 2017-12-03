package com.tactfactory.tasker.repository.fixture;

import android.content.Context;
import android.util.Log;

import com.tactfactory.tasker.entity.Task;
import com.tactfactory.tasker.repository.TaskRepository;

public abstract class TaskFixture {
    private static final String TAG = TaskFixture.class.getSimpleName();

    public static void add(Context context) {
        Log.d(TAG, "Add Tasks Fixture...");
        TaskRepository repository = new TaskRepository(context);

        for (int i = 0 ; i < 10 ; i++) {
            Task task = new Task("test " + String.valueOf(i));
            repository.persist(task);

            SubTaskFixture.add(context, task);
        }
    }
}
