package com.baway.liuheng.mywork.fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baway.liuheng.mywork.R;
import com.baway.liuheng.mywork.adapter.RecyAdapter;
import com.baway.liuheng.mywork.bean.UserBean;
import com.baway.liuheng.mywork.presenter.RecyPresenter;
import com.baway.liuheng.mywork.view.RecyView;

import java.util.List;

/**
 * Created by lenovo on 2017/11/8.
 */

public class FragmentOne extends Fragment implements RecyView {
    RecyPresenter presenter;
    private View itemview;
    private RecyclerView rlv;
    private TextView delet;
    boolean cb=false;
    private CheckBox mcb;
    private RecyAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        SharedPreferences flag=getActivity().getSharedPreferences("flag", Context.MODE_APPEND);
        boolean layout = flag.getBoolean("layout", false);
        if (layout){
            itemview = View.inflate(getActivity(), R.layout.fragment_one, null);
            RelativeLayout rl=itemview.findViewById(R.id.rl);
            rl.setVisibility(View.VISIBLE);
        }else {
            itemview = View.inflate(getActivity(), R.layout.fragment_one, null);
        }
        presenter=new RecyPresenter(this);
        initview();
        presenter.showRecy();
        return itemview;
    }

    private void initview() {
        rlv = itemview.findViewById(R.id.rlv);
        rlv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mcb = itemview.findViewById(R.id.cb);
        delet = itemview.findViewById(R.id.delet);
    }

    @Override
    public void showRecy(final List<UserBean.SongListEntity> ub) {
        adapter = new RecyAdapter(getActivity(),ub,cb);
        rlv.setAdapter(adapter);

        mcb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            private RecyAdapter adapter1;

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    cb=true;
                }else {
                    cb=false;
                }
                adapter = new RecyAdapter(getActivity(), ub, cb);
                rlv.setAdapter(adapter);
            }
        });
        //删除
        adapter.setOnItemClickListener(new RecyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ub.remove(position);
                adapter.notifyDataSetChanged();
            }
        });

        delet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cb){
                    ub.clear();
                }else {

                }
                adapter.notifyDataSetChanged();
            }
        });


    }
}
