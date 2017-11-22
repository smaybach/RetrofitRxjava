package com.baway.liuheng.mywork.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.baway.liuheng.mywork.presenter.IPresnter;

/**
 * Created by lenovo on 2017/11/8.
 */

public abstract class BaseActivity<T extends IPresnter> extends Activity {
    T presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createPresenter();
    }
    abstract void createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter!=null){
            presenter.deatch();
        }
    }
}
