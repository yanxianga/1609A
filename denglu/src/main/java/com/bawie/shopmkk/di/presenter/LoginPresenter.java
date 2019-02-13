package com.bawie.shopmkk.di.presenter;

import com.bawie.shopmkk.bean.BeanLogin;
import com.bawie.shopmkk.di.model.LoginModel;
import com.bawie.shopmkk.di.view.LoginView;
import com.bawie.shopmkk.utils.OkHttpUtils;
import com.google.gson.Gson;

import java.util.Map;
/**
 * date:2018/12/5
 * author:KK(别来无恙)
 * function:
 * 1.写出LoginModel和LoginView
 * 2.实现LoginView的有参构造 LoginModel重新创建对象
 * 3.
 */
public class LoginPresenter {
    private LoginModel mLoginModel;
    private LoginView mLoginView;

    public LoginPresenter(LoginView loginView) {
        //View的有参构造  重现new Model层
        mLoginView = loginView;
        mLoginModel = new LoginModel();
    }

    //登录
    public void onSuccess(String url, Map<String,String> map){
        //调用Model的方法
        mLoginModel.onSuccess(url, map, new OkHttpUtils.OkHttpUtilsInterface() {
            @Override
            public void success(String result) {
                if (result != null){
                    //Gson解析
                    BeanLogin beanLogin = new Gson().fromJson(result, BeanLogin.class);
                    //获取他的信息
                    String message = beanLogin.getMessage();
                    mLoginView.onSuccess(message);
                }
            }
            @Override
            public void failed(Exception e) {

            }
        });
    }

}
