package com.example.chatlink.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.chatlink.Fragment.Add;
import com.example.chatlink.Fragment.Home;
import com.example.chatlink.Fragment.Notification;
import com.example.chatlink.Fragment.Profile;
import com.example.chatlink.Fragment.Search;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    int noOfTabs;

    public ViewPagerAdapter(@NonNull FragmentManager fm, int noOfTabs) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.noOfTabs = noOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            default:
                case 0:
                    return new Home();
            case 1:
                return new Search();
            case 2:
                return new Add();
            case 3:
                return new Notification();
            case 4:
                return new Profile();

        }
    }

    @Override
    public int getCount() {
        return noOfTabs;
    }
}
