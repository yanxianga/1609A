package com.bawie.shopmkk.utils;

import android.os.Handler;
import android.os.Looper;


import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * date:2018/12/5
 * author:KK(别来无恙)
 * function:
 * 网络工具请求
 */
public class OkHttpUtils {
    private final Handler mHandler;
    private final OkHttpClient mOkHttpClient;
    private static OkHttpUtils sOkHttpUtils;
    private Call mCall;

    private OkHttpUtils(){
        //创建Handler
        mHandler = new Handler(Looper.getMainLooper());
        //拦截器
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(3000, TimeUnit.MILLISECONDS)
                .readTimeout(3000, TimeUnit.MILLISECONDS)
                .writeTimeout(3000, TimeUnit.MILLISECONDS)
                .build();
    }
    //双重锁模式单例
    public static OkHttpUtils getInstance(){
        if (sOkHttpUtils == null){
            synchronized (OkHttpUtils.class){
                if (sOkHttpUtils == null){
                    return sOkHttpUtils = new OkHttpUtils();
                }
            }
        }
        return sOkHttpUtils;
    }
    //接口回调
    public interface OkHttpUtilsInterface{
        void success(String result);
        void failed(Exception e);
    }
    //get
    public void doGet(String url, final OkHttpUtilsInterface okHttpUtilsInterface){
        //创建request
        Request request = new Request.Builder()
                .url(url)
                .build();
        mCall = mOkHttpClient.newCall(request);
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (okHttpUtilsInterface != null){
                            okHttpUtilsInterface.failed(e);
                        }
                    }
                });
            }
        @Override
        public void onResponse(Call call, Response response) throws IOException {
            final String result = response.body().string();
                if (result != null){
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            //判断接口是否为空
                            if (okHttpUtilsInterface != null){
                                okHttpUtilsInterface.success(result);
                            }
                        }
                    });
                }
            }
        });
    }
    //POST
    public void doPost(String url,Map<String ,String> map,final OkHttpUtilsInterface okHttpUtilsInterface){
        FormBody.Builder builder = new FormBody.Builder();
        for (String key:map.keySet()){
            builder.add(key,map.get(key));
        }
        FormBody formBody = builder.build();
        //创建request对象
        Request request = new Request.Builder()
                .post(formBody)
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //判断接口是否为空
                        if (okHttpUtilsInterface != null){
                            okHttpUtilsInterface.failed(e);
                        }
                    }
                });
            }
        @Override
        public void onResponse(Call call, Response response) throws IOException {
            final String result = response.body().string();
                if (result != null){
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            //判断接口是否为空
                            if (okHttpUtilsInterface != null){
                                okHttpUtilsInterface.success(result);
                            }
                        }
                    });
                }
            }
        });
    }
    /**
     * 销毁
     */
    public void onDestroy() {
        if (mCall != null) {
            mCall=null;
        }
        mHandler.removeCallbacksAndMessages(null);
    }


}
