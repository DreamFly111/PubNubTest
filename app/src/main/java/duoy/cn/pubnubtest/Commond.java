package duoy.cn.pubnubtest;

/**
 * Created by Dream on 2016/5/27.
 */
public class Commond {
    public String sender;
    public String message;
    public long timeStamp;

    public Commond() {
    }

    public Commond(String sender, String message, long timeStamp) {
        this.sender = sender;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    @Override
    public int hashCode() {
        return (this.sender + this.message + this.timeStamp).hashCode();
    }
}
