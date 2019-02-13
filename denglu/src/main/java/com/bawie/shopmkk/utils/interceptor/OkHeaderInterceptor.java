package com.bawie.shopmkk.utils.interceptor;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * date:2018/12/6
 * author:KK(别来无恙)
 * function:请求头拦截器
 */
public class OkHeaderInterceptor implements Interceptor {
    private Map<String,String> headers;
    //设置了请求数据

    public OkHeaderInterceptor(Map<String, String> headers) {
        if (headers != null){
            this.headers = headers;
        }
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        //得到原来的请求
        Request request = chain.request();
        //设置builder  方便得到
        Request.Builder builder = request.newBuilder();
        if (headers != null){
            //得到所有的键的值
            Set<String> set = headers.keySet();
            Iterator<String> iterator = set.iterator();
            //遍历
            while (iterator.hasNext()){
                //得到下一个值的对应的键
                String next = iterator.next();
                //使用builder添加头部
                builder.addHeader(next,headers.get(next));
            }
        }
        //得到request
        request = builder.build();
        //进程
        return chain.proceed(request);
    }
}
