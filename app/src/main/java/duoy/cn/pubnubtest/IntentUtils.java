package duoy.cn.pubnubtest;

import android.content.Context;
import android.content.Intent;

/**
 * Created by dandanba on 3/1/16.
 */
public class IntentUtils{
    private static final String TAG = IntentUtils.class.getSimpleName();

    public static Intent generateIntent(Context context, Class<?> cls) {
        return new Intent(context, cls);
    }
}
