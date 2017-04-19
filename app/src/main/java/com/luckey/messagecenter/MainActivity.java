package com.luckey.messagecenter;

import android.support.v4.view.ViewPager;

import com.huangj.huangjlibrary.base.BaseActivity;

public class MainActivity extends BaseActivity {

    private String[] datas;
    private TabsPagerAdapter tabsPagerAdapter;

    private ViewPager tabsViewpager;
    private TabView tabview;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        initView();
//    }

    @Override
    protected int getContentId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        super.init();
        initView();
    }

    private void initView() {
        tabsViewpager = (ViewPager) findViewById(R.id.news_viewpager);
        tabview = (TabView) findViewById(R.id.tabview);
        datas = new String[]{"安防","车况","服务"};
        tabview.setTabs(datas);
        tabsPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager(),datas);
        tabsViewpager.setAdapter(tabsPagerAdapter);
        tabview.setViewPager(tabsViewpager);
    }
}
