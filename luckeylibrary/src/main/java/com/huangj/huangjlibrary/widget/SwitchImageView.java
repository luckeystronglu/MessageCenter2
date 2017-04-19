package com.huangj.huangjlibrary.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.huangj.huangjlibrary.R;



/**
 * Created by Ken on 2016/10/19.9:22
 */
public class SwitchImageView extends ImageView {

    //当前控件的开关状态
    private boolean isChecked;
    private int checkImage, unCheckImage;

    public SwitchImageView(Context context) {
        this(context, null);
    }

    public SwitchImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwitchImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paresAttr(attrs);
        init();
    }

    /**
     * 解析自定义属性
     * @param attrs
     */
    private void paresAttr(AttributeSet attrs) {
        TypedArray typedArray = getResources().obtainAttributes(attrs, R.styleable.SwitchImageView);
        checkImage = typedArray.getResourceId(R.styleable.SwitchImageView_checked_image, -1);
        unCheckImage = typedArray.getResourceId(R.styleable.SwitchImageView_unchecked_image, -1);
        typedArray.recycle();
    }

    private void init() {
        if(isChecked){
            this.setImageResource(checkImage);
        } else {
            this.setImageResource(unCheckImage);
        }
    }

    public void setChecked(boolean isChecked){
        this.isChecked = isChecked;
        init();
    }

    /**
     * 将当前状态置反
     */
    public void updateChecked(){
        this.isChecked = !this.isChecked;
        init();
    }
}
