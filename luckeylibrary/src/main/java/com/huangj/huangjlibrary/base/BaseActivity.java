package com.huangj.huangjlibrary.base;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.huangj.huangjlibrary.R;
import com.huangj.huangjlibrary.util.ScreenUtil;

import butterknife.ButterKnife;

/**
 * Created by HuangJ on 2016/9/26.15:50
 * Activity的基类；
 */
public abstract class BaseActivity extends AppCompatActivity{

    private FragmentManager fragmentManager;
    //当前正在展示Fragment
    private BaseFragment showFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentId());
        //注册activity;
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();

        //沉浸式状态栏；
        if(isOpenStatus()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
            //获得状态栏的高度；

            int height = ScreenUtil.getStatusHeight(this);
            if(height != -1){
                //设置Padding;
                View view = findViewById(R.id.actionbar);
                if(view != null){
                    view.setPadding(0,height,0,0);
                }

            }
        }

        init();
        bindListener();
        loadDatas();
    }
    /**
     * 初始化；
     */
    protected void init() {
    }

    /**
     * 绑定监听；
     */
    protected void bindListener() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }

    /**
     * 加载数据；
     */
    protected void loadDatas() {

    }
    /**
     * 以动画的方式启动activity
     */
    protected void starActivityForAnimation(Intent intent , int animatinid,int animatoutid){
        startActivity(intent);
        overridePendingTransition(animatinid,animatoutid);
    }
    /**
     * 获得activity显示的布局ID；
     */
    protected abstract int getContentId();

    //是否打开沉浸式状态栏;
    public boolean isOpenStatus(){
        return true;
    }

    protected  <T> T findViewByIds(int resId){
        return (T) findViewById(resId);
    }

    /**
     * 展示Fragment
     */
    protected void showFragment(int resid, BaseFragment fragment){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //隐藏正在展示的fragment；
        if(showFragment != null){
            fragmentTransaction.hide(showFragment);
        }
        //展示对象；
        Fragment fragmentByTag = fragmentManager.findFragmentByTag(fragment.getClass().getName());
        if(fragmentByTag != null){
            fragmentTransaction.show(fragmentByTag);
            showFragment = (BaseFragment) fragmentByTag;
        }else{
            fragmentTransaction.add(resid,fragment,fragment.getClass().getName());
            showFragment = fragment;
        }
        fragmentTransaction.commit();

    }


}
