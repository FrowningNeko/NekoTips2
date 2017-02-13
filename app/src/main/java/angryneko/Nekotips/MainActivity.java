package angryneko.Nekotips;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton buttonSetting;
    Button Start, Stop;
    PendingIntent myPendingIntent;
    Intent intent;
    public static int yy;
    public static int mm;
    public static int dd;
    static int hourOfDay;
    static int minute;
    public static Calendar calendar;
    TextView tvStatus;
    int REQUEST_CODE = 11223344;
    public static AlarmManager alarmManager;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_hourOfDay = "hourOfDay";
    public static final String APP_PREFERENCES_minute = "minute";
    private SharedPreferences mSettings;
    private SharedPreferences mSett;
    String ListPreference;
    String etSetting;



    final int MENU_SETTING_ID = 3;
    final int MENU_QUIT_ID = 1;
    final int MENU_ABOUT_ID = 2;
    final int MENU_TIPS_ID = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics metricsB = new DisplayMetrics();
        display.getMetrics(metricsB);

        if(metricsB.widthPixels < 1024 && metricsB.heightPixels < 600){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        buttonSetting = (ImageButton) findViewById(R.id.imBut);
        Start = (Button) findViewById(R.id.buttonStart);
        Stop = (Button) findViewById(R.id.buttonStop);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        mSett = PreferenceManager.getDefaultSharedPreferences(this);
        tvStatus = (TextView) findViewById(R.id.textView7);

        Start.setOnClickListener(this);
        Stop.setOnClickListener(this);
        buttonSetting.setOnClickListener(this);

        if (mSettings.contains(APP_PREFERENCES_hourOfDay)) {
            // Получаем число из настроек
            hourOfDay = mSettings.getInt(APP_PREFERENCES_hourOfDay , 0);
            minute = mSettings.getInt(APP_PREFERENCES_minute, 0);
        } else {
            hourOfDay = 12;
            minute = 0;
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putInt(APP_PREFERENCES_hourOfDay, hourOfDay);
            editor.putInt(APP_PREFERENCES_minute, minute);
            editor.apply();
        }
        getSystemService(Activity.ALARM_SERVICE);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE); //Уведомление
        intent = new Intent(this, RepeatingAlarmService.class);
        myPendingIntent = PendingIntent.getBroadcast(this, REQUEST_CODE, intent, 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        menu.add(0, MENU_TIPS_ID, 0, "Совет дня");
        menu.add(0, MENU_SETTING_ID, 0, "Настройки");
        menu.add(0, MENU_ABOUT_ID, 0, "О программе");
        menu.add(0, MENU_QUIT_ID, 0, "Выйти");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case MENU_ABOUT_ID:
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
                break;
            case MENU_QUIT_ID:
                finish();
                break;
            case MENU_SETTING_ID:
                Intent intent1 = new Intent(getBaseContext(), SettingActivity.class);
                startActivity(intent1);
                break;
            case MENU_TIPS_ID:
                Intent intent12 = new Intent(getBaseContext(), TipsActivity.class);
                startActivity(intent12);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonStart:
                hourOfDay = mSettings.getInt(APP_PREFERENCES_hourOfDay , 0);
                minute = mSettings.getInt(APP_PREFERENCES_minute, 0);
                Calendar myAlarmDate = Calendar.getInstance();
                calendar = Calendar.getInstance();
                yy = calendar.get(Calendar.YEAR);
                mm = calendar.get(Calendar.MONTH);
                dd = calendar.get(Calendar.DAY_OF_MONTH);
                myAlarmDate.setTimeInMillis(System.currentTimeMillis());
                myAlarmDate.set(Calendar.MONTH, mm);
                myAlarmDate.set(Calendar.YEAR, yy);
                myAlarmDate.set(Calendar.DAY_OF_MONTH, dd);
                myAlarmDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
                myAlarmDate.set(Calendar.MINUTE, minute);
                myAlarmDate.set(Calendar.SECOND, 0);
                alarmManager.set(AlarmManager.RTC_WAKEUP, myAlarmDate.getTimeInMillis(), myPendingIntent);
                SharedPreferences.Editor editor = mSettings.edit();
                editor.putInt(APP_PREFERENCES_hourOfDay, hourOfDay);
                editor.putInt(APP_PREFERENCES_minute, minute);
                editor.apply();
                Toast.makeText(this, "Приложение запущено", Toast.LENGTH_LONG).show();
                break;
            case R.id.buttonStop:

                if (alarmManager != null) {
                    alarmManager.cancel(myPendingIntent);
                    Toast.makeText(this, "Приложение остановлено", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.imBut:
                Intent intent1 = new Intent(getBaseContext(), SettingActivity.class);
                startActivity(intent1);
                break;
        }

    }

    public void onResume() {

        ListPreference = mSett.getString("listPref", "0");
        int m = Integer.parseInt(ListPreference);
        switch (m) {
            case 1:
                tvStatus.setTextSize(16);
                break;
            case 2:
                tvStatus.setTextSize(18);
                break;
            case 3:
                tvStatus.setTextSize(20);
                break;
            case 4:
                tvStatus.setTextSize(22);
                break;
            case 0:
                tvStatus.setTextSize(18);
                break;
        }
        super.onResume();
    }


}