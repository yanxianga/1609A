package yxx.com.gouwuche.ui.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import yxx.com.gouwuche.R;
import yxx.com.gouwuche.data.Bean.GsonBean;

public  class MyTwoAdapter extends BaseQuickAdapter<GsonBean.DataBean.ListBean,BaseViewHolder> {


    public MyTwoAdapter(int layoutResId, @Nullable List<GsonBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GsonBean.DataBean.ListBean item) {
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
