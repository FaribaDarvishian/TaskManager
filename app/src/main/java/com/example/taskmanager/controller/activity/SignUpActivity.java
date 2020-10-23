package com.example.taskmanager.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.taskmanager.R;
import com.example.taskmanager.controller.fragment.LoginFragment;
import com.example.taskmanager.controller.fragment.SignUpFragment;

public class SignUpActivity extends AppCompatActivity {

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context,SignUpActivity.class);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .add(R.id.login_container,new SignUpFragment())
                .commit();
    }
}