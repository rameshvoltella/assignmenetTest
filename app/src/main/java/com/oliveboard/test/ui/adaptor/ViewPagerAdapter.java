package com.oliveboard.test.ui.adaptor;

/**
 * Created by munnaz on 10/7/17.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.oliveboard.test.ui.CourseDetailFragment;

import java.util.ArrayList;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<String>descriptionDataList;
    public ViewPagerAdapter(FragmentManager fm, ArrayList<String>descriptionDataList) {
        super(fm);
        this.descriptionDataList=descriptionDataList;
    }

    @Override
    public Fragment getItem(int position) {
        /*Passing data to the CourseDetailFragment*/
        return  CourseDetailFragment.instance(descriptionDataList.get(position));
    }

    @Override
    public int getCount() {
        return descriptionDataList.size();
    }

}