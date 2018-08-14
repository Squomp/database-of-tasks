package com.example.mitch.databaseoftasks;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

public class EditFragment extends Fragment {

    private OnFragmentInteractionListener myListener;
    private long startTime = 0;
    private long endTime = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // get task that was passed through
        Bundle args = getArguments();
        final Task task = (Task)args.getSerializable("taskToEdit");

        View view = inflater.inflate(R.layout.fragment_edit, container, false);

        //initialize instance of interface so creating the onClick doesn't break on null
        myListener = (OnFragmentInteractionListener) this.getActivity();

        // set edittext to task desc
        final EditText taskDesc = view.findViewById(R.id.nameEditText);
        taskDesc.setText(task.getTaskDesc());

        //set completed status
        CheckBox isComplete = view.findViewById(R.id.completeBox);
        isComplete.setChecked(task.isCompleted());

        // set total time spent
        TextView totalTime = view.findViewById(R.id.timeSpentTextView);
        totalTime.setText("Total time spent: " + task.getTimeSpent() + " hours");

        // add listener to checkbox
        CheckBox cb = view.findViewById(R.id.completeBox);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                task.setCompleted(isChecked);
            }
        });

        final Button startTimerBtn = view.findViewById(R.id.startTimerBtn);
        final Button stopTimerBtn = view.findViewById(R.id.stopTimerBtn);
        stopTimerBtn.setEnabled(false);

        // get time elapsed and update DB
        startTimerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTime = System.currentTimeMillis();
                stopTimerBtn.setEnabled(true);
                startTimerBtn.setEnabled(false);
            }
        });

        stopTimerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endTime = System.currentTimeMillis();
                stopTimerBtn.setEnabled(false);
                startTimerBtn.setEnabled(true);
                long diff = endTime - startTime;
                task.setTimeSpent(task.getTimeSpent() + ((diff / 1000) / 60));
            }
        });

        //Get delete button and call remove
        Button deleteBtn = view.findViewById(R.id.deleteButton);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.getTaskDB().deleteTask(task);
                myListener.changeFragment(1, null);
            }
        });

        //Get Save button
        Button btn = view.findViewById(R.id.saveBtn);
        //Create onClick to call changeFragment
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task.setTaskDesc(taskDesc.toString());
                myListener.getTaskDB().updateTask(task);
                myListener.changeFragment(1, null);
            }
        });

        return view;
    }
}
