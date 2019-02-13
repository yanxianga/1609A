package com.bawie.shopmkk;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bawie.shopmkk.ui.adapter.ViewAdapter;

public class MainActivity extends AppCompatActivity {

    private UserViewPager viewpager;
    private RadioButton homeMain;
    private RadioButton circleMain;
    private RadioButton shopcartMain;
    private RadioButton orderMain;
    private RadioButton mineMain;
    private RadioGroup radio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
        //初始化数据
        initData();
        //设置监听器
        initListener();

    }

    private void initListener() {
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }
            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        homeMain.setChecked(true);
                        break;
                    case 1:
                        circleMain.setChecked(true);
                        break;
                    case 2:
                        shopcartMain.setChecked(true);
                        break;
                    case 3:
                        orderMain.setChecked(true);
                        break;
                    case 4:
                        mineMain.setChecked(true);
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.homeMain:
                        viewpager.setCurrentItem(0);
                        break;
                    case R.id.circleMain:
                        viewpager.setCurrentItem(1);
                        break;
                    case R.id.shopcartMain:
                        viewpager.setCurrentItem(2);
                        break;
                    case R.id.orderMain:
                        viewpager.setCurrentItem(3);
                        break;
                    case R.id.mineMain:
                        viewpager.setCurrentItem(4);
                        break;
                }
            }
        });

    }



    private void initData() {
        //创建ViewPagerAdapter
        ViewAdapter viewAdapter = new ViewAdapter(getSupportFragmentManager());
        viewpager.setAdapter(viewAdapter);
    }
    private void initView() {
        viewpager = (UserViewPager) findViewById(R.id.viewpager);
        homeMain = (RadioButton) findViewById(R.id.homeMain);
        circleMain = (RadioButton) findViewById(R.id.circleMain);
        shopcartMain = (RadioButton) findViewById(R.id.shopcartMain);
        orderMain = (RadioButton) findViewById(R.id.orderMain);
        mineMain = (RadioButton) findViewById(R.id.mineMain);
        radio = (RadioGroup) findViewById(R.id.radio);
    }
}
