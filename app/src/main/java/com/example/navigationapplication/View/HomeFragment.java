package com.example.navigationapplication.View;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.navigationapplication.Adapter.RoomUserAdapter;
import com.example.navigationapplication.Listener;
import com.example.navigationapplication.R;
import com.example.navigationapplication.Room.User;
import com.example.navigationapplication.Room.UserDao;
import com.example.navigationapplication.Room.UserDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    FloatingActionButton add_btn, delete_btn;
    RecyclerView recycler;
    ProgressBar progressBar;
    UserDatabase userDatabase;
    UserDao userDao;
    RoomUserAdapter adapter;
    ArrayList<User> userlist = new ArrayList<>();
    ArrayList<User> chlist = new ArrayList<>();
    SearchView searchView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootview = inflater.inflate(R.layout.fragment_home, container, false);
        doInitContent(rootview);

        userDatabase = UserDatabase.getInstance(getContext());
        userDao = userDatabase.userDao();
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filteredlist(newText);
                return true;
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(manager);


        adapter = new RoomUserAdapter(getContext(), userlist, new Listener() {
            @Override
            public void onUpdate(int position) {
                Dialog dialog1 = new Dialog(getContext());
                dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog1.setContentView(R.layout.dialogue_add);
                EditText name_upd = dialog1.findViewById(R.id.ed_name);
                Button okbtn = dialog1.findViewById(R.id.okbtn);
                Button cancelbtn = dialog1.findViewById(R.id.cancelbtn);
                name_upd.setText(userlist.get(position).getName());
                int id = userlist.get(position).getUid();
                okbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (name_upd.getText().toString().trim().isEmpty()) {
                            Toast.makeText(getContext(), "please enter the name", Toast.LENGTH_SHORT).show();
                        } else {
//                          do update name
                            String nameupd = name_upd.getText().toString().trim();

                            userDao.Updateuser(nameupd, id);
                            fetchdata();
                            dialog1.dismiss();
                            Toast.makeText(getContext(), " Name updated successfully", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                cancelbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog1.dismiss();

                    }
                });
                dialog1.show();
            }

            @Override
            public void onDelete(int position) {

                userDao.DeleteUser(userlist.get(position));
                userlist.remove(position);
                adapter.notifyItemRemoved(position);
                Toast.makeText(getContext(), "user deleted successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void oncheck(int position) {
                if(!chlist.contains(userlist.get(position))){
                chlist.add(userlist.get(position));
            }
            }

            @Override
            public void onuncheck(int position) {
                chlist.remove(userlist.get(position));
            }
        });
        recycler.setAdapter(adapter);
        fetchdata();
        adapter.notifyDataSetChanged();
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chlist.isEmpty()){
                    Toast.makeText(getContext(),"select any one",Toast.LENGTH_SHORT).show();
                }else {
                    for (User i : chlist) {
                        userDao.DeleteUser(i);
                    }
                    chlist.clear();
                    fetchdata();
                }
            }
        });

        add_btn.setOnClickListener(view -> {

            Dialog dialog1 = new Dialog(getContext());
            dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog1.setContentView(R.layout.dialogue_add);
            EditText name = dialog1.findViewById(R.id.ed_name);
            Button okbtn = dialog1.findViewById(R.id.okbtn);
            Button cancelbtn = dialog1.findViewById(R.id.cancelbtn);
            okbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (name.getText().toString().trim().isEmpty()) {
                        Toast.makeText(getContext(), "please enter the name", Toast.LENGTH_SHORT).show();
                    } else {
//                          do insert name
                        String username = name.getText().toString().trim();
                        User user = new User();
                        user.setName(username);
                        userDao.InsertUser(user);
                        fetchdata();
                        dialog1.dismiss();
                        Toast.makeText(getContext(), " Name added successfully", Toast.LENGTH_SHORT).show();
                    }

                }
            });
            cancelbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog1.dismiss();

                }
            });
            dialog1.show();
        });

        return rootview;
    }

    private void fetchdata() {
        userlist.clear();
        userlist.addAll(userDao.getAllusers());
        adapter.notifyDataSetChanged();
    }

    private void doInitContent(View rootview) {
        add_btn = rootview.findViewById(R.id.add_btnhome);
        delete_btn = rootview.findViewById(R.id.delete_btnhome);
        recycler = rootview.findViewById(R.id.recycler_home);
        progressBar = rootview.findViewById(R.id.progress_home);
        searchView= rootview.findViewById(R.id.searchview);
    }
      private void filteredlist(String text) {
        ArrayList<User> filteredlist=new ArrayList<>();
        for(User list:userlist){
            if(list.getName().toLowerCase().contains(text.toLowerCase())){
                filteredlist.add(list);
            }
        }
        if(filteredlist.isEmpty()){
            Toast.makeText(getContext(), "no data available", Toast.LENGTH_SHORT).show();
            filteredlist.clear();
            adapter.setfilteredlist(filteredlist);
        }else{
            adapter.setfilteredlist(filteredlist);
        }
    }
}