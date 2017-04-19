package com.luckey.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huangj.huangjlibrary.base.BaseFragment;
import com.luckey.messagecenter.R;

import butterknife.Bind;

/**
 * Created by mmmm on 2016/10/28.
 */
public class PhotoFragment extends BaseFragment {
    private TextView tvNumber,tvNumberc;

    @Bind(R.id.iv_photo)
    public ImageView iv;
    public static PhotoFragment getInstance(String url,int number,int sum){
        PhotoFragment photoFragment = new PhotoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url",url);
        bundle.putInt("number",number);
        bundle.putInt("sum",sum);
        photoFragment.setArguments(bundle);
        return photoFragment;
    }
    @Override
    public int getContentId() {
        return R.layout.msgfragment_photo;
    }

    @Override
    protected void init(View view) {
        tvNumber = (TextView)view.findViewById(R.id.tv_number);
        tvNumberc = (TextView)view.findViewById(R.id.tv_numberc);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        String url = bundle.getString("url");
        int number = bundle.getInt("number") + 1;
        int sum = bundle.getInt("sum");
        tvNumber.setText(number + "");
        tvNumberc.setText(sum + "");
        Glide.with(getActivity()).load(url).into(iv);
    }
}
