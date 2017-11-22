package com.baway.liuheng.mywork.model;

import com.baway.liuheng.mywork.bean.UserBean;
import com.baway.liuheng.mywork.utils.Apiserver;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lenovo on 2017/11/8.
 */

public class RecyModel implements IRecyModel {
    //Retrofit + Rxjava
    @Override
    public void recy(final Callback callback) {
        Apiserver.APISERVER.gethomes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserBean>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onNext(UserBean userBean) {
                        List<UserBean.SongListEntity> ub=userBean.getSong_list();
                        callback.complate(ub);
                    }
                });
    }
}
