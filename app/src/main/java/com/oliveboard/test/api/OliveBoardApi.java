package com.oliveboard.test.api;

import com.oliveboard.test.model.CourseResponse;


import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by munnaz on 10/7/17.
 */

public interface OliveBoardApi {
/*retrofit interface for getting course data*/
    @GET("hiring/mocks.cgi")
    Observable<CourseResponse> getCourses();
}
