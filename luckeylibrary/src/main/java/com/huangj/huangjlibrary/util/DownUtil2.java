package com.huangj.huangjlibrary.util;

import android.os.Handler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Ken on 2016/9/9.17:09
 */
public class DownUtil2 {

    /*
    创建一个拥有5个线程的线程池
     */
    private static ExecutorService executor = Executors.newFixedThreadPool(5);

    private OnDownListener2 onDownListener;

    private Handler handler = new Handler();

    //设置解析JSON的接口回调
    public DownUtil2 setOnDownListener2(OnDownListener2 onDownListener) {
        this.onDownListener = onDownListener;
        return this;
    }

    /**
     * 下载JSON数据
     */
    public void downJSON2(final String url){
        executor.submit(new Runnable() {
            @Override
            public void run() {
                //在子线程中执行
                byte[] bytes = HttpUtil.requestURL(url);

                if(bytes != null){
                    String json = new String(bytes);
                    //解析JSON
                    if(onDownListener != null){
                        final Object object = onDownListener.paresJson2(json);
                        //将解析的结果回传给主线程
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //在主线程中执行
                                onDownListener.downSucc2(object);
                            }
                        });
                    }
                }
            }
        });
    }



    /**
     * 接口回调
     */
    public interface OnDownListener2{
        //解析JSON时回调
        Object paresJson2(String json);

        //解析完成后回调
        void downSucc2(Object object);
    }
}
