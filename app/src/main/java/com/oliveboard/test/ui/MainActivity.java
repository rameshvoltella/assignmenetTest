package com.oliveboard.test.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.oliveboard.test.R;
import com.oliveboard.test.ui.adaptor.ViewPagerAdapter;
import com.oliveboard.test.interfaces.MainViewCallback;
import com.oliveboard.test.model.CourseResponse;
import com.oliveboard.test.presenter.MainPresenterImpl;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by munnaz on 10/7/17.
 */


public class MainActivity extends AppCompatActivity implements MainViewCallback {

    //Declaring the variables

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.loading_view)
    ProgressBar progressLayout;
    @BindView(R.id.message_view)
    LinearLayout reloadMessageLayout;
    @BindView(R.id.reload_iv)
    ImageView reloadImageView;


    MainPresenterImpl presenter;
    private ViewPagerAdapter viewPagerAdapter;
    ArrayList<String> descriptionDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /*Bind the view using butter knife*/
        ButterKnife.bind(this);

        /*Initializing the adaptor and setting the toolbar*/
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), descriptionDataList);
        viewPager.setAdapter(viewPagerAdapter);
        setSupportActionBar(toolbar);

        /*Setting the tabbar selection color and indicator color*/
        tabLayout.setTabTextColors(ContextCompat.getColorStateList(this, R.color.tab_selector));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.indicator));

        /*Setting the viewpager and tab bar change listner*/
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        /*Reload on click reloadImageView*/
        reloadImageView.setOnClickListener(v -> {
            progressLayout.setVisibility(View.VISIBLE);
            reloadMessageLayout.setVisibility(View.GONE);
            presenter.onApiCalled();
        });

        presenter = new MainPresenterImpl(this);
        presenter.onApiCalled();

    }

    /*Callback from the api results*/
    @Override
    public void onSetData(boolean isError, CourseResponse data) {
        if (isError) {
            progressLayout.setVisibility(View.GONE);
            reloadMessageLayout.setVisibility(View.VISIBLE);
        } else {
            progressLayout.setVisibility(View.GONE);
            progressLayout.setVisibility(View.GONE);
            /*clear the old tabs before adding new tabs*/
            if (descriptionDataList.size() > 0) {
                tabLayout.removeAllTabs();
            }
            descriptionDataList.clear();
            /*Adding new Tabs and Course Description*/
            for (int i = 0; i < data.exams.size(); i++) {
                /*Some times api can fail in any chance to avoid the index out of bound exception just checking size is >2*/
                if(data.exams.get(i).size()>1) {
                    tabLayout.addTab(tabLayout.newTab().setText(data.exams.get(i).get(0)));
                    descriptionDataList.add(data.exams.get(i).get(1));
                }
            }

            /*Notify the adaptor viewPagerAdapter with new data*/
            viewPagerAdapter.notifyDataSetChanged();
        }
    }
}