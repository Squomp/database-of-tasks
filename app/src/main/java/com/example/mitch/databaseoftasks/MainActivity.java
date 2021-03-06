package com.example.mitch.databaseoftasks;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    private DBConnection taskDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        taskDB = new FirebaseConnection();
        changeFragment(1, null);
    }

    @Override
    public void changeFragment(int id, Task task){
        Fragment fragment = null;
        if (id == 1) { // Task List
            fragment = new TaskListFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, fragment);
            ft.commit();
        } else if (id == 2) { // Add task
            fragment = new AddFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, fragment);
            ft.commit();
        } else if (id == 3 && task != null) { // Edit task
            fragment = new EditFragment();
            Bundle args = new Bundle();
            args.putSerializable("taskToEdit", task);
            fragment.setArguments(args);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, fragment);
            ft.commit();
        } else if (id == 3 && task == null) {
            Toast.makeText(this.getApplicationContext(),"task must not be null to edit task", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public DBConnection getTaskDB() {
        if (taskDB == null) {
            taskDB = new FirebaseConnection();
        }
        return taskDB;
    }
}
