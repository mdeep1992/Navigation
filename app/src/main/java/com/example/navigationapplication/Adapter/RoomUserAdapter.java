package com.example.navigationapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationapplication.CheckListener;
import com.example.navigationapplication.Listener;
import com.example.navigationapplication.R;
import com.example.navigationapplication.Room.User;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

public class RoomUserAdapter extends RecyclerView.Adapter<RoomUserAdapter.viewholder> {
    Context context;
    ArrayList<User> userlist;
    Listener listener;

    public RoomUserAdapter(Context context, ArrayList<User> userlist, Listener listener) {
        this.context = context;
        this.userlist = userlist;
        this.listener = listener;
    }
    public void setfilteredlist(  ArrayList<User> filteredlist){
        this.userlist=filteredlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RoomUserAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomUserAdapter.viewholder holder, int position) {
        int p = position;
        User user = userlist.get(position);
        holder.name.setText(user.getName());
        holder.edit.setOnClickListener(view -> listener.onUpdate(p));
        holder.delete.setOnClickListener(view -> listener.onDelete(p));
//        holder.checkBox.setChecked(false);
        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
            listener.oncheck(p);
           }else{
              listener.onuncheck(p);
            }
        });



    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        ImageView edit, delete;
        MaterialTextView name;
        CheckBox checkBox;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_row);
            edit = itemView.findViewById(R.id.updateicon);
            delete = itemView.findViewById(R.id.deleteicon);
            checkBox = itemView.findViewById(R.id.checkbox);
        }
    }
}
