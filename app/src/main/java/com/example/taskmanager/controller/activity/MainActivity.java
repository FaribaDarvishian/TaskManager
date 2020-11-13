package com.example.taskmanager.controller.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.taskmanager.R;
import com.example.taskmanager.controller.fragment.LoginFragment;
import com.example.taskmanager.controller.fragment.SignInFragment;


public class MainActivity extends AppCompatActivity
        implements SignInFragment.Callbacks, LoginFragment.Callbacks{
    private LoginFragment mLoginFragment;
    private SignInFragment mSignInFragment;
  //  private UserListFragment mUserListFragment;
private FragmentManager mFragmentManager;
    public static Intent newIntent(Context context){
        Intent intent = new Intent(context,MainActivity.class);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoginFragment = LoginFragment.newInstance();


        FragmentManager fragmentManager = getSupportFragmentManager();


            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, mLoginFragment)
                    .commit();

    }
    @Override
    public void onBackPressed() {
//        if (mFragmentManager.getBackStackEntryCount() > 0)
//            mFragmentManager.popBackStackImmediate();
//        else
            super.onBackPressed();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //MenuInflater inflater = getMenuInflater();
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_login, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_item_setting:
                Toast.makeText(this, "this Feature will be add soon!", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onLoginClicked() {
//        Intent intent = TaskPagerActivity.newIntent(this);
//        startActivity(intent);

    }

    @Override
    public void onSinInClicked() {
        FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, SignInFragment.newInstance())
                    .addToBackStack("SignInFragment")
                    .commit();

    }
    @Override
    public void onBackClicked() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, new LoginFragment())
                .commit();

    }

}