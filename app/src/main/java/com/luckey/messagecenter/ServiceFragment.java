package com.luckey.messagecenter;

import android.view.View;
import android.widget.Button;

import com.luckey.base.BaseOFragment;
import com.zqx.chart.anim.Anim;
import com.zqx.chart.data.HistogramData;
import com.zqx.chart.view.Histogram;

/**
 * Created by lenovo on 2016/12/22.
 */
public class ServiceFragment extends BaseOFragment{
    float[] ydata = new float[5];
//    float[] ydata2 = new float[5];
    private Button updateBtn;

    @Override
    public int getContentId() {
        return R.layout.fragment_message_service;
    }

    @Override
    protected void init(View view) {
        update();
        String[] xdata = new String[5];
        for (int i = 0; i < 5; i++) {
            if (i == 0) {
//                xdata[i] = "00:00-01:59" + "\n" +"2017/04/17";
                xdata[i] = "00:00-01:59";
            }
            if (i == 1) {
                xdata[i] = "02:00-03:59";
            }
            if (i == 2) {
                xdata[i] = "04:00-05:59";
            }
            if (i == 3) {
                xdata[i] = "06:00-07:59";
            }
            if (i == 4) {
                xdata[i] = "08:00-09:59";
            }

        }
//        final String[] xdata = new String[]{"06-11","06-12","06-13","06-14","06-15"};
        final Histogram histogramChart = findViewByIds(R.id.histogramchart);
        final HistogramData histogramData = HistogramData.builder()
                .setXdata(xdata)
                .setYdata(ydata)
                .setAnimType(Anim.ANIM_ALPHA)
                .build();
        histogramChart.setChartData(histogramData);

        updateBtn = findViewByIds(R.id.update);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
                histogramData.setYdata(ydata);
                histogramChart.update(histogramData);
            }
        });

//        histogramData.setYdata(ydata2);

    }



    private void update(){
        for (int i=0;i<5;i++){
            ydata[i] = (float) (Math.random() * 100.0f);
        }
    }


}
