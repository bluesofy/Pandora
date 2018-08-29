package cn.byk.pandora.libs.base.event;

import android.content.Intent;

import cn.byk.pandora.libs.util.message.BaseEvent;

/**
 * Created by Byk on 2018/8/14.
 * <p>
 * Package Changed Action Event
 **/
public class PkgActEvent extends BaseEvent {

    private static final String TAG = PkgActEvent.class.getSimpleName();

    public static final int TYPE_ADD = 1;
    public static final int TYPE_REPLACE = 2;
    public static final int TYPE_REMOVE = 3;

    private boolean mViaSilent;
    private Intent mData;

    protected PkgActEvent() {
    }

    public static PkgActEvent build() {
        return new PkgActEvent();
    }

    @Override
    public String getTag() {
        return TAG;
    }

    public Intent getData() {
        return mData;
    }

    public PkgActEvent setData(Intent data) {
        mData = data;
        return this;
    }

    public PkgActEvent viaSilent(boolean isSilent) {
        mViaSilent = isSilent;
        return this;
    }

    public boolean isViaSilent() {
        return mViaSilent;
    }

    public PkgActEvent add(String tips) {
        setType(TYPE_ADD);
        setMsg(tips);
        return this;
    }

    public PkgActEvent replace(String tips) {
        setType(TYPE_REPLACE);
        setMsg(tips);
        return this;
    }

    public PkgActEvent remove(String tips) {
        setType(TYPE_REMOVE);
        setMsg(tips);
        return this;
    }
}
