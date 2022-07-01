package adet.finalsproject.t174.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{
    Switch lightDarkSwitch = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        lightDarkSwitch = (Switch) findViewById(R.id.lightDarkSwitch);
        lightDarkSwitch.setOnCheckedChangeListener(this);

        switch (AppCompatDelegate.getDefaultNightMode()) {
            case AppCompatDelegate.MODE_NIGHT_YES:
                lightDarkSwitch.setText("Dark Mode");
                lightDarkSwitch.setChecked(true);
                break;

            default:
                lightDarkSwitch.setText("Light Mode");
                lightDarkSwitch.setChecked(false);
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            System.out.println("checked");
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            lightDarkSwitch.setText("Dark Mode");
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            System.out.println("unchecked");
            lightDarkSwitch.setText("Light Mode");
        }
    }
}