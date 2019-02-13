package com.bawie.shopmkk.di.presenter;

import com.bawie.shopmkk.bean.JsonBean;
import com.bawie.shopmkk.di.Contart.IContart;
import com.bawie.shopmkk.di.model.IGouModel;

import java.lang.ref.SoftReference;

public class IGouPersenter implements IContart.IGouPersenter<IContart.IGouView> {

    IContart.IGouView iGouView;
    private SoftReference<IGouPersenter> softReference;
    private IGouModel iGouModel;

    @Override
    public void attach(IContart.IGouView iGouView) {
        this.iGouView = iGouView;
        softReference = new SoftReference<>(this);

        iGouModel = new IGouModel();
    }

    @Override
    public void deatch(IContart.IGouView iGouView) {
        softReference.clear();
    }

    @Override
    public void requestdata() {
        iGouModel.ContionData(new IContart.IGouModel.OnCallBack() {
            @Override
            public void OnCallBack(JsonBean jsonBean) {
                iGouView.ShowData(jsonBean);
            }
        });
    }
}
