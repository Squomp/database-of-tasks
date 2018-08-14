package com.example.mitch.databaseoftasks;

import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TaskListFragment extends ListFragment {

    private ArrayList<Task> tasks = new ArrayList<>();
    private CustomAdapter adapter;
    private OnFragmentInteractionListener myListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //set view to fragment_task_list and inflate
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);

        //initialize instance of interface so creating the onClick doesn't break on null
        myListener = (OnFragmentInteractionListener) this.getActivity();

        //Get FAB
        FloatingActionButton btn = view.findViewById(R.id.addButton);
        //Create onClick to call changeFragment
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myListener.changeFragment(2, null);
            }
        });

        //Get listview and set clickListener
        ListView listview = view.findViewById(android.R.id.list);

        adapter = new CustomAdapter(view.getContext(), tasks);
        listview.setAdapter(adapter);

        return view;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }
}
