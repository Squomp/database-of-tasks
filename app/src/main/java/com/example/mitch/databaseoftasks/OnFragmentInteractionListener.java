package com.example.mitch.databaseoftasks;

public interface OnFragmentInteractionListener {
    public void changeFragment(int id, Task task);
    public DBConnection getTaskDB();
}
