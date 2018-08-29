package cn.byk.pandora.libs.base.widget;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Byk on 2018/8/6.
 **/
public class BaseViewPager extends ViewPager {

    private boolean isDisableScroll;

    public BaseViewPager(@NonNull Context context) {
        super(context);
    }

    public BaseViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseViewPager disableScroll(boolean value) {
        isDisableScroll = value;
        return this;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return !isDisableScroll && super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return !isDisableScroll && super.onInterceptTouchEvent(ev);
    }
}
