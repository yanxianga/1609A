package com.bawie.shopmkk.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bawie.shopmkk.R;
import com.bawie.shopmkk.bean.GsonBean;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopcartFragment extends Fragment {


    private MZBannerView mMZBanner;
    private String str;
    private List<GsonBean.ResultBean> list;
    private GsonBean gsonBean;

    public ShopcartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopcart, container, false);
        mMZBanner = (MZBannerView) view.findViewById(R.id.banner);
        int [] url = new int[] {R.drawable.cj,R.drawable.lyq,R.drawable.hzp,R.drawable.wy,R.drawable.px};
        ArrayList<Object> list = new ArrayList<>();

        for (int i = 0; i < url.length; i++) {
            list.add(url[i]);
        }
        Log.e("yxx","shuu111"+list.size());
        // 设置数据u
        mMZBanner.setPages(list, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });



        return view;
    }
    public static class BannerViewHolder implements MZViewHolder<Integer> {
        private ImageView mImageView;
        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item,null);
            mImageView = (ImageView) view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, Integer data) {
            // 数据绑定
            mImageView.setImageResource(data);
        }
    }

}
;