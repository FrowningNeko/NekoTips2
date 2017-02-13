package angryneko.Nekotips;


import android.content.BroadcastReceiver;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.widget.Toast;

import java.util.Random;


public class RepeatingAlarmService extends BroadcastReceiver {
    private static final int NOTIFY_ID = 101;


    @Override
    public void onReceive(Context context, Intent intent) {
        //Сообщение, которое будет выводиться в уведомлениях



        Intent notificationIntent = new Intent(context, TipsActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        Resources res = context.getResources();
        Notification.Builder builder = new Notification.Builder(context);

        builder.setContentIntent(contentIntent)
                .setSmallIcon(R.drawable.ico1)
                        // большая картинка
                .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.ico1))
                        //.setTicker(res.getString(R.string.warning)) // текст в строке состояния
                .setTicker("Здравствуй, уделишь мне минутку внимания?")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                        //.setContentTitle(res.getString(R.string.notifytitle)) // Заголовок уведомления
                .setContentTitle("Новый совет!")
                        //.setContentText(res.getString(R.string.notifytext))
                .setContentText("У меня появился для тебя новый совет"); // Текст уведомления

        Notification notification = builder.getNotification(); // до API 16
        //Notification notification = builder.build();

        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFY_ID, notification);
Intent intent12 = new Intent(context, Tips.class);
        context.startService(intent12);
        Intent intent1 = new Intent(context, ServiceEx.class);
        context.startService(intent1);
    }


}