package com.vjsm.sports.tourantshub;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class User_Post extends Fragment {


    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.Cricket:
                    Fragment fragment=new Cricket_List();
                    loadFragment(fragment);
                    return true;
                case R.id.VolleyBall:
                    Fragment fragment1=new Volleyball_List();
                    loadFragment(fragment1);
                    return true;
                case R.id.Football:
                    Fragment fragment2=new Football_List();
                    loadFragment(fragment2);

                    return true;
                case R.id.Kabaddi:
                    Fragment fragment3=new Kabaddi_List();
                    loadFragment(fragment3);

                    return true;
            }
            return false;
        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.activity_user__post,container,false);

        mTextMessage = (TextView)v. findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView)v. findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Fragment fragment=new Cricket_List();
        loadFragment(fragment);
        return v;
    }

    private boolean loadFragment(Fragment fragment) {

        if (fragment!=null){
           getActivity(). getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameloader, fragment)
                    .commit();
            return true;
        }
        return false;


    }


    }



