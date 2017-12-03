package com.tactfactory.tasker.view;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.tactfactory.tasker.R;
import com.tactfactory.tasker.entity.Task;
import com.tactfactory.tasker.repository.SubTaskRepository;
import com.tactfactory.tasker.repository.TaskRepository;
import com.tactfactory.tasker.view.adapter.SubTaskRecyclerViewCursorAdapter;

/**
 * A placeholder fragment containing a simple view.
 */
public class SubTaskListActivityFragment extends Fragment {
    public static final String ENTITY_DATA = "task";

    private Task task;

    private EditText title;
    private RecyclerView list;

    public SubTaskListActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_subtask_list, container, false);
        this.list = view.findViewById(R.id.subtask_list);
        this.title = view.findViewById(R.id.subtask_title_value);

        this.task = (Task) this.getActivity().getIntent().getSerializableExtra(ENTITY_DATA);
        this.title.setText(this.task.getName());

        SubTaskRepository repository = new SubTaskRepository(this.getContext());
        Cursor cursor = repository.findAll(this.task);

        this.list.setAdapter(new SubTaskRecyclerViewCursorAdapter(this.getContext(), cursor));
        this.list.setLayoutManager(new LinearLayoutManager(this.getContext()));
        this.list.addOnItemTouchListener(new RecyclerTouchListener(this.getContext(),
                this.list, new RecyclerTouchListener.ClickListener() {

                    @Override
                    public void onClick(View view, final int position) {
                        //Values are passing to activity & to fragment as well
                        Toast.makeText(SubTaskListActivityFragment.this.getContext(), "Single Click on position        :"+position,
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onLongClick(View view, int position) {
                        Toast.makeText(SubTaskListActivityFragment.this.getContext(), "Long press on position :"+position,
                                Toast.LENGTH_LONG).show();
                    }

                }));
        return view;
    }

    public void addSubTask() {
        Toast.makeText(this.getContext(), "Add sub-task", Toast.LENGTH_SHORT).show();
    }

    private void save() {
        this.task.setName(this.title.getText().toString());

        TaskRepository repository = new TaskRepository(this.getContext());
        repository.persist(this.task);
    }

}
