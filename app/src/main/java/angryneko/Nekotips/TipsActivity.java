package angryneko.Nekotips;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


public class TipsActivity extends AppCompatActivity {

    TextView tvTips;
    String ListPreference;
    private SharedPreferences mSett;
    final int MENU_SETTING_ID = 3;
    final int MENU_ABOUT_ID = 2;
    final int MENU_QUIT_ID = 1;
    int tips22;
    private SharedPreferences mSettings;
    public static final String APP_PREFERENCES_TS = "12345";
    public static final String APP_PREFERENCES = "mysettings";
    InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tips);

        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics metricsB = new DisplayMetrics();
        display.getMetrics(metricsB);

        if(metricsB.widthPixels < 1024 && metricsB.heightPixels < 600){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }


        mSett = PreferenceManager.getDefaultSharedPreferences(this);
        mSettings = PreferenceManager.getDefaultSharedPreferences(this);

        tips22 = mSettings.getInt(APP_PREFERENCES_TS, 0);

        tvTips = (TextView) findViewById(R.id.tvTips);
        String tips[] = getResources().getStringArray(R.array.Tips);
        tvTips.setText(
                tips[tips22]);
        ListPreference = mSett.getString("listPref", "0");
        int m = Integer.parseInt(ListPreference);
        switch (m) {
            case 1:
                tvTips.setTextSize(16);
                break;
            case 2:
                tvTips.setTextSize(18);
                break;
            case 3:
                tvTips.setTextSize(20);
                break;
            case 4:
                tvTips.setTextSize(22);
                break;
            case 0:
                tvTips.setTextSize(18);
                break;
        }
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-2759098232123964/9788280536");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                finish();
            }
        });

        requestNewInterstitial();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.add(0, MENU_SETTING_ID, 0, "Настройки");
        menu.add(0, MENU_ABOUT_ID, 0, "О программе");
        menu.add(0, MENU_QUIT_ID, 0, "Выйти");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case MENU_ABOUT_ID:
                Intent intent = new Intent(TipsActivity.this, AboutActivity.class);
                startActivity(intent);
                break;
            case MENU_QUIT_ID:
                finish();
                break;
            case MENU_SETTING_ID:
                Intent intent1 = new Intent(getBaseContext(), SettingActivity.class);
                startActivity(intent1);
        }
        return super.onOptionsItemSelected(item);
    }

    public void onResume() {

        ListPreference = mSett.getString("listPref", "0");
        int m = Integer.parseInt(ListPreference);
        switch (m) {
            case 1:
                tvTips.setTextSize(16);
                break;
            case 2:
                tvTips.setTextSize(18);
                break;
            case 3:
                tvTips.setTextSize(20);
                break;
            case 4:
                tvTips.setTextSize(22);
                break;
            case 0:
                tvTips.setTextSize(18);
                break;
        }
        super.onResume();
    }

    public void onPause(){

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
        super.onPause();
    }
    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .build();

        mInterstitialAd.loadAd(adRequest);
}}
