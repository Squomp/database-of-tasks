package com.example.mitch.databaseoftasks;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void changeFragment(int id){
        Fragment fragment = null;
        if (id == 1) {
            fragment = new TaskListFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.currentFragment, fragment);
            ft.commit();
        } else if (id == 2) {
            fragment = new AddFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.currentFragment, fragment);
            ft.commit();
        } else if (id == 3) {
            fragment = new EditFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.currentFragment, fragment);
            ft.commit();
        }
    }

    public void addOnClick(View view) {
        // save to database

        // switch fragments
        changeFragment(1);
    }
}
