package cn.byk.pandora.libs.base.widget.layout;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import cn.byk.pandora.libs.base.R;

/**
 * Created by Byk on 2018/8/6.
 **/
public class MultiStateView extends FrameLayout {

    private static final String TAG = MultiStateView.class.getSimpleName();

    private LayoutInflater mInflater;

    private int mEmptyImage;
    private CharSequence mEmptyText, mLoadingText;
    private int mErrorImage;
    private CharSequence mErrorText, mRetryText;
    private int mTextColor, mTextSize;
    private int mButtonTextColor, mButtonTextSize;
    private Drawable mButtonBackground;
    private int mEmptyResId = NO_ID, mLoadingResId = NO_ID, mErrorResId = NO_ID;
    private int mContentId = NO_ID;
    private Map<Integer, View> mLayouts = new HashMap<>();

    private OnClickListener mRetryListener;
    private OnClickListener mRetryButtonClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mRetryListener != null) {
                mRetryListener.onClick(v);
            }
        }
    };

    public MultiStateView(@NonNull Context context) {
        super(context);
    }

    public MultiStateView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MultiStateView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        mInflater = LayoutInflater.from(context);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MultiStateView, defStyleAttr,
                R.style.MultiStateView_style);

        mLoadingText = a.getString(R.styleable.MultiStateView_msv_loadingText);

        // Set the DefValue
        mEmptyImage = a.getResourceId(R.styleable.MultiStateView_msv_emptyImage, -1);
        mEmptyText = a.getString(R.styleable.MultiStateView_msv_emptyText);

        mErrorImage = a.getResourceId(R.styleable.MultiStateView_msv_errorImage, NO_ID);
        mErrorText = a.getString(R.styleable.MultiStateView_msv_errorText);
        mRetryText = a.getString(R.styleable.MultiStateView_msv_retryText);

        mTextColor = a.getColor(R.styleable.MultiStateView_msv_textColor, 0xff999999);
        mTextSize = a.getDimensionPixelSize(R.styleable.MultiStateView_msv_textSize, dp2px(16));

        mButtonTextColor = a.getColor(R.styleable.MultiStateView_msv_buttonTextColor, 0xff999999);
        mButtonTextSize = a.getDimensionPixelSize(R.styleable.MultiStateView_msv_buttonTextSize, dp2px(16));
        mButtonBackground = a.getDrawable(R.styleable.MultiStateView_msv_buttonBackground);

        mEmptyResId = a.getResourceId(R.styleable.MultiStateView_msv_emptyResId, R.layout.view_msv_empty);
        mLoadingResId = a.getResourceId(R.styleable.MultiStateView_msv_loadingResId, R.layout.view_msv_loading);
        mErrorResId = a.getResourceId(R.styleable.MultiStateView_msv_errorResId, R.layout.view_msv_error);
        a.recycle();
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if (getChildCount() == 0) {
            return;
        }
        if (getChildCount() > 1) {
            removeViews(1, getChildCount() - 1);
        }
        View view = getChildAt(0);
        setContentView(view);
        showLoading();
    }

    public static MultiStateView wrap(Activity activity) {
        return wrap(((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0));
    }

    public static MultiStateView wrap(Fragment fragment) {
        return wrap(fragment.getView());
    }

    public static MultiStateView wrap(View view) {
        if (view == null) {
            throw new RuntimeException("content view can not be null");
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        int index = parent.indexOfChild(view);
        parent.removeView(view);

        MultiStateView layout = new MultiStateView(view.getContext());
        parent.addView(layout, index, lp);
        layout.addView(view);
        layout.setContentView(view);
        return layout;
    }

    private void setContentView(View view) {
        mContentId = view.getId();
        mLayouts.put(mContentId, view);
    }

    public MultiStateView setLoading(@LayoutRes int id) {
        if (mLoadingResId != id) {
            remove(mLoadingResId);
            mLoadingResId = id;
        }
        return this;
    }

    public MultiStateView setEmpty(@LayoutRes int id) {
        if (mEmptyResId != id) {
            remove(mEmptyResId);
            mEmptyResId = id;
        }
        return this;
    }

    public MultiStateView setError(@LayoutRes int id) {
        if (mErrorResId != id) {
            remove(mErrorResId);
            mErrorResId = id;
        }
        return this;
    }

    public MultiStateView setLoadingText(String value) {
        mLoadingText = value;
        text(mLoadingResId, R.id.msv_loading_text, mLoadingText);
        return this;
    }

    public MultiStateView setEmptyImage(@DrawableRes int resId) {
        mEmptyImage = resId;
        image(mEmptyResId, R.id.msv_empty_image, mEmptyImage);
        return this;
    }

    public MultiStateView setEmptyText(String value) {
        mEmptyText = value;
        text(mEmptyResId, R.id.msv_empty_text, mEmptyText);
        return this;
    }

    public MultiStateView setErrorImage(@DrawableRes int resId) {
        mErrorImage = resId;
        image(mErrorResId, R.id.msv_error_image, mErrorImage);
        return this;
    }

    public MultiStateView setErrorText(String value) {
        mErrorText = value;
        text(mErrorResId, R.id.msv_error_text, mErrorText);
        return this;
    }

    public MultiStateView setRetryText(String text) {
        mRetryText = text;
        text(mErrorResId, R.id.msv_retry_button, mRetryText);
        return this;
    }

    public MultiStateView setRetryListener(OnClickListener listener) {
        mRetryListener = listener;
        return this;
    }

    public void showLoading() {
        show(mLoadingResId);
    }

    public void showEmpty() {
        show(mEmptyResId);
    }

    public void showError() {
        show(mErrorResId);
    }

    public void showContent() {
        show(mContentId);
    }

    private void show(int layoutId) {
        for (View view : mLayouts.values()) {
            view.setVisibility(GONE);
        }
        layout(layoutId).setVisibility(VISIBLE);
    }

    private void remove(int layoutId) {
        if (mLayouts.containsKey(layoutId)) {
            View vg = mLayouts.remove(layoutId);
            removeView(vg);
        }
    }

    private View layout(int layoutId) {
        if (mLayouts.containsKey(layoutId)) {
            return mLayouts.get(layoutId);
        }
        View layout = mInflater.inflate(layoutId, this, false);
        layout.setVisibility(GONE);
        addView(layout);
        mLayouts.put(layoutId, layout);

        if (layoutId == mEmptyResId) {
            ImageView img = (ImageView) layout.findViewById(R.id.msv_empty_image);
            if (img != null && mEmptyImage != NO_ID) {
                img.setImageResource(mEmptyImage);
            }
            TextView view = (TextView) layout.findViewById(R.id.msv_empty_text);
            if (view != null) {
                view.setText(mEmptyText);
                view.setTextColor(mTextColor);
                view.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
            }
        } else if (layoutId == mErrorResId) {
            ImageView img = (ImageView) layout.findViewById(R.id.msv_error_image);
            if (img != null && mErrorImage != NO_ID) {
                img.setImageResource(mErrorImage);
            }
            TextView txt = (TextView) layout.findViewById(R.id.msv_error_text);
            if (txt != null) {
                txt.setText(mErrorText);
                txt.setTextColor(mTextColor);
                txt.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
            }
            TextView btn = (TextView) layout.findViewById(R.id.msv_retry_button);
            if (btn != null) {
                btn.setText(mRetryText);
                btn.setTextColor(mButtonTextColor);
                btn.setTextSize(TypedValue.COMPLEX_UNIT_PX, mButtonTextSize);

                if (Build.VERSION.SDK_INT >= 16) {
                    btn.setBackground(mButtonBackground);
                } else {
                    //noinspection deprecation
                    btn.setBackgroundDrawable(mButtonBackground);
                }

                btn.setOnClickListener(mRetryButtonClickListener);
            }
        }
        return layout;
    }

    private void text(int layoutId, int ctrlId, CharSequence value) {
        if (mLayouts.containsKey(layoutId)) {
            TextView view = (TextView) mLayouts.get(layoutId)
                                               .findViewById(ctrlId);
            if (view != null) {
                view.setText(value);
            }
        }
    }

    private void image(int layoutId, int ctrlId, int resId) {
        if (mLayouts.containsKey(layoutId)) {
            ImageView view = (ImageView) mLayouts.get(layoutId)
                                                 .findViewById(ctrlId);
            if (view != null) {
                view.setImageResource(resId);
            }
        }
    }

    private int dp2px(float dp) {
        return (int) (getResources().getDisplayMetrics().density * dp);
    }

}
