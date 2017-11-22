package com.baway.liuheng.mywork.presenter;

import com.baway.liuheng.mywork.bean.UserBean;
import com.baway.liuheng.mywork.model.IRecyModel;
import com.baway.liuheng.mywork.model.RecyModel;
import com.baway.liuheng.mywork.view.RecyView;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by lenovo on 2017/11/8.
 */

public class RecyPresenter implements IPresnter<RecyView> {
    WeakReference<RecyView> weakReference;
    IRecyModel model;
    RecyView view;

    public RecyPresenter(RecyView view) {
        this.view = view;
        model=new RecyModel();
        atteach(view);
    }
    public void showRecy(){
        model.recy(new IRecyModel.Callback() {
            @Override
            public void complate(List<UserBean.SongListEntity> ub) {
                weakReference.get().showRecy(ub);
            }
        });
    }
    @Override
    public void atteach(RecyView view) {
        weakReference=new WeakReference<RecyView>(view);
    }

    @Override
    public void deatch() {
        weakReference.clear();

    }
}
