package com.example.mitch.databaseoftasks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Timer;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Task> tasks;
    private LayoutInflater inflater;
    private OnFragmentInteractionListener myListener;

    public CustomAdapter(Context appContext, ArrayList<Task> taskList){
        this.context = appContext;
        this.tasks = taskList;
        inflater = (LayoutInflater.from(appContext));

        //initialize instance of interface so creating the onClick doesn't break on null
        myListener = (OnFragmentInteractionListener) context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.task_list_item, null);

        final TextView task = convertView.findViewById(R.id.taskTextView);
        task.setText(tasks.get(position).getTaskDesc());

        CheckBox cb = convertView.findViewById(R.id.completedCheckBox);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Task t = tasks.get(position);
                t.setCompleted(isChecked);
                myListener.getTaskDB().updateTask(t);
            }
        });

        Button btn = convertView.findViewById(R.id.taskEditButton);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // switch fragment
                myListener.changeFragment(3, myListener.getTaskDB().getTask(tasks.get(position).getID()));
            }
        });
        return convertView;
    }
}
