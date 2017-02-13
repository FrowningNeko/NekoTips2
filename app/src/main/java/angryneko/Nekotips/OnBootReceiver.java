package angryneko.Nekotips;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class OnBootReceiver extends BroadcastReceiver {

    //Запускаем приложение при включении телефона
    private SharedPreferences mSett;

    @Override
    public void onReceive(Context context, Intent intent) {
        mSett = PreferenceManager.getDefaultSharedPreferences(context);
        if (mSett.getBoolean("checkboxPref", false)) {
            {
                if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
                    Intent serviceLauncher = new Intent(context, ServiceEx.class);
                    context.startService(serviceLauncher);
                }
            }
        }

    }
}

