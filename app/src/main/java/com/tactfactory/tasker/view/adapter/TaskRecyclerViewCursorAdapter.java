package com.tactfactory.tasker.view.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tactfactory.tasker.R;
import com.tactfactory.tasker.repository.TaskRepository;

public class TaskRecyclerViewCursorAdapter extends RecyclerView.Adapter<TaskRecyclerViewCursorAdapter.ViewHolder> {

    private Context context;
    private Cursor cursor;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public ViewHolder(View view) {
            super(view);
            this.textView = view.findViewById(R.id.task_label);
        }

        public void bindCursor(Cursor cursor) {
            textView.setText(cursor.getString(
                    cursor.getColumnIndexOrThrow(TaskRepository.COLUMN_NAME)
            ));
        }
    }

    public TaskRecyclerViewCursorAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_task_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        this.cursor.moveToPosition(position);
        holder.bindCursor(this.cursor);
    }

    @Override
    public int getItemCount() {
        return this.cursor.getCount();
    }
}
