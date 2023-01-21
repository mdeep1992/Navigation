package com.example.navigationapplication.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.navigationapplication.R;
import com.example.navigationapplication.Adapter.ViewpagerAdapter;
import com.google.android.material.tabs.TabLayout;


public class CallFragment extends Fragment {

    ViewpagerAdapter adapter;
    ViewPager2 viewPager2;
    TabLayout tabLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_call, container, false);
        doInitContent(rootview);
        adapter = new ViewpagerAdapter(this.requireActivity());
        viewPager2.setAdapter(adapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.e("position", String.valueOf(tab.getPosition()));
                viewPager2.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });
        return rootview;
    }

    private void doInitContent(View rootview) {
        viewPager2 = rootview.findViewById(R.id.viewpager);
        tabLayout = rootview.findViewById(R.id.tablayout);
    }
}