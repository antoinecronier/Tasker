package com.tactfactory.tasker.view;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tactfactory.tasker.R;
import com.tactfactory.tasker.entity.Task;
import com.tactfactory.tasker.repository.TaskRepository;
import com.tactfactory.tasker.repository.fixture.TaskFixture;
import com.tactfactory.tasker.view.adapter.TaskRecyclerViewCursorAdapter;

/**
 * A placeholder fragment containing a simple view.
 */
public class TaskListActivityFragment extends Fragment {
    private RecyclerView list;

    public TaskListActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_task_list, container, false);
        this.list = view.findViewById(R.id.task_list);

        TaskFixture.add(this.getContext());

        TaskRepository repository = new TaskRepository(this.getContext());
        final Cursor cursor = repository.findAll();

        this.list.setAdapter(new TaskRecyclerViewCursorAdapter(this.getContext(), cursor));
        this.list.setLayoutManager(new LinearLayoutManager(this.getContext()));
        this.list.addOnItemTouchListener(new RecyclerTouchListener(this.getContext(),
                this.list, new RecyclerTouchListener.ClickListener() {

                    @Override
                    public void onClick(View view, final int position) {
                        cursor.moveToPosition(position);
                        startSubTask(TaskRepository.loadFromCursor(cursor));
                    }

                    @Override
                    public void onLongClick(View view, int position) {
                        cursor.moveToPosition(position);
                        removeTask(TaskRepository.loadFromCursor(cursor));
                    }

                }));
        return view;
    }

    public void addTask() {
        this.startSubTask(new Task("New task"));
    }

    public void removeTask(Task task) {

    }

    private void startSubTask(final Task task) {
        Intent intent = new Intent(this.getContext(), SubTaskListActivity.class);
        intent.putExtra(SubTaskListActivityFragment.ENTITY_DATA, task);
        this.getActivity().startActivity(intent);
    }
}
