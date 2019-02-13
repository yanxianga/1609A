package com.bawie.shopmkk.di.model;

import com.bawie.shopmkk.bean.JsonBean;
import com.bawie.shopmkk.di.Contart.IContart;
import com.bawie.shopmkk.utils.Constance;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class IGouModel implements IContart.IGouModel {
    @Override
    public void ContionData(final OnCallBack onCallBack) {
        OkGo.<String>get(Constance.SHOPPINGCART_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String s = response.body().toString();
                Gson gson = new Gson();
                JsonBean bean = gson.fromJson(s, JsonBean.class);
                onCallBack.OnCallBack(bean);
            }
        });
    }
}
