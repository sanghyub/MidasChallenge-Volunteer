package com.example.sangh.midasparactice.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.sangh.midasparactice.Fragment.DonationListFragment;
import com.example.sangh.midasparactice.Fragment.VolunteerListFragment;

/**
 * Created by sangh on 2017-05-27.
 */

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    // Count number of tabs
    private int tabCount;

    public TabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        // Returning the current tabs
        switch (position) {
            case 0:
                VolunteerListFragment tabFragment1 = new VolunteerListFragment();
                return tabFragment1;
            case 1:
                DonationListFragment tabFragment2 = new DonationListFragment();
                return tabFragment2;
            case 2:
                DonationListFragment tabFragment3 = new DonationListFragment();
                return tabFragment3;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}