package com.luckey.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.luckey.fragment.PhotoFragment;
import com.luckey.model.Image;

import java.util.List;

/**
 * Created by mmmm on 2016/10/28.
 */
public class MsgViewPagerAdapter extends FragmentPagerAdapter {
    private List<Image> datas;
    public MsgViewPagerAdapter(FragmentManager fm, List<Image> datas) {
        super(fm);
        this.datas = datas;
    }

    @Override
    public Fragment getItem(int position) {
        return PhotoFragment.getInstance(datas.get(position).ImageUrl,position,datas.size());

    }

    @Override
    public int getCount() {
        return datas.size();
    }
}
