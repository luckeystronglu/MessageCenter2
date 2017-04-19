package com.huangj.huangjlibrary.util;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Ken on 2016/9/19.14:35
 */
public class HttpUtil {

    /**
     * 下载资源
     * @return
     */
    public static byte[] requestURL(String urlStr){
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(3000);
            conn.connect();
            Log.d("print", "requestURL:链接编码： " + conn.getResponseCode());
            if(conn.getResponseCode() == 200){
                in = conn.getInputStream();
                out = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024 * 8];
                int len;
                while((len = in.read(buffer)) != -1){
                    out.write(buffer, 0, len);
                }
                return out.toByteArray();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(in != null){
                    in.close();
                }
                if(out != null){
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
