package com.example.mitch.databaseoftasks;

import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TaskListFragment extends ListFragment {

    private final String TAG = "TaskListFragment";
    private ArrayList<Task> tasks = new ArrayList<>();
    private CustomAdapter adapter;
    private OnFragmentInteractionListener myListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //set view to fragment_task_list and inflate
        final View view = inflater.inflate(R.layout.fragment_task_list, container, false);

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

        DatabaseReference dbTasks = FirebaseDatabase.getInstance().getReference("tasks");
        dbTasks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tasks.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Task task = postSnapshot.getValue(Task.class);
                    tasks.add(task);
                }
                ListView listview = view.findViewById(android.R.id.list);
                adapter = new CustomAdapter(view.getContext(), tasks);
                listview.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        //Get listview and set adapter
//        ListView listview = view.findViewById(android.R.id.list);
//        tasks = new ArrayList<>(myListener.getTaskDB().getAllTasks());
        Toast.makeText(getActivity(), tasks.size() + "", Toast.LENGTH_LONG).show();
//        adapter = new CustomAdapter(view.getContext(), tasks);
//        listview.setAdapter(adapter);

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
