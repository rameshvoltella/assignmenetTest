package com.oliveboard.test.interfaces;

import com.oliveboard.test.model.CourseResponse;

/**
 * Created by munnaz on 10/7/17.
 */

public interface ResultReceiverListener {
    /*Result listener after the api call back occur*/
    void onResult(CourseResponse data);
    void onFailure();
}
