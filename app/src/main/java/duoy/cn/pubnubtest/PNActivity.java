package duoy.cn.pubnubtest;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLog;
import android.util.Log;

import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubException;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

public class PNActivity extends AppCompatActivity {

    private final static String TAG = "PUBNUB";
    private String mUUID;
    private String mChannel;
    private Pubnub mPubNub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUUID = User.getCurrentUser().getObjectId();
        mChannel = "test";

        initPubNub();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (mPubNub == null) {
            initPubNub();
        } else {
            subscribe();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        unsubscribe();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
        if (mPubNub != null) {
            mPubNub.shutdown();
            mPubNub = null;
        }
    }

    public void initPubNub() {
        Log.i(TAG, "initPubNub :" + "uuid :" + mUUID);
        mPubNub = new Pubnub(Constants.PN_PUB_KEY, Constants.PN_SUB_KEY);
        mPubNub.setUUID(mUUID);
        subscribe();
    }

    public void subscribe() {
        Log.i(TAG, "subscribe :" + "channel :" + mChannel);
        EventBus.getDefault().register(this);

        try {
            mPubNub.subscribe(mChannel, new PNCallback(this) {
                @Override
                public void successCallback(String channel, Object message) {
                    super.successCallback(channel, message);
                    if (mChannel.equals(channel)) {
                        Commond command = JsonUtils.readValue(message.toString(), Commond.class);
                        if (!mUUID.equals(command.getSender())) {
                            Log.i(TAG, "delay :" + (command.getTimeStamp() - System.currentTimeMillis()));
                            EventBus.getDefault().post(new PNEvent(command.getMessage()));
//                            EventBus.getDefault().post("hello");
                        }
                    }
                }

                @Override
                public void connectCallback(String channel, Object message) {
                    super.connectCallback(channel, message);
                }

            });
        } catch (PubnubException e) {
            e.printStackTrace();
        }
    }

    private void unsubscribe() {
        Log.i(TAG, "unsubscribe :" + ",channel :" + mChannel);
        if (mPubNub != null) {
            mPubNub.unsubscribeAll();
        }
        EventBus.getDefault().unregister(this);
    }

    public void publish(String message){
        Log.i(TAG, "publish :" + ",message :" + message);
        if (message.equals("")) return; // Return if empty

        final Commond command = new Commond(mUUID, message, System.currentTimeMillis());
        message = JsonUtils.writeValueAsString(command);
        mPubNub.publish(mChannel, message, new PNCallback(this) {
            @Override
            public void successCallback(String channel, Object message) {
            }
        });
    }
}
