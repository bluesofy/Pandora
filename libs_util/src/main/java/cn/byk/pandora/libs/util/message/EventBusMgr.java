package cn.byk.pandora.libs.util.message;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Byk on 2018/7/7.
 **/
public class EventBusMgr {

    /**
     * EventBus register
     */
    public static void register(Object object) {
        if (!EventBus.getDefault()
                     .isRegistered(object)) {
            EventBus.getDefault()
                    .register(object);
        }
    }

    /**
     * EventBus unregister
     */
    public static void unRegister(Object object) {
        if (EventBus.getDefault()
                    .isRegistered(object)) {
            EventBus.getDefault()
                    .unregister(object);
        }
    }

    public static void post(BaseEvent event) {
        EventBus.getDefault()
                .post(event);
    }

    public static void postSticky(BaseEvent event) {
        EventBus.getDefault()
                .postSticky(event);
    }

    public static void removeStickyEvent(BaseEvent object) {
        EventBus.getDefault()
                .removeStickyEvent(object);
    }
}
