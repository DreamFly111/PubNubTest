package duoy.cn.pubnubtest;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;

/**
 * Created by dandanba on 3/1/16.
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        /*Fabric.with(this, new Crashlytics());
        FIR.init(this);*/


        AVObject.registerSubclass(User.class);

        AVOSCloud.initialize(this, Constants.AVOS_APP_ID, Constants.AVOS_APP_KEY); // 初始化参数依次为 this, AppId, AppKey
        // AVOSCloud.useAVCloudUS(); // 启用北美节点
        // AVAnalytics.enableCrashReport(this.getApplicationContext(), true);  // 启用崩溃错误统计
        AVOSCloud.setLastModifyEnabled(true);
        AVOSCloud.setDebugLogEnabled(true);
    }
}
