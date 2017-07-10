package com.oliveboard.test.ui;

/**
 * Created by munnaz on 10/7/17.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oliveboard.test.R;
import com.oliveboard.test.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CourseDetailFragment extends Fragment {
    //Declaring the variables
    @BindView(R.id.description_tv) TextView courseDetailTextView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_course_details, container, false);
        /*Bind the view using butter knife*/
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //setting the course details
        courseDetailTextView.setText(getArguments().getString(Constants.BUNDLE_DATA));

    }

    public static CourseDetailFragment instance(String data){
        /*Adding the data to the bundle*/
        CourseDetailFragment tabFragment=new CourseDetailFragment();
        Bundle bundleData=new Bundle();
        bundleData.putString(Constants.BUNDLE_DATA,data);
        tabFragment.setArguments(bundleData);
       return tabFragment;
    }
}
