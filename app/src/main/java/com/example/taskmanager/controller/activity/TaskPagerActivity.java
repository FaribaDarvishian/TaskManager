package com.example.taskmanager.controller.activity;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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
import com.example.taskmanager.repository.UserRepository;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class TaskPagerActivity extends AppCompatActivity
        implements TaskDetailFragment.Callbacks, AddTaskFragment.Callbacks  {

    public static final String EXTRA_BUNDLE_USERNAME = "com.example.taskmanager.activity.extraBundleUsername";
    private TaskListFragment mTasksListFragmentDone;
    private TaskListFragment mTasksListFragmentDoing;
    private TaskListFragment mTasksListFragmentTodo;
    private TasksRepository mTasksRepository;
    private UserRepository mUserRepository;
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
        mUserRepository = UserRepository.getInstance();
        mTasksRepository = TasksRepository.getInstance();
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
                    return mTasksListFragmentDone;
                case 1:
                    return mTasksListFragmentDoing;
                case 2:
                    return mTasksListFragmentTodo;
            }
            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (mUserRepository.getUserType(mUsername) != null) {
            switch (mUserRepository.getUserType(mUsername)) {
                case USER:
                    MenuInflater inflater1 = getMenuInflater();
                    inflater1.inflate(R.menu.menu_user_task_pager, menu);
                    return true;
                case ADMIN:
                    MenuInflater inflater = getMenuInflater();
                    inflater.inflate(R.menu.menu_admin_task_pager, menu);
                    return true;
            }

        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_item_clear_tasks:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.delete_question);

                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        switch (mUserRepository.getUserType(mUsername)) {
                            case USER:
                                mTasksRepository.deleteUserTask(mUsername);
                                viewPager.setAdapter(pagerAdapter);
                                break;
                            case ADMIN:
                                mTasksRepository.clearTaskRepository();
                                viewPager.setAdapter(pagerAdapter);
                                break;
                        }
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            case R.id.menu_item_log_out:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
