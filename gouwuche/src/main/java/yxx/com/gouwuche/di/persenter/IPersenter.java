package yxx.com.gouwuche.di.persenter;

import java.lang.ref.SoftReference;

import yxx.com.gouwuche.di.contart.IContart;
import yxx.com.gouwuche.di.model.IModel;

public class IPersenter implements IContart.IPersenter<IContart.IContartView> {

    IContart.IContartView iContartView;
    private IModel iModel;
    private SoftReference<IPersenter> softReference;

    @Override
    public void attach(IContart.IContartView iContartView) {
        this.iContartView = iContartView;

        softReference = new SoftReference<>(this);
        iModel = new IModel();
    }

    @Override
    public void deatch(IContart.IContartView iContartView) {
        softReference.clear();
    }

    @Override
    public void requestData() {
        iModel.ContainData(new IContart.IMode.OnCallBack() {
            @Override
            public void OnCallBack(String requestData) {
                iContartView.ShowData(requestData);
            }
        });
    }
}
