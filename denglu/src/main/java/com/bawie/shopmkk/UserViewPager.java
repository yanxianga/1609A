package com.bawie.shopmkk;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * date:2018/12/4
 * author:KK(别来无恙)
 * function:
 */
public class UserViewPager extends ViewPager {
    //是否可以左右滑动? true可以 像Android原生ViewPager一样
    //false 禁止viewPager左右滑动
    private boolean scrollable = false;
    public UserViewPager(@NonNull Context context) {
        super(context);
    }

    public UserViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    //scrollable的set方法
    // 由于上边设置为false  因此不会自动滑动
    public void setScrollable(boolean scrollable) {
        this.scrollable = scrollable;
    }

    //防止Fragment左右滑动
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return scrollable;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return scrollable;
    }
}
