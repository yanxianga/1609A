package yxx.com.gouwuche.di.model;

import android.util.Log;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import yxx.com.gouwuche.data.Constant;
import yxx.com.gouwuche.di.contart.IContart;

public class IModel implements IContart.IMode {
    @Override
    public void ContainData(final OnCallBack onCallBack) {
        OkGo.<String>get(Constant.SHOPPINGCART_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String aaa = response.body().toString();
                Log.e("yxx","数据"+aaa);
                onCallBack.OnCallBack(aaa);
            }
        });
    }
}
