package com.huangj.huangjlibrary.util;

import android.graphics.BitmapFactory;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.widget.EditText;

import java.io.IOException;

/**
 * Created by Ken on 2016/10/23.17:27
 * 添加表情
 * 图片添加到EditText
 */
public class BiaoQingUtil {

    /**
     * 添加表情到EditText
     * @param editText
     */
    public static void addBiaoQing(EditText editText, String pname){
        //获得输入框中光标的位置
        int selectionEnd = editText.getSelectionEnd();

        //实现图文混排
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append("[" + pname + "]");
        try {
            spannableStringBuilder.setSpan(
                    new ImageSpan(editText.getContext(), BitmapFactory.decodeStream(editText.getContext().getAssets().open("p/" + pname))),
                    0,
                    spannableStringBuilder.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        //插入表情到指定的位置
        editText.getText().insert(selectionEnd, spannableStringBuilder);
    }
}
