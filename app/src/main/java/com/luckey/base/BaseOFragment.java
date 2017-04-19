package com.luckey.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huangj.huangjlibrary.R;
import com.huangj.huangjlibrary.base.BaseActivity;
import com.huangj.huangjlibrary.util.ScreenUtil;
import com.luckey.socket.SocketAppPacket;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by HuangJ on 2016/9/26.17:23
 */
public abstract class BaseOFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentId(), container, false);
        return view;
    }

    protected <T> T findViewByIds(int resId) {
        return (T) getActivity().findViewById(resId);
    }

    /**
     * 该方法紧跟onCreateView后执行；
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        //查找actionbar控件设置paddingtop
        BaseActivity activity = (BaseActivity) getActivity();
        //判断activity是否开启沉浸式通知栏
        if (activity.isOpenStatus()) {
            View actionbarview = view.findViewById(R.id.actionbar);
            if (actionbarview != null) {
                int heigth = ScreenUtil.getStatusHeight(getActivity());
                actionbarview.setPadding(0, heigth, 0, 0);
            }
        }

        init(view);
        bindListener();
        loadDatas();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            getDatas(bundle);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }


    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(SocketAppPacket eventPackage) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }

    protected void getDatas(Bundle bundle) {

    }

    //初始化
    protected void init(View view) {

    }

    //监听的绑定；
    protected void bindListener() {

    }

    //下载数据；
    protected void loadDatas() {

    }

    //需要显示的布局；
    public abstract int getContentId();

}
