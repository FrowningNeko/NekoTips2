package angryneko.Nekotips;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class TimeSetting extends FragmentActivity {
    TextView tvTime;
    static int hourOfDay;
    static int minute;
    private SharedPreferences mSettings;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_hourOfDay = "hourOfDay";
    public static final String APP_PREFERENCES_minute = "minute";
    Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_setting);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        tvTime = (TextView)findViewById(R.id.textView10);

        if (mSettings.contains(APP_PREFERENCES_hourOfDay)) {
            // Получаем число из настроек
            hourOfDay = mSettings.getInt(APP_PREFERENCES_hourOfDay , 0);
            minute = mSettings.getInt(APP_PREFERENCES_minute, 0);
            tvTime.setText(hourOfDay + ":" + minute);
        } else {
            hourOfDay = 12;
            minute = 0;
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putInt(APP_PREFERENCES_hourOfDay, hourOfDay);
            editor.putInt(APP_PREFERENCES_minute, minute);
            editor.apply();
            tvTime.setText(hourOfDay + ":" + minute);
        }

    }
    public void selectTime(View view) {
        DialogFragment newFragment = new SelectTimeFragment();
        newFragment.show(getSupportFragmentManager(), "TimePicker");
    }
    public void populateSetTime(int hourOfDay, int minute) {

        tvTime.setText(hourOfDay + ":" + minute);
        this.hourOfDay = hourOfDay;
        this.minute = minute;
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putInt(APP_PREFERENCES_hourOfDay, hourOfDay);
        editor.putInt(APP_PREFERENCES_minute, minute);
        editor.apply();
    }
    @SuppressLint("ValidFragment")
    public class SelectTimeFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);;
            minute = calendar.get(Calendar.MINUTE);
            return new TimePickerDialog(getActivity(), this, hourOfDay, minute, true);
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            populateSetTime(hourOfDay, minute);
        }
    }

}

