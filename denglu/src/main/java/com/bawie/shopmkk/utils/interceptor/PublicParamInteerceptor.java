package com.bawie.shopmkk.utils.interceptor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * date:2018/12/6
 * author:KK(别来无恙)
 * function:
 */
public class PublicParamInteerceptor implements Interceptor {
    //定义一个属性
    private Map<String,String> paramMap;

    public PublicParamInteerceptor(Map<String, String> paramMap) {
        this.paramMap = paramMap;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        //取出request对象判断是get还是post
        Request request = chain.request();
        //取出url网址进行拼接
        String url = request.url().toString();
        //判断get,post
        if(request.method().equalsIgnoreCase("GET")){
            if(paramMap != null && paramMap.size() > 0){
                //StringBuilder来拼接自符串
                StringBuilder stringBuilder = new StringBuilder(url);
                //拼接Key和value值
                for (Map.Entry<String,String> entry: paramMap.entrySet()) {
                    stringBuilder.append("&"+entry.getKey()+"="+entry.getValue());
                }
                //返回自符串
                url = stringBuilder.toString();
                //判断是否有?
                if(!url.contains("?")){
                    url = url.replace("&","?");
                }
                //新建newRequest传入拼接好的url地址
                Request newRequest = request.newBuilder()
                        .url(url)
                        .build();
                //返回response
                return chain.proceed(newRequest);
            }
        }else{
            if(paramMap != null && paramMap.size() >0){
                //创建body对象
                RequestBody body = request.body();
                //判断body是否为空
                if(body != null && body instanceof FormBody){
                    //创建frombody对象
                    FormBody frombody =  (FormBody)body;
                    //获取FormBody.Builder对象
                    FormBody.Builder builder = new FormBody.Builder();
                    //定义HashMap对象因为Key值不能重复
                    HashMap<String, String> hashMap = new HashMap<>();
                    for (int i = 0 ; i < frombody.size() ; i++){
                        builder.add(frombody.encodedName(i),frombody.encodedValue(i));
                        hashMap.put(frombody.encodedName(i),frombody.encodedValue(i));
                    }
                    //把公共请求参数添加到新的body中
                    for (Map.Entry<String,String > entry: paramMap.entrySet()) {
                        if(!hashMap.containsKey(entry.getKey())){
                            builder.add(entry.getKey(),entry.getValue());
                        }
                    }
                    FormBody build = builder.build();
                    Request newRequest = request.newBuilder()
                            .post(build)
                            .url(url)
                            .build();

                    return chain.proceed(newRequest);
                }
            }
        }
        return chain.proceed(request);
    }
}
