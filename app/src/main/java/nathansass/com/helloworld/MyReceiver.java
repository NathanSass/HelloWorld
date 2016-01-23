package nathansass.com.helloworld;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by nathansass on 1/23/16.
 */
public class MyReceiver extends BroadcastReceiver {
//    Must close device then when the device is turned on, this shows
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Intent Detected.", Toast.LENGTH_LONG).show();
    }
}
