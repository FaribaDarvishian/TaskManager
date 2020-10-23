package com.example.taskmanager.controller.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.taskmanager.R;
import com.example.taskmanager.controller.activity.SignUpActivity;
import com.example.taskmanager.controller.activity.TaskPagerActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    private Button mButtonLogin;
    private Button mButtonSignUp;


    public LoginFragment() {
        // Required empty public constructor
    }


    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_login, container, false);

        findViews(view);
        setListeners();
        return view;
    }
    private void findViews(View view){
        mButtonLogin = view.findViewById(R.id.button_login);
       mButtonSignUp= view.findViewById(R.id.button_sign_in);

    }
    private void setListeners(){
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = TaskPagerActivity.newIntent(getContext());
                startActivity(intent);
            }
        });
        mButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = SignUpActivity.newIntent(getContext());
                startActivity(intent);
            }
        });
    }
}