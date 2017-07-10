package com.oliveboard.test.presenter;

import com.oliveboard.test.api.ApiCall;
import com.oliveboard.test.interfaces.MainViewCallback;
import com.oliveboard.test.interfaces.ResultReceiverListener;
import com.oliveboard.test.model.CourseResponse;

/**
 * Created by munnaz on 10/7/17.
 */

public class MainPresenterImpl implements MainPresenter, ResultReceiverListener {

    private MainViewCallback mainViewCallback;

    public MainPresenterImpl(MainViewCallback mainViewCallback) {
        this.mainViewCallback = mainViewCallback;
    }

    @Override
    public void onApiCalled() {
        ApiCall.getInstance().callCourseApi(this);
    }

    @Override
    public void onResult(CourseResponse data) {
        if (mainViewCallback != null)
            mainViewCallback.onSetData(false, data);
    }

    @Override
    public void onFailure() {
        if (mainViewCallback != null)
            mainViewCallback.onSetData(true, null);
    }
}
