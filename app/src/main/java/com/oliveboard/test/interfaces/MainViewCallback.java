package com.oliveboard.test.interfaces;

import com.oliveboard.test.model.CourseResponse;

import java.util.ArrayList;

/**
 * Created by munnaz on 10/7/17.
 */

public interface MainViewCallback {
    /*Presenter to view communication*/
    void onSetData(boolean isError, CourseResponse data);
}
