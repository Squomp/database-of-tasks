package com.example.mitch.databaseoftasks;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class AddFragment extends Fragment {

    private OnFragmentInteractionListener myListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragView = inflater.inflate(R.layout.fragment_add, container, false);

        //Set listener
        myListener = (OnFragmentInteractionListener) this.getActivity();

        //Get Save button
        Button btn = fragView.findViewById(R.id.addSaveBtn);
        //Create onClick to call changeFragment
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText desc = fragView.findViewById(R.id.addEditText);
                myListener.getTaskDB().addTask(new Task(desc.getText().toString()));
                myListener.changeFragment(1, null);
            }
        });

        return fragView;
    }
}
