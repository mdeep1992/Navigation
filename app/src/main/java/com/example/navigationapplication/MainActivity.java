package com.example.navigationapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavController navCo;
        TextView title;
        title = findViewById(R.id.title);
        Toolbar toolbar;
        BottomNavigationView bottomNavigationView;


        bottomNavigationView = findViewById(R.id.bottom_navi);
        toolbar = findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
//        toolbar.setVisibility(View.GONE);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment);
        navCo = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(bottomNavigationView, navCo);
        navCo.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                if (navDestination.getLabel().equals("callFragment2")) {
                    Log.e("label1", navDestination.getLabel().toString() );
                    title.setText(getResources().getString(R.string.call));
                    toolbar.setVisibility(View.VISIBLE);
                } else if (navDestination.getLabel().equals("homeFragment2")) {
                    Log.e("label2", navDestination.getLabel().toString() );
                    title.setText(getResources().getString(R.string.home));
                    toolbar.setVisibility(View.VISIBLE);
                } else if (navDestination.getLabel().equals("settingFragment2")) {
                    Log.e("label3", navDestination.getLabel().toString() );
                        title.setText(getResources().getString(R.string.setting));
                        toolbar.setVisibility(View.VISIBLE);
                    }
                }

        });

    }



}