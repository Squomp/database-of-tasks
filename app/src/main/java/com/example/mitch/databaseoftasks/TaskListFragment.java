package com.example.mitch.databaseoftasks;

import android.app.Activity;
import android.support.v4.app.Fragment;
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

    private ArrayList<String> tasks = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private OnFragmentInteractionListener myListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //set view to fragment_task_list and inflate
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);

        //create adapter for ListView
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, tasks);
        //set adapter
        setListAdapter(adapter);

        //initialize instance of interface so creating the onClick doesn't break on null
        myListener = (OnFragmentInteractionListener) this.getActivity();

        //Get FAB
        FloatingActionButton btn = view.findViewById(R.id.addButton);
        //Create onClick to call changeFragment
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myListener.changeFragment(2);
            }
        });

        //Get listview and set clickListener
        ListView listview = view.findViewById(android.R.id.list);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> l, View v, int position, long id) {
                // get item information
                Toast.makeText(getActivity().getApplicationContext(), "Position: " + position + " ID: " + id, Toast.LENGTH_LONG).show();
                // switch fragment
                myListener.changeFragment(3);
            }
        });

        return view;
    }

//    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
//        // get item information
//        Toast.makeText(getActivity().getApplicationContext(), "Position: " + position + " ID: " + id, Toast.LENGTH_LONG).show();
//        // switch fragment
//        myListener.changeFragment(3);
//    }

    // sets myListener to the activity hosting the new fragment
    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
        try {
            myListener = (OnFragmentInteractionListener) childFragment.getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(childFragment.getActivity().toString() + " does not implement OnFragmentInteractionListener");
        }
    }

    // clears myListener when fragment is detached
    @Override
    public void onDetach() {
        super.onDetach();
        myListener = null;
    }

    public ArrayList<String> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<String> tasks) {
        this.tasks = tasks;
    }
}
