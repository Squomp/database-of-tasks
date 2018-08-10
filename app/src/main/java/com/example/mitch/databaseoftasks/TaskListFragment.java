package com.example.mitch.databaseoftasks;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class TaskListFragment extends ListFragment {

    ArrayList<String> tasks = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, tasks);
        setListAdapter(adapter);

        return inflater.inflate(R.layout.fragment_task_list, container, false);
    }

    public void addTask(View view){

    }
}
