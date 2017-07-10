package com.oliveboard.test.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.oliveboard.test.interfaces.ResultReceiverListener;
import com.oliveboard.test.model.CourseResponse;
import com.oliveboard.test.utils.Constants;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by munnaz on 10/7/17.
 */

public class ApiCall {
    public static ApiCall instance;
    private OliveBoardApi oliveBoardApi;

    /*Singleton getInstance created with synchronized to control the access of multiple threads*/
    public static synchronized ApiCall getInstance()
    {
        if(instance==null) {
            instance = new ApiCall();
        }
        return instance;
    }
    private ApiCall() {
        // Implement a method to build your retrofit
        build();
    }
    private void build()
    {
        /*This is a Interceptor use to log the api cal request and response*/
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
         this.oliveBoardApi = retrofit.create(OliveBoardApi.class);
    }

    public void callCourseApi(ResultReceiverListener resultReceiverListener)
    {
        /*Used rx observable with retrofit*/

        Observable<CourseResponse> observable= oliveBoardApi.getCourses();
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                //subscribe on new thread pass data on success and fail when exception occur
        .subscribeOn(Schedulers.newThread())
                .subscribe(resultReceiverListener::onResult,
                        throwable ->{throwable.printStackTrace();
                                      resultReceiverListener.onFailure();});

    }


}
