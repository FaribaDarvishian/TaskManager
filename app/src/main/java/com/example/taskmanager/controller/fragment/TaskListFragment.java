package com.example.taskmanager.controller.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.taskmanager.R;
import com.example.taskmanager.model.State;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.TasksRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TaskListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskListFragment extends Fragment {


    public static final String ARGS_STATE = "argsState";
    public static final String ARGS_USERNAME = "argsUsername";

    private State mState;
    private String mUsername;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFloatingActionButtonAdd;
    private LinearLayout mLinearLayout1;
    private LinearLayout mLinearLayout2;
    private TasksRepository mTasksRepository;

    public TaskListFragment() {
        // Required empty public constructor
    }


    public static TaskListFragment newInstance(State state, String username) {
        TaskListFragment fragment = new TaskListFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARGS_STATE, state);
        args.putString(ARGS_USERNAME, username);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mState = (State) getArguments().getSerializable(ARGS_STATE);
            mUsername = getArguments().getString(ARGS_USERNAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);
        findViews(view);
        setListeners();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        switch (getResources().getConfiguration().orientation) {
            case 1:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                break;
            case 2:
                mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                break;
        }
        return view;
    }

    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view_tasks);
        mLinearLayout2 = view.findViewById(R.id.layout2);
        mLinearLayout1 = view.findViewById(R.id.layout1);
        mFloatingActionButtonAdd = view.findViewById(R.id.floating_action_button_add);
    }

    private void setListeners() {

    }

    private class TaskHolder extends RecyclerView.ViewHolder {

        private TextView mTextViewTaskTittle;
        private TextView mTextViewTaskState;
        private TextView mTextViewTaskDate;
        private ImageView mImageViewShareTask;
        private ImageView mImageViewDeleteTask;

        private Task mTask;

        public TaskHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewTaskTittle = itemView.findViewById(R.id.list_row_task_title);
            mTextViewTaskState = itemView.findViewById(R.id.list_row_Task_state);
            mTextViewTaskDate = itemView.findViewById(R.id.text_view_task_date);
            mImageViewShareTask = itemView.findViewById(R.id.image_view_share);
            mImageViewDeleteTask = itemView.findViewById(R.id.image_view_delete_task);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//TODO

                }
            });
            mImageViewShareTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//TODO

                }
            });
            mImageViewDeleteTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mTasksRepository.delete(mTask.getId());
                    updateUI();
                }
            });
        }

        //TODO


        public void bindTask(Task task) {
            mTask = task;
            if (getAdapterPosition() % 2 == 1)
                itemView.setBackgroundColor(Color.GRAY);
            else
                itemView.setBackgroundColor(Color.WHITE);

            mTextViewTaskTittle.setText(task.getTaskTitle());
            mTextViewTaskState.setText(task.getTaskState().toString());
            mTextViewTaskDate.setText(task.getTaskDate().toString());
        }
    }
    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {

        private List<Task> mTasks;

        public void setTasks(List<Task> tasks) {
            mTasks = tasks;
        }

        public TaskAdapter(List<Task> tasks) {
            mTasks = tasks;
        }

        @NonNull
        @Override
        public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.list_row_item, parent, false);

            TaskHolder taskHolder = new TaskHolder(view);

            return taskHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
            Task task = mTasks.get(position);
            holder.bindTask(task);
        }

        @Override
        public int getItemCount() {
            return mTasks.size();
        }
    }
}