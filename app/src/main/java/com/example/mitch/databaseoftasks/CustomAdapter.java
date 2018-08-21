package com.example.mitch.databaseoftasks;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
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
        return tasks.size();
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

        TextView timeText = convertView.findViewById(R.id.timeTextView);
        timeText.setText(String.format("%.2f", tasks.get(position).getTimeSpent()) + " hours");

        CheckBox cb = convertView.findViewById(R.id.completedCheckBox);
        cb.setChecked(tasks.get(position).isCompleted());
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Task t = tasks.get(position);
                t.setCompleted(isChecked);
                myListener.getTaskDB().updateTask(t);
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
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        Button btn = convertView.findViewById(R.id.taskEditButton);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // switch fragment
                List<Task> tasks = myListener.getTaskDB().getAllTasks();
                Task taskToEdit = null;
                for (Task t : tasks) {
                    if (t.getStrID().equals(tasks.get(position).getStrID())) {
                        taskToEdit = t;
                    }
                }
                myListener.changeFragment(3, taskToEdit);
            }
        });
        return convertView;
    }
}
