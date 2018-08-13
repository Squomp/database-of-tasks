package com.example.mitch.databaseoftasks;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class EditFragment extends Fragment {

    private OnFragmentInteractionListener myListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // get task that was passed through
        Bundle args = getArguments();
        Task task = (Task)args.getSerializable("taskToEdit");

        View view = inflater.inflate(R.layout.fragment_edit, container, false);

        //initialize instance of interface so creating the onClick doesn't break on null
        myListener = (OnFragmentInteractionListener) this.getActivity();

        // set edittext to task desc
        EditText taskDesc = view.findViewById(R.id.nameEditText);
        taskDesc.setText(task.getTaskDesc());

        //set completed status
        CheckBox isComplete = view.findViewById(R.id.completeBox);
        isComplete.setChecked(task.isCompleted());

        // set total time spent
        TextView totalTime = view.findViewById(R.id.timeSpentTextView);
        totalTime.setText("Total time spent: " + task.getTimeSpent());

        // TODO make timer and set currentTimeTextView to display it

        //Get Save button
        Button btn = view.findViewById(R.id.saveBtn);
        //Create onClick to call changeFragment
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myListener.changeFragment(1, null);
            }
        });

        return view;
    }
}
