package com.baway.liuheng.mywork.utils;

import com.baway.liuheng.mywork.bean.UserBean;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by lenovo on 2017/11/8.
 */

public interface Apiserver {
    //接口中定义GET的识别路径
    @GET("ting?method=baidu.ting.billboard.billList&type=1&size=10&offset=0")
    Observable<UserBean> gethomes();

    //添加拦截器
    OkHttpClient CLIENT=new OkHttpClient.Builder()
            .addInterceptor(new LoggingInterceptor())
            .build();
    Retrofit RETROFIT=new Retrofit.Builder()
            .baseUrl("http://tingapi.ting.baidu.com/v1/restserver/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(CLIENT)
            .build();
    Apiserver APISERVER=RETROFIT.create(Apiserver.class);

}
