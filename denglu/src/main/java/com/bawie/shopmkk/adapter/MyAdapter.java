package com.bawie.shopmkk.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bawie.shopmkk.R;
import com.bawie.shopmkk.bean.JsonBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


public class MyAdapter extends BaseQuickAdapter<JsonBean.DataBean,BaseViewHolder> {


    private RecyclerView rv_goods;
    private List<JsonBean.DataBean.ListBean> beanList;
    private MyTwoAdapter twoAdapter;

    public MyAdapter(int layoutResId, @Nullable List<JsonBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, JsonBean.DataBean dataBean) {

        helper.setText(R.id.tv_business_name, dataBean.getSellerName());
        rv_goods = helper.getView(R.id.rv_good);

        beanList = dataBean.getList();
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);

        twoAdapter = new MyTwoAdapter(R.layout.shangpin, beanList);
        rv_goods.setLayoutManager(manager);
        rv_goods.setAdapter(twoAdapter);
    }
}
