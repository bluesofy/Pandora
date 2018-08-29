package cn.byk.pandora.libs.base.event;

import cn.byk.pandora.libs.util.message.BaseEvent;

/**
 * Created by Byk on 2018/8/14.
 **/
public class ScreenEvent extends BaseEvent {

    private static final String TAG = ScreenEvent.class.getSimpleName();

    public static final int TYPE_SCREEN_ON = 1;
    public static final int TYPE_SCREEN_OFF = 2;
    public static final int TYPE_USER_PRESENT = 3;

    protected ScreenEvent() {}

    public static ScreenEvent build() {
        return new ScreenEvent();
    }

    @Override
    public String getTag() {
        return TAG;
    }

    public ScreenEvent on() {
        setType(TYPE_SCREEN_ON);
        return this;
    }

    public ScreenEvent off() {
        setType(TYPE_SCREEN_OFF);
        return this;
    }

    public ScreenEvent userPresent() {
        setType(TYPE_USER_PRESENT);
        return this;
    }
}
