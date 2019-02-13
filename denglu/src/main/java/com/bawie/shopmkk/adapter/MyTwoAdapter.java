package com.bawie.shopmkk.adapter;

import android.support.annotation.Nullable;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bawie.shopmkk.R;
import com.bawie.shopmkk.bean.JsonBean;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public  class MyTwoAdapter extends BaseQuickAdapter<JsonBean.DataBean.ListBean,BaseViewHolder> {


    public MyTwoAdapter(int layoutResId, @Nullable List<JsonBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, JsonBean.DataBean.ListBean item) {
        helper.setText(R.id.tv_goodsPrice,"￥："+item.getPrice());
        helper.setText(R.id.tv_goodsTitle,item.getTitle());
        ImageView iv_goodsIcon = helper.getView(R.id.iv_goodsIcon);
        String imagesString = item.getImages();
        String[] imagesStr = imagesString.split("\\|");
        Glide.with(mContext).load(imagesStr[0]).into(iv_goodsIcon);
        CheckBox cb_goods = helper.getView(R.id.cb_goods);
        cb_goods.setChecked(item.getGoodsChecked());
    }

}
