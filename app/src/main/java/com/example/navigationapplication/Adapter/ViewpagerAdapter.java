package com.example.navigationapplication.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.navigationapplication.View.OutgoingcallFragment;
import com.example.navigationapplication.View.incomingcallFragment;

public class ViewpagerAdapter extends FragmentStateAdapter {
    public ViewpagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new incomingcallFragment();
            case 1:
                return new OutgoingcallFragment();
            default:
                return new incomingcallFragment();

        }



    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
