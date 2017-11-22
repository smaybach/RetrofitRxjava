package com.baway.liuheng.mywork.model;

import com.baway.liuheng.mywork.bean.UserBean;

import java.util.List;

/**
 * Created by lenovo on 2017/11/8.
 */

public interface IRecyModel {
    void recy(Callback callback);
    interface Callback{
        void complate(List<UserBean.SongListEntity> ub);
    }
}
