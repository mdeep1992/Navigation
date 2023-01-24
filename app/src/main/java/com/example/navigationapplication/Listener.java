package com.example.navigationapplication;

import com.example.navigationapplication.Room.User;

public interface Listener {
    void onUpdate(int position);
    void onDelete(int position);
    void oncheck(int position);
    void onuncheck(int position);
}
