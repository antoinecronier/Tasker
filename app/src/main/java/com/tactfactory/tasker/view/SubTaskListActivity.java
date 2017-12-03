package com.tactfactory.tasker.view;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.tactfactory.tasker.R;

public class SubTaskListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Main View
        this.setContentView(R.layout.activity_subtask_list);
        final SubTaskListActivityFragment fragment = (SubTaskListActivityFragment)this.getSupportFragmentManager().findFragmentById(R.id.fragment);

        // Toolbar
        Toolbar toolbar = this.findViewById(R.id.toolbar);
        //this.setSupportActionBar(toolbar);

        // Floating Action
        FloatingActionButton fab = this.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment.addSubTask();
            }
        });
    }
}
