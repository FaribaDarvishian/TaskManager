package com.example.taskmanager.controller.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.taskmanager.R;
import com.example.taskmanager.controller.fragment.AddTaskFragment;
import com.example.taskmanager.controller.fragment.TaskDetailFragment;
import com.example.taskmanager.controller.fragment.TaskListFragment;
import com.example.taskmanager.model.State;
import com.example.taskmanager.repository.TasksRepository;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class TaskPagerActivity extends AppCompatActivity
        implements TaskDetailFragment.Callbacks, AddTaskFragment.Callbacks  {

    public static final String EXTRA_BUNDLE_USERNAME = "com.example.taskmanager.activity.extraBundleUsername";
    private TaskListFragment mTasksListFragmentDone;
    private TaskListFragment mTasksListFragmentDoing;
    private TaskListFragment mTasksListFragmentTodo;
    private TasksRepository mTasksRepository;
    private String mUsername;
    private ViewPager2 viewPager;

    String[] titles = {"Done", "Doing", "Todo"};
    private FragmentStateAdapter pagerAdapter;

    public static Intent newIntent(Context context, String username) {
        Intent intent = new Intent(context, TaskPagerActivity.class);
        intent.putExtra(EXTRA_BUNDLE_USERNAME, username);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_pager);
        mUsername= getIntent().getStringExtra(EXTRA_BUNDLE_USERNAME);

        mTasksListFragmentDone = TaskListFragment.newInstance(State.DONE, mUsername);
        mTasksListFragmentDoing = TaskListFragment.newInstance(State.DOING, mUsername);
        mTasksListFragmentTodo = TaskListFragment.newInstance(State.TODO, mUsername);

        findViews();
        pagerAdapter = new TaskPagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout_task_state);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(titles[position])
        ).attach();


    }

    private void findViews() {
        viewPager = findViewById(R.id.view_pager_fragments);

    }

    @Override
    public void updateTasksFragment(State taskState, String username) {
        mTasksRepository = TasksRepository.getInstance();
        switch (taskState) {
            case DONE:
                mTasksListFragmentDone.updateUI();
                break;
            case DOING:
                mTasksListFragmentDoing.updateUI();
                break;
            case TODO:
                mTasksListFragmentTodo.updateUI();
                break;
        }
    }

    private class TaskPagerAdapter extends FragmentStateAdapter {
        public TaskPagerAdapter(FragmentActivity fragmentManager) {
            super(fragmentManager);
        }


        @Override
        public int getItemCount() {
            return 3;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
//                    return TasksFragment.newInstance(TaskState.DONE, mUsername);
                    return mTasksListFragmentDone;
                case 1:
//                    return TasksFragment.newInstance(TaskState.DOING, mUsername);
                    return mTasksListFragmentDoing;
                case 2:
                    return mTasksListFragmentTodo;
            }
            return null;
        }
    }
}
