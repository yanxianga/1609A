package com.bawie.shopmkk.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.bawie.shopmkk.R;
import com.bawie.shopmkk.adapter.MyAdapter;
import com.bawie.shopmkk.bean.JsonBean;
import com.bawie.shopmkk.di.Contart.IContart;
import com.bawie.shopmkk.di.presenter.IGouPersenter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements IContart.IGouView ,CompoundButton.OnCheckedChangeListener{


    @butterknife.BindView(R.id.rv_business)
    android.support.v7.widget.RecyclerView rvBusiness;
    @butterknife.BindView(R.id.cb_all)
    CheckBox cbAll;
    @butterknife.BindView(R.id.btn_price)
    Button btnPrice;
    @butterknife.BindView(R.id.tv_count)
    TextView tvCount;
    butterknife.Unbinder unbinder;
    private IGouPersenter gouPersenter;
    private List<JsonBean.DataBean> list;
    private MyAdapter adapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = butterknife.ButterKnife.bind(this, view);

        gouPersenter = new IGouPersenter();
        gouPersenter.attach(this);
        gouPersenter.requestdata();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void ShowData(JsonBean jsonBean) {
        cbAll.setOnCheckedChangeListener(null);

        cbAll.setOnCheckedChangeListener(this);

        list = jsonBean.getData();

        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvBusiness.setLayoutManager(manager);
        //Log.e("yxx","shuju"+list);
        adapter = new MyAdapter(R.layout.zhanshi, list);
        rvBusiness.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        gouPersenter.deatch(this);
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
