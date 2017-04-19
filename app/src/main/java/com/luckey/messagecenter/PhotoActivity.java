package com.luckey.messagecenter;

import android.content.Intent;
import android.support.v4.view.ViewPager;

import com.huangj.huangjlibrary.base.BaseActivity;
import com.luckey.adapter.MsgViewPagerAdapter;
import com.luckey.model.Image;

import java.util.List;

/**
 * Created by mmmm on 2016/10/28.
 */
public class PhotoActivity extends BaseActivity {
    private MsgViewPagerAdapter pagerAdapter;

    public ViewPager vp;
    @Override
    protected int getContentId() {
        return R.layout.activity_photo;
    }

    @Override
    protected void init() {
        vp = findViewByIds(R.id.photo_vp);

        Intent intent = getIntent();
        List<Image> datas = (List<Image>) intent.getSerializableExtra("photo");
        int position = intent.getIntExtra("position",0);
        pagerAdapter = new MsgViewPagerAdapter(getSupportFragmentManager(),datas);
        vp.setAdapter(pagerAdapter);
        vp.setCurrentItem(position);
    }
}
