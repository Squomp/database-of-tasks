package com.example.mitch.databaseoftasks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> tasks;
    private LayoutInflater inflater;
    private OnFragmentInteractionListener myListener;

    public CustomAdapter(Context appContext, ArrayList<String> taskList){
        this.context = appContext;
        this.tasks = taskList;
        inflater = (LayoutInflater.from(appContext));

        //initialize instance of interface so creating the onClick doesn't break on null
        myListener = (OnFragmentInteractionListener) context;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.task_list_item, null);

        TextView task = convertView.findViewById(R.id.taskTextView);
        task.setText(tasks.get(position));

        // TODO get time and complete status from DB


        Button btn = convertView.findViewById(R.id.taskEditButton);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // switch fragment
                myListener.changeFragment(3);
            }
        });
        return convertView;
    }




    public ArrayList<String> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<String> tasks) {
        this.tasks = tasks;
    }
}
