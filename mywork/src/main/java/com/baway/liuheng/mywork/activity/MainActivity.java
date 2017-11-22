package com.baway.liuheng.mywork.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.baway.liuheng.mywork.R;
import com.baway.liuheng.mywork.adapter.TabAdapter;
import com.baway.liuheng.mywork.fragment.FragmentOne;
import com.baway.liuheng.mywork.fragment.FragmentTwo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private List<Fragment> fragmentList;
    private TabAdapter adapter;
    private TabLayout mTab;
    private ViewPager mVp;
    boolean flag=false;
    private SharedPreferences flag1;
    private TextView mBianji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //加入集合
        initdata();
        //配置适配器
        geteData();
        bianji();
    }

    private void bianji() {
        flag1 = getSharedPreferences("flag", MODE_PRIVATE);
        mBianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.this.flag){
                    mBianji.setText("完成");
                    flag1.edit().putBoolean("layout",flag).commit();
                    MainActivity.this.flag=false;
                    geteData();
                }else {
                    mBianji.setText("编辑");
                    flag1.edit().putBoolean("layout",flag).commit();
                    MainActivity.this.flag=true;
                    geteData();
                }
            }
        });
    }
    private void initView() {
        mTab = (TabLayout) findViewById(R.id.tab);
        mVp = (ViewPager) findViewById(R.id.vp);
        mBianji = (TextView) findViewById(R.id.bianji);
        mBianji.setOnClickListener(this);
    }

    private void initdata() {
        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new FragmentOne());
        fragmentList.add(new FragmentTwo());
    }

    public void geteData() {
        adapter = new TabAdapter(getSupportFragmentManager(), this, fragmentList);
        mVp.setAdapter(adapter);
        mTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTab.setupWithViewPager(mVp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bianji:
                // TODO 17/11/08
                break;
            default:
                break;
        }
    }
}
