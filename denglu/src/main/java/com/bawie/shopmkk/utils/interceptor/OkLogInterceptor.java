package com.bawie.shopmkk.utils.interceptor;

import android.util.Log;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * date:2018/12/6
 * author:KK(别来无恙)
 * function:日志拦截器
 */
public class OkLogInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        //得到原来的request
        Request request = chain.request();
        //判断方法
        String method = request.method();
        //得到url
        HttpUrl url = request.url();
        Log.d("本次请求","url:"+url.toString()+"===method=="+method);
        Log.d("本次json","json:"+request.toString());

        //找到请求头
        Headers headers = request.headers();
        //获得set集合
        Set<String> names = headers.names();
        //迭代器
        Iterator<String> iterator = names.iterator();
        //遍历
        while (iterator.hasNext()) {
            //得到值
            String next = iterator.next();
            String value = headers.get(next);
            Log.d("你好",next+":"+value);
        }
        //采用了责任链模式  设置了继续请求
        return chain.proceed(request);
    }
}
