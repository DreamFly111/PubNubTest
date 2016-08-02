package duoy.cn.pubnubtest;

/**
 * Created by Dream on 2016/5/27.
 */
public class PNEvent {
    private Object message;

    public PNEvent(Object message) {
        this.message = message;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
}

