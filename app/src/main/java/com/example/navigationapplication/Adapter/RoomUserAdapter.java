package com.example.navigationapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationapplication.Listener;
import com.example.navigationapplication.R;
import com.example.navigationapplication.Room.User;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class RoomUserAdapter extends RecyclerView.Adapter<RoomUserAdapter.viewholder> {
    Context context;
    ArrayList<User> userlist ;
    Listener listener;

    public RoomUserAdapter(Context context, ArrayList<User> userlist, Listener listener) {
        this.context = context;
        this.userlist = userlist;
        this.listener = listener;
    }

    public void addusers(User user) {
        userlist.add(user);
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
        int p=position;
        User user = userlist.get(position);
        holder.name.setText(user.getName());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onUpdate(p);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDelete(p);
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

        public viewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_row);
            edit = itemView.findViewById(R.id.updateicon);
            delete = itemView.findViewById(R.id.deleteicon);
        }
    }
}
