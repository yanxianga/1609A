package yxx.com.gouwuche.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.CheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import yxx.com.gouwuche.R;
import yxx.com.gouwuche.data.Bean.GsonBean;

public class MyAdapter  extends BaseQuickAdapter<GsonBean.DataBean,BaseViewHolder> {


    private RecyclerView rv_goods;
    private List<GsonBean.DataBean.ListBean> beanList;
    private MyTwoAdapter twoAdapter;

    public MyAdapter(int layoutResId, @Nullable List<GsonBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GsonBean.DataBean dataBean) {

        helper.setText(R.id.tv_business_name, dataBean.getSellerName());
        rv_goods = helper.getView(R.id.rv_good);

        beanList = dataBean.getList();
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);

        twoAdapter = new MyTwoAdapter(R.layout.shangpin, beanList);
        rv_goods.setLayoutManager(manager);
        rv_goods.setAdapter(twoAdapter);
    }
}
