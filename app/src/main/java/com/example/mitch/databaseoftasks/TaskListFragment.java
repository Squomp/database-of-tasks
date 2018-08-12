package com.example.mitch.databaseoftasks;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class TaskListFragment extends ListFragment {

    private ArrayList<String> tasks = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private OnFragmentInteractionListener myListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, tasks);
        setListAdapter(adapter);
        FloatingActionButton btn = view.findViewById(R.id.addButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myListener.changeFragment(2);
            }
        });

        return view;
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
        try {
            myListener = (OnFragmentInteractionListener) childFragment.getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(childFragment.getActivity().toString() + " does not implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        myListener = null;
    }
}
