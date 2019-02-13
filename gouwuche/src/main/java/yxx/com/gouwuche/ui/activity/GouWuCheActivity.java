package yxx.com.gouwuche.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yxx.com.gouwuche.R;
import yxx.com.gouwuche.data.Bean.GsonBean;
import yxx.com.gouwuche.di.contart.IContart;
import yxx.com.gouwuche.di.persenter.IPersenter;
import yxx.com.gouwuche.ui.adapter.MyAdapter;

public class GouWuCheActivity extends AppCompatActivity implements IContart.IContartView ,CompoundButton.OnCheckedChangeListener {

    @BindView(R.id.rv_business)
    RecyclerView rvBusiness;
    @BindView(R.id.cb_all)
    CheckBox cbAll;
    @BindView(R.id.btn_price)
    Button btnPrice;
    @BindView(R.id.tv_count)
    TextView tvCount;
    private IPersenter iPersenter;
    private List<GsonBean.DataBean> list;
    private MyAdapter adapter;
    private GouWuCheActivity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gou_wu_che);
        ButterKnife.bind(this);
        mContext = GouWuCheActivity.this;
        iPersenter = new IPersenter();
        iPersenter.attach(this);
        iPersenter.requestData();
    }

    @OnClick(R.id.btn_price)
    public void onViewClicked() {

    }

    @Override
    public void ShowData(String requestData) {
        cbAll.setOnCheckedChangeListener(null);

        cbAll.setOnCheckedChangeListener(this);

        Gson gson = new Gson();
        GsonBean gsonBean = gson.fromJson(requestData, GsonBean.class);
        list = gsonBean.getData();

        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rvBusiness.setLayoutManager(manager);
        //Log.e("yxx","shuju"+list);
        adapter = new MyAdapter(R.layout.zhanshi, list);
        rvBusiness.setAdapter(adapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPersenter.deatch(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        for (int i = 1; i < list.size(); i++) {
            list.get(i).setBusinessChecked(isChecked);
            for (int j = 0; j < list.get(i).getList().size(); j++) {
                list.get(i).getList().get(j).setGoodsChecked(isChecked);
            }

        }
        calculateTotalCount();
        adapter.notifyDataSetChanged();
    }
    private void calculateTotalCount() {
        //对总价进行计算
        double totalCount = 0;
        //外层条目
        for (int i = 0; i < list.size(); i++) {
            //内层条目
            for (int j = 0; j < list.get(i).getList().size(); j++) {
                //判断内层条目是否勾选
                if (list.get(i).getList().get(j).getGoodsChecked() == true){
                    //获取商品数据*商品价格
                    double price = list.get(i).getList().get(j).getPrice();
                    totalCount += price;
                }
            }
        }
        tvCount.setText("总价是："+String.valueOf(totalCount));
    }

}
