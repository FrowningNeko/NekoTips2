package angryneko.Nekotips;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;


public class SettingActivity extends PreferenceActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        // Get the custom preference

        Preference customPref = (Preference) findPreference("customPref");
        customPref
                .setOnPreferenceClickListener(new OnPreferenceClickListener() {

                    public boolean onPreferenceClick(Preference preference) {
                        Intent intent = new Intent(getBaseContext(), AboutActivity.class);
                        startActivity(intent);
                        return true;
                    }

                });
        Preference customTips= (Preference) findPreference("customTips");
        customTips
                .setOnPreferenceClickListener(new OnPreferenceClickListener() {

                    public boolean onPreferenceClick(Preference preference) {
                        Intent intent12 = new Intent(getBaseContext(), TipsActivity.class);
                        startActivity(intent12);
                        return true;
                    }

                });
        Preference customPref2= (Preference) findPreference("customPref2");
        customPref2
                .setOnPreferenceClickListener(new OnPreferenceClickListener() {

                    public boolean onPreferenceClick(Preference preference) {
                        String url = "https://play.google.com/store/apps/details?id=angryneko.Nekotipspro";
                        Intent intent13 = new Intent(Intent.ACTION_VIEW);
                        intent13.setData(Uri.parse(url));
                        startActivity(intent13);
                        return true;
                    }

                });

    Preference customTime= (Preference) findPreference("customTime");
    customTime
            .setOnPreferenceClickListener(new OnPreferenceClickListener() {

        public boolean onPreferenceClick(Preference preference) {
            Intent intent12 = new Intent(getBaseContext(), TimeSetting.class);
            startActivity(intent12);
            return true;
        }

    });
}

}
