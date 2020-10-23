package com.example.taskmanager.controller.activity;


import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class TaskPagerActivity extends AppCompatActivity {
    public static final String EXTRA_BUNDLE_USERNAME = "com.example.taskmanager.activity.extraBundleUsername";
    public static Intent newIntent(Context context, String username) {
        Intent intent = new Intent(context, TaskPagerActivity.class);
        intent.putExtra(EXTRA_BUNDLE_USERNAME, username);
        return intent;
    }

}