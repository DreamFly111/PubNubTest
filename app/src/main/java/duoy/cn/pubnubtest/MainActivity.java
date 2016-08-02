package duoy.cn.pubnubtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubException;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends PNActivity{

    @Bind(R.id.textText)
    TextView textView;

    @Bind(R.id.editText)
    EditText editText;

    @OnClick(R.id.btn)
    public void onClick(View view){

        final String message = editText.getText().toString();
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
        publish(message);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //注册
        ButterKnife.bind(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPNEvent(PNEvent event){
        String message = event.getMessage().toString();
        textView.setText(message);
        Log.i("test", message+"@@@");
    }

}
