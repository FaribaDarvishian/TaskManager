package com.example.taskmanager.controller.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.taskmanager.R;

public abstract class SingleFragmentActivity extends AppCompatActivity {

    public abstract Fragment createFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if (fragment== null){
            fragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container,createFragment())
                    .commit();
        } else {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container,createFragment())
                    .commit();
        }
    }
}