package com.example.mitch.databaseoftasks;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener, AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listview = findViewById(android.R.id.list);
        listview.setOnItemClickListener(this);
        changeFragment(1, null);
    }

    @Override
    public void changeFragment(int id, Task task){
        Fragment fragment = null;
        if (id == 1) { // Task List
            fragment = new TaskListFragment();
            //TEMP
            ((TaskListFragment)fragment).getTasks().add(new Task("YEET"));
            /////////////
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.currentFragment, fragment);
            ft.commit();
        } else if (id == 2) { // Add task
            fragment = new AddFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.currentFragment, fragment);
            ft.commit();
        } else if (id == 3 && task != null) { // Edit task
            fragment = new EditFragment();
            Bundle args = new Bundle();
            args.putSerializable("taskToEdit", task);
            fragment.setArguments(args);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.currentFragment, fragment);
            ft.commit();
        } else if (id == 3 && task == null) {
            Toast.makeText(this.getApplicationContext(),"task must not be null to edit task", Toast.LENGTH_LONG).show();
        }
    }

    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        // get item information
        Toast.makeText(this.getApplicationContext(), "Position: " + position + " ID: " + id, Toast.LENGTH_LONG).show();
        // switch fragment
        changeFragment(3, null);
    }

    public void addOnClick(View view) {
        // save to database

        // switch fragments
        changeFragment(1, null);
    }
}
