package com.luckey.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.luckey.messagecenter.R;
import com.luckey.model.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mmmm on 2016/10/26.
 */
public class MsgGridViewAdapter extends BaseAdapter {
    private List<Image> datas;
    private Context context;

    public MsgGridViewAdapter(Context context){
        this.context = context;
        this.datas = new ArrayList<>();
    }

    public void setDatas(List<Image> datas){
        this.datas = datas;
        this.notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_msg_gv,null);
        ImageView iv = (ImageView) convertView.findViewById(R.id.gv_iv);
        Glide.with(context)
                .load(datas.get(position).ImageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.1f)
                .crossFade()
                .into(iv);
        return convertView;
    }
}
