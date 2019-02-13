package com.bawie.shopmkk.di.model;

import com.bawie.shopmkk.utils.OkHttpUtils;

import java.util.Map;

/**
 * date:2018/12/5
 * author:KK(别来无恙)
 * function:
 */
public class LoginModel {
    public void onSuccess(String url, Map<String,String> map,OkHttpUtils.OkHttpUtilsInterface okHttpUtilsInterface){
        //获取OkHttpUtils的post方法  传入参数  路径 map集合  接口
        OkHttpUtils.getInstance().doPost(url,map,okHttpUtilsInterface);
    }


}
