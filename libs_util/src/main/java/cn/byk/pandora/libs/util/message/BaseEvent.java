package cn.byk.pandora.libs.util.message;

/**
 * Created by Byk on 2018/7/7.
 **/
public class BaseEvent {

    private static final String TAG = BaseEvent.class.getSimpleName();

    private int type;
    private String msg;

    public BaseEvent() {}

    public BaseEvent(int type, String msg) {
        this.msg = msg;
        this.type = type;
    }

    public String getTag() {
        return TAG;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
